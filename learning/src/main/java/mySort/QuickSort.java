package mySort;

import java.util.Arrays;

public class QuickSort {
    private void quickSort(int[] array){
        quickSort(array,0,array.length-1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (array == null || left >= right || array.length <= 1) {
            return;
        }
        int mid = partition(array,left,right);
        quickSort(array, left, mid);
        quickSort(array, mid+1, right);
    }

    private int partition(int[] array, int left, int right) {
        int temp = array[left];

        while(left<right){
            while (temp<= array[right] && left<right){
                --right;
            }
            if(left<right) {
                array[left] = array[right];
                left++;
            }
            while(temp>= array[left] && left<right){
                left++;
            }
            if(left<right){
                array[right] = array[left];
                --right;
            }
        }
        array[left] = temp;
        return left;
    }

    public static void main(String[] args){
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
