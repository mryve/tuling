package 合并两个有序数组;

import java.util.Arrays;

/**
 * 给定两个非递减的数组, 另有两个整数m,n, 分别为num1和num2里元素数目, 写一个函数使两个数组合并并保持有序
 * 例:  num1:[1,2,3,0,0,0]  num2:[2,5,6]  m=3  n=3       =>    num1:[1,2,2,3,5,6]
 * 注意: 合并后的数组不应由函数返回, 而是储存在num1中
 */
public class Test {
    public static void main(String[] args) {
        int[] num1 = new int[]{1,2,3,0,0,0};
        int[] num2 = new int[]{2,5,6};
        int m = 3;
        int n = 3;
        System.out.println(Arrays.toString(merge(num1, m, num2, n)));
    }

    /**
     * 思路: 因为num1的后位全是0, 所以我们可以从后往前添加, 对比num1和num2的末尾, 谁大放谁
     * @param num1
     * @param m
     * @param num2
     * @param n
     * @return
     */
    public static int[] merge(int[] num1, int m, int[] num2, int n) {
        int k = m + n;
        int num1Index = m-1;
        int num2Index = n-1;
        for (int i = k - 1; i >= 0; i--) {
            if (num1Index < 0) {    //num1已经取完, 直接放num2
                num1[i] = num2[num2Index--];
            } else if (num2Index < 0) {     //num2已经取完, 直接放num1
                num1[i] = num1[num1Index--];
            } else if (num1[num1Index] < num2[num2Index]) {
                num1[i] = num2[num2Index--];
            } else {
                num1[i] = num1[num1Index--];
            }
        }
        return num1;
    }

}
