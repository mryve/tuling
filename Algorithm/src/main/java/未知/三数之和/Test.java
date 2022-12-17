package 未知.三数之和;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length < 3) {
            return null;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int head = i + 1, tail = length - 1;
            if (nums[i] > 0) {
                break;
            }
            while (head < tail) {
                int sum = nums[i] + nums[head] + nums[tail];
                if (sum < 0) {
                    head++;
                } else if (sum > 0) {
                    tail--;
                } else {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[head]);
                    list.add(nums[tail]);
                    res.add(list);
                    while (head + 1 < length && nums[head] == nums[head + 1]){
                        head++;
                    }
                    while (head + 1 < length && nums[tail] == nums[tail - 1]){
                        tail--;
                    }
                    head++;
                    tail--;
                }
            }
            while (i + 1 < length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }
}
