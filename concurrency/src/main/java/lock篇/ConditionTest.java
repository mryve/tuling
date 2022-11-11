package lock篇;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 * 等待唤醒机制 await/signal测试
 */
@Slf4j
public class ConditionTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                log.info(Thread.currentThread().getName() + " 开始处理任务");
                //会释放当前持有的锁，然后阻塞当前线程
                condition.await();
                log.info(Thread.currentThread().getName() + " 结束处理任务");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                log.info(Thread.currentThread().getName() + " 释放锁");
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.info(Thread.currentThread().getName() + " 开始处理任务");

                Thread.sleep(2000);
                //唤醒因调用Condition#await方法而阻塞的线程
                condition.signal();
                log.info(Thread.currentThread().getName() + " 结束处理任务");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                log.info(Thread.currentThread().getName() + " 释放锁");
            }
        }, "t2").start();


    }
}
