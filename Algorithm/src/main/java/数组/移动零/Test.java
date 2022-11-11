package 数组.移动零;

import java.util.Arrays;

/**
 * 给定一个数组nums, 编写一个函数, 将数组里的0全部转移到数组末尾
 * 例: [0,1,0,3,12]    =>   [1,3,12,0,0]
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveZeroes(new int[]{0, 1, 0, 3, 12})));
    }

    /**
     * 思路: 创建两个指针slow和fast, fast进行遍历, 当nums[fast]不为0时, slow进行修改; 当fast为0时, slow不动, fast继续向前; 当数组遍历完成后, slow以后全设置为0
     * @param nums
     * @return
     */
    public static int[] moveZeroes(int[] nums) {
        int slow = 0, fast =0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }
}
