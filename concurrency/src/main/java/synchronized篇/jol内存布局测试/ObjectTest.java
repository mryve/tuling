package synchronized篇.jol内存布局测试;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author  Fox
 *
 * 关闭指针压缩（-XX:-UseCompressedOops）
 */
public class ObjectTest {

    public static void main(String[] args) throws InterruptedException {
        //jvm延迟偏向
        Thread.sleep(5000);
        Object obj = new Test();
        //Object obj = new Integer[4];
        obj.hashCode();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"获取锁\n"+ClassLayout.parseInstance(obj).toPrintable());
            synchronized (obj){
                System.out.println(Thread.currentThread().getName()+"\n"+ClassLayout.parseInstance(obj).toPrintable());
            }
            System.out.println(Thread.currentThread().getName()+"释放锁\n"+ClassLayout.parseInstance(obj).toPrintable());

            // jvm 优化
//            try {
//                Thread.sleep(100000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        },"Thread1").start();


        //Thread.sleep(2000);


        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"获取锁\n"+ClassLayout.parseInstance(obj).toPrintable());
            synchronized (obj){
                System.out.println(Thread.currentThread().getName()+"\n"+ClassLayout.parseInstance(obj).toPrintable());
            }
            System.out.println(Thread.currentThread().getName()+"释放锁\n"+ClassLayout.parseInstance(obj).toPrintable());
        },"Thread2").start();

    }
}

class Test{
    private boolean flag;
    private long  p;
}

class MyTest{
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
    }
}
