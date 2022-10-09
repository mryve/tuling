package JVM内存模型;

// JVM设置  -Xss128k(默认1M)
public class StackOverflowTest {

    static int count = 0;

    static void redo() {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(count);
        }
    }
}

//运行结果：
//        java.lang.StackOverflowError
//        at com.tuling.jvm.StackOverflowTest.redo(StackOverflowTest.java:12)
//        at com.tuling.jvm.StackOverflowTest.redo(StackOverflowTest.java:13)
//        at com.tuling.jvm.StackOverflowTest.redo(StackOverflowTest.java:13)
//        ......