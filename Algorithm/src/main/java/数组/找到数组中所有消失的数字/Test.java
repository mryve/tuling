package 数组.找到数组中所有消失的数字;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个长度为n-1的递增数组中所有数字都是唯一的, 并且每个数字都在0~n-1之间. 范围0~n-1中有几个数字不在数列中, 找出他们
 * 例: [0,1,2,3,5,6,7,8], 长度为9-1, n为9, 每个数字在0-8之间, 找到数组中缺失的4
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    /**
     * 从第一个元素开始, 数值-1再取模, 找到对应位置的数据+n; 当遍历一遍之后剩下比长度n小的数据坐标+1就是缺失的数字
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = (nums[i] - 1) % n;
            nums[x] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                list.add(i+1);
            }
        }
        return list;
    }
}
