package mySort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] array = { 9, -8, 7, -6, 5, 5, 3, 2, 1, 0, 11, -2, -3 };

        heapSort(array);

    }

    public static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        buildMaxHeap(array);
        //System.out.println(Arrays.toString(array));
        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i);
            maxHeap(array, i, 0);
            //System.out.println(Arrays.toString(array));
        }
    }

    private static void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int half = (array.length-1) / 2;
        for (int i = half; i >= 0; i--) {
            maxHeap(array, array.length, i);
        }
    }

    private static void maxHeap(int[] array, int heapSize, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int largest = index;
        if (left < heapSize && array[left] > array[largest]) {
            largest = left;
        }

        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }

        if (index != largest) {
            //System.out.println("A:"+Arrays.toString(array));
            swap(array, index, largest);
            //System.out.println("B:"+Arrays.toString(array));
            maxHeap(array, heapSize, largest);
        }
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
