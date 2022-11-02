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
        System.out.println(Arrays.toString(merge2(num1, m, num2, n)));
    }

    /**
     * 思路: 因为num1的后位全是0, 所以我们可以从后往前添加, 对比num1和num2的末尾, 谁大放谁
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * @return
     */
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        int num1Index = m-1;
        int num2Index = n-1;
        for (int i = k - 1; i >= 0; i--) {
            if (num1Index < 0) {    //num1已经取完, 直接放num2
                nums1[i] = nums2[num2Index--];
            } else if (num2Index < 0) {     //num2已经取完, 直接放num1
                nums1[i] = nums1[num1Index--];
            } else if (nums1[num1Index] < nums2[num2Index]) {
                nums1[i] = nums2[num2Index--];
            } else {
                nums1[i] = nums1[num1Index--];
            }
        }
        return nums1;
    }

    public static int[] merge2(int[] nums1, int m, int[] nums2, int n) {
        int nums1Index = m-1;
        int nums2Index = n-1;

        for (int numIndex = m + n - 1; numIndex >= 0; numIndex--) {
            if (nums1Index < 0) {
                nums1[numIndex] = nums2[nums2Index--];
            } else if (nums2Index < 0) {
                nums1[numIndex] = nums1[nums1Index--];
            } else if (nums1[nums1Index] < nums2[nums2Index]) {
                nums1[numIndex] = nums2[nums2Index--];
            } else {
                nums1[numIndex] = nums1[nums1Index--];
            }
        }
        return nums1;
    }
}
