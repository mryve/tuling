package 两数之和;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个数组nums和一个目标值target, 找出nums中两个相加等于target值得数的下标
 * 例:  nums:[2,7,11,15]    target=9       =>    [0,1]
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    /**
     * 思路: 如果用暴力解法效率会非常低, 我们可以创建一个HashMap用来存放扫描过的值以及下标, 当我们开始便利值时可以直接在map中查看是否有匹配的值.
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.get(num) != null) {
                res[1] = map.get(num);
                res[0] = i;
                return res;
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

}
