package 排序算法;

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
    }

}
