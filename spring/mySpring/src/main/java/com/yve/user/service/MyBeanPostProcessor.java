package com.yve.user.service;
import com.yve.springframe.BeanPostProcessor;
import com.yve.springframe.Component;

/**
 * @author 伟大的Yve菌
 * 定义初始化前后的操作
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println(beanName + "初始化后");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println(beanName + "初始化前");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
