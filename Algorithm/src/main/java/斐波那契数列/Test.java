package 斐波那契数列;

import java.util.HashMap;

/**
 * 写一个函数, 输入n, 求斐波那契数列第n项, 斐波那契数列定义如下: f(n)= {0 n=0; 1 n=1; f(n-2)+f(n-1) n>1}
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(get(3));
    }
    /**
     * 思路等同于爬楼梯
     * @param n
     * @return
     */
    public static int get(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (null != map.get(n)) {
            return map.get(n);
        } else {
            int res = get(n-2) + get(n-1);
            map.put(n, res);
            return res;
        }
    }
}
