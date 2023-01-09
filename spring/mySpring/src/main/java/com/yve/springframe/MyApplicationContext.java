package com.yve.springframe;


import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 伟大的Yve菌
 * 我们自己定义的一个spring的ApplicationContext类, 我们通过ApplicationContext可以获取到任意bean对象
 */
public class MyApplicationContext {
    private Class appConfig;
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<String, Object> singleObjects = new HashMap<>();
    private List<BeanPostProcessor> beanPostProcessorsList = new ArrayList<>();

    /**
     * @param appConfig 传入配置类
     *                  配置类赋值, 扫描路径获取到bean对象之后把单例bean放入单例池
     */
    public MyApplicationContext(Class appConfig) {
        this.appConfig = appConfig;
        scan(appConfig);
        //遍历循环每个bean, 将所有的单例bean放入单例池中
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            //获取名字和信息
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            //如果是单例就创建并放入到单例池
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singleObjects.put(beanName, bean);
            }
        }
    }

    /**
     * @param appConfig 传入配置类
     *                  此方法用于扫描classpath路径下需要被加载为bean的类, 并对他们进行存放.
     */
    private void scan(Class appConfig) {
        //首先判断当前类上是否有扫描路径注解
        if (appConfig.isAnnotationPresent(ComponentScan.class)) {
            //取出注解中的路径
            ComponentScan componentScan = (ComponentScan) appConfig.getAnnotation(ComponentScan.class);
            String path = componentScan.value(); //com.yve.user.service
            //目前的到的是文件位置, 但是我们需要修改为路径
            path = path.replace(".", "/"); //com/yve/user/service

            //我们需要解析的的实际上是编译之后的class类上的注解, 也就是classpath下的对应目录, 而path中是我们java文件的路径, 所以我们需要ClassLoader来读取到classpath的位置
            ClassLoader classLoader = MyApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            //获取对应路径下的所有文件
            File file = new File(resource.getFile());
            //判断文件是否为目录
            if (file.isDirectory()) {
                for (File listFile : file.listFiles()) {
                    //获取到每个文件的绝对路径
                    String absolutePath = listFile.getAbsolutePath(); //两个文件, 这里为了方便就写一个,\\为单斜杠  D:\\tuling\\code\\spring\\mySpring\\target\\classes\\com\\yve\\user\\service\\UserService.class
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class")).replace("\\", ".");
                    //System.out.println(absolutePath); //com.yve.user.service.OrderService

                    try {
                        //判断每个文件是否有@Component注解
                        Class<?> clazz = classLoader.loadClass(absolutePath);
                        if (clazz.isAnnotationPresent(Component.class)) {
                            //判断哪些Bean实现了BeanPostProcessor
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                BeanPostProcessor instance = (BeanPostProcessor) clazz.getConstructor().newInstance();
                                beanPostProcessorsList.add(instance);
                            }
                            String beanName = clazz.getAnnotation(Component.class).value();
                            //创建BeanDefinition保存Bean对象信息
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setType(clazz);

                            //如果该类有@Scope注解就保存为注解中的值, 否则默认为singleton
                            if (clazz.isAnnotationPresent(Scope.class)) {
                                beanDefinition.setScope(clazz.getAnnotation(Scope.class).value());
                            } else {
                                beanDefinition.setScope("singleton");
                            }

                            //把定义好的bean放入到beanDefinitionMap中
                            //如果@Component注解中没有值, 则取该类型首字母小写作为beanName
                            if (beanName.isEmpty()) {
                                beanName = Introspector.decapitalize(clazz.getSimpleName());
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @param beanName 通过beanName获取到bean对象
     * @return bean对象
     */
    public Object getBean(String beanName) {
        //通过beanDefinitionMap获取到BeanDefinition并根据作用域来返回bean对象
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        //如果没有beanName则证明该类型bean没有被声明
        if (beanDefinition == null) {
            return new NullPointerException("No bean definition");
        }
        if (beanDefinition.getScope().equals("singleton")) {
            //这里是singleton类型的bean
            Object singletonBean = singleObjects.get(beanName);
            //如果这个时候单例bean还没被加载就现在加载
            if (singletonBean == null) {
                singletonBean = createBean(beanName, beanDefinition);
                singleObjects.put(beanName, singletonBean);
            }
            return singletonBean;
        } else {
            //这里是prototype类型的bean
            return createBean(beanName, beanDefinition);
        }
    }

    /**
     * @param beanName
     * @param beanDefinition
     * @return bean对象
     * 该方法通过beanDefinition中的type的构造方法创建一个bean对象
     */
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        //通过type获取到类
        Class clazz = beanDefinition.getType();
        Object instance = null;
        try {
            //通过构造方法创建对象
            instance = clazz.getConstructor().newInstance();

            //为添加了@Autowired注解的属性赋值
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    //为属性赋值
                    field.set(instance, getBean(field.getName()));
                }
            }

            //初始化前操作
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorsList) {
                beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }
            //进行初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }
            //初始化后操作
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorsList) {
                beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
