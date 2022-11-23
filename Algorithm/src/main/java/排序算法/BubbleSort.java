package 排序算法;


import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0,0};
        //SelectSort.selectSort(arr);
        QuickSort.quickSort(arr, 0, arr.length-1);
        //InsertSort.insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void bubbleSort(int[] arr) {
        for (int j = 0; j <arr.length; j ++) {
            for (int i = 0; i < arr.length -1- j; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }
}
