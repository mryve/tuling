package 排序算法;

import java.util.HashMap;

public class InsertSort {
    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int preIndex = i;
            int cur = arr[i + 1];
            while (preIndex >= 0 && cur < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = cur;
        }

        new HashMap<>().put("123", "1");
    }



}
