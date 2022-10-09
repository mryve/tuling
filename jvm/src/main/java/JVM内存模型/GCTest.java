package JVM内存模型;

//添加运行JVM参数： -XX:+PrintGCDetails
//设置大对象大小: -XX:PretenureSizeThreshold=1000 -XX:+UseSerialGC

//知识点:
// 1. Eden区放不下时会进行minor gc
// 2. Survivor区放不下是会直接转移到老年代
// 3. 大对象会直接进入老年代
public class GCTest {
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        //allocation1 = new byte[60000*1024];
//
        allocation2 = new byte[8000*1024];
//
//      allocation3 = new byte[1000*1024];
//     allocation4 = new byte[1000*1024];
//     allocation5 = new byte[1000*1024];
//     allocation6 = new byte[1000*1024];

    }
}