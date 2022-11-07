package synchronized篇.线程执行顺序测试monitor;


import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 * <p>
 * 根据QMode的不同，会执行不同的唤醒策略
 */
@Slf4j
public class SyncQModeDemo {

    public static void main(String[] args) throws InterruptedException {

        SyncQModeDemo demo = new SyncQModeDemo();

        demo.startThreadA();
        //控制线程执行时间
        Thread.sleep(100);
        demo.startThreadB();
        Thread.sleep(100);
        demo.startThreadC();
        Thread.sleep(100);
        //demo.startThreadD();
    }

    final Object lock = new Object();

    public void startThreadA() {
        new Thread(() -> {
            synchronized (lock) {
                log.info("A get lock");
                try {
                    Thread.sleep(1000);
                    //lock.wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("A release lock");
            }
        }, "thread-A").start();
    }

    public void startThreadB() {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    log.info("B get lock");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("B release lock");
            }
        }, "thread-B").start();
    }

    public void startThreadC() {
        new Thread(() -> {
            synchronized (lock) {

                log.info("C get lock");
            }
        }, "thread-C").start();
    }

    public void startThreadD() {
        new Thread(() -> {
            synchronized (lock) {

                log.info("D get lock");
            }
        }, "thread-D").start();
    }

}
