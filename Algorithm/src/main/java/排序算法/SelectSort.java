package 排序算法;

public class SelectSort {
    public static void selectSort(int[] arr) {
        for (int j = 0; j < arr.length - 1; j++) {
            int minIndex = j;
            int min = arr[j];
            for (int i = j + 1; i < arr.length; i++) {
                if (min > arr[i]) {
                    min = arr[i];
                    minIndex = i;
                }
            }
            arr[minIndex] = arr[j];
            arr[j] = min;
        }

    }
}
