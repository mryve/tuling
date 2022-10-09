package JVM类加载机制;

/**
 * 测试类加载--初始化
 * 类加载的顺序: 读取-验证-准备-解析-初始化
 */
public class TestDynamicLoad {

    static {
        System.out.println("*************load JVM类加载机制.TestDynamicLoad************");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("*************load test************");
        B b = null;  //B不会加载，除非这里执行 new JVM类加载机制.B()
    }
}

class A {
    static {
        System.out.println("*************load JVM类加载机制.A************");
    }

    public A() {
        System.out.println("*************initial JVM类加载机制.A************");
    }
}

class B {
    static {
        System.out.println("*************load JVM类加载机制.B************");
    }

    public B() {
        System.out.println("*************initial JVM类加载机制.B************");
    }
}

//运行结果：
//        *************load JVM类加载机制.TestDynamicLoad************
//        *************load JVM类加载机制.A************
//        *************initial JVM类加载机制.A************
//        *************load test************