package lock篇;

import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 * 可中断
 */
@Slf4j
public class ReentrantLockDemo3 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {

            log.info("t1启动...");

            try {
                lock.lockInterruptibly();
                try {
                    log.info("t1获得了锁");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("t1等锁的过程中被中断");
            }

        }, "t1");


        lock.lock();
        try {
            log.info("main线程获得了锁");
            t1.start();
            //先让线程t1执行
            Thread.sleep(1000);

            t1.interrupt();
            log.info("线程t1执行中断");
        } finally {
            lock.unlock();
        }

    }

}
