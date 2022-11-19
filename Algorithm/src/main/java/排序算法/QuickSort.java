package 排序算法;

public class QuickSort {
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            return;
        }
        int left = start;
        int right = end;
        int pivot = arr[left];

        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }
            if (left == right) {
                arr[left] = pivot;
            }
        }
        quickSort(arr, start, right - 1);
        quickSort(arr, left + 1, end);
    }
}
