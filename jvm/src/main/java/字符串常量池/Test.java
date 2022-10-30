package 字符串常量池;

public class Test {
    public static void main(String[] args) {
        basicType();
    }

    public static void checkIntern() {
        String s1 = new String("a") + new String("b"); //int void
        String s2 = s1.intern();
        System.out.println(s1 == s2);
    }

    public static void basicType() {
        Integer i1 = 128;
        Integer i2 = 128;
        System.out.println(i1 == i2);
    }

    public static void twoWays() {
        String s1 = "a" + 1;
        String s2 = new String("a") + new String("1");
        String s3 = "a1";
        System.out.println(s1 == s3);
    }
}
