package 爬楼梯;

import java.util.HashMap;

/**
 * 假设有n个楼梯, 你每次可以爬一层或者两层, 你有多少种方法可以爬到楼顶
 * 例:   输入2   =>  2;       输入3  =>  3
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }
    /**
     * 思路: 采用递归进行解题, 已知当只剩一层时有一种方法, 只剩两层时有两种方法, 有n层时就有climbStairs(n-1) + climbStairs(n-2)种方法
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        HashMap<Integer, Integer> storeMap = new HashMap<>();
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (null != storeMap.get(n)) {
            return storeMap.get(n);
        } else {
            int result = climbStairs(n-1) + climbStairs(n-2);
            storeMap.put(n, result);
            return result;
        }
    }
    public static int climbStairs2(int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int result = 0;
        int pre = 2;
        int prePre = 1;
        for (int i = 3; i <= target; ++i) {
            result = pre + prePre;
            prePre = pre;
            pre = result;
        }
        return result;
    }
}
