package synchronized篇.锁.偏向锁测试;

import org.openjdk.jol.info.ClassLayout;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Fox
 * 测试 偏向锁，轻量级锁，重量级锁标记变化
 * 关闭延迟开启偏向锁： -XX:BiasedLockingStartupDelay=0
 * 无锁 001
 * 偏向锁 101
 * 轻量级锁 00
 * 重量级锁 10
 */
@Slf4j
public class LockEscalationDemo{

    public static void main(String[] args) throws InterruptedException {

        log.info(ClassLayout.parseInstance(new Object()).toPrintable());
        //HotSpot 虚拟机在启动后有个 4s 的延迟才会对每个新建的对象开启偏向锁模式
        Thread.sleep(5000);
        Object obj = new Object();
        // 思考： 如果对象调用了hashCode,还会开启偏向锁模式吗
        //obj.hashCode();
        log.info(ClassLayout.parseInstance(obj).toPrintable());

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName()+"开始执行。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
                synchronized (LockEscalationDemo.class){
                    // 思考：偏向锁执行过程中，调用hashcode会发生什么？
                    //obj.hashCode();
//                    //obj.notify();
////                    try {
////                        obj.wait(50);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }

                    log.info(Thread.currentThread().getName()+"获取锁执行中。。。\n"
                            +ClassLayout.parseInstance(obj).toPrintable());
                }
                log.info(Thread.currentThread().getName()+"释放锁。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread1").start();

        //控制线程竞争时机
        Thread.sleep(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName()+"开始执行。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
                synchronized (obj){
                    log.info(Thread.currentThread().getName()+"获取锁执行中。。。\n"
                            +ClassLayout.parseInstance(obj).toPrintable());
                }
                log.info(Thread.currentThread().getName()+"释放锁。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
            }
        },"thread2").start();

//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                log.info(Thread.currentThread().getName()+"开始执行。。。\n"
//                        +ClassLayout.parseInstance(obj).toPrintable());
//                synchronized (obj){
//
//                    log.info(Thread.currentThread().getName()+"获取锁执行中。。。\n"
//                            +ClassLayout.parseInstance(obj).toPrintable());
//                }
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                log.info(Thread.currentThread().getName()+"释放锁。。。\n"
//                        +ClassLayout.parseInstance(obj).toPrintable());
//            }
//        },"thread3").start();


        Thread.sleep(5000);
        log.info(ClassLayout.parseInstance(obj).toPrintable());



    }

    public void lockTest() throws InterruptedException {
        log.info(ClassLayout.parseInstance(new Object()).toPrintable());
        Thread.sleep(4000);
        log.info(ClassLayout.parseInstance(new Object()).toPrintable());
    }
}