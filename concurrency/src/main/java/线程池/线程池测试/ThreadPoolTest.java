package 线程池.线程池测试;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executor1 = Executors.newCachedThreadPool();  //快
        ExecutorService executor2 = Executors.newFixedThreadPool(10);  //慢
        ExecutorService executor3 = Executors.newSingleThreadExecutor();  //非常慢
        ExecutorService executor4 = Executors.newScheduledThreadPool(10);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10));


        for (int i = 1; i <= 100; i++) {
            threadPoolExecutor.execute(new MyTask(i));
        }
    }
}

class MyTask implements Runnable {
    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "第" + i + "个");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
