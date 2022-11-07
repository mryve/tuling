package synchronized篇.线程执行顺序测试monitor;

public class MySyncTest {

    Object lock = new Object();

    public void startThreadA() {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("a get lock");
                try {
                    //Thread.sleep(1000);
                    lock.wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("a release lock");
            }
        }, "thread-A").start();
    }
    public void startThreadB() {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("b get lock");
                try {
                    Thread.sleep(1000);
                    //lock.wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("b release lock");
            }
        }, "thread-A").start();
    }
    public void startThreadC() {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("c get lock");
                try {
                    Thread.sleep(1000);
                    //lock.wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("c release lock");
            }
        }, "thread-A").start();
    }
    public void startThreadD() {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("d get lock");
                try {
                    Thread.sleep(1000);
                    //lock.wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("d release lock");
            }
        }, "thread-A").start();
    }
    public void startThreadE() {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("e get lock");
                try {
                    Thread.sleep(1000);
                    //lock.wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("e release lock");
            }
        }, "thread-A").start();
    }

    public static void main(String[] args) throws InterruptedException {
        MySyncTest mySyncTest = new MySyncTest();
        mySyncTest.startThreadA();
        Thread.sleep(100);
        mySyncTest.startThreadB();
        mySyncTest.startThreadC();
        mySyncTest.startThreadD();
        mySyncTest.startThreadE();

    }
}
