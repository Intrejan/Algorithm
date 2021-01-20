package mySort;

import java.util.Arrays;

public class MergeSort {
    private void mergeSort(int[] array){
        mergeSort(array,0,array.length-1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if(array==null || array.length<=1 || left>=right){
            return;
        }
        int center = (left+right)/2;
        mergeSort(array,left,center);
        mergeSort(array,center+1,right);
        merge(array,left,center,right);
    }

    private void merge(int[] array, int left, int center, int right) {
        int[] tempArray = new int[right-left+1];
        int i = left;
        int j = center+1;
        int k = 0;
        for(;i<= center&& j<=right;k++){
            if(array[i] < array[j]){
                tempArray[k] = array[i++];
            }else{
                tempArray[k] = array[j++];
            }
        }
        while(i <= center)
            tempArray[k++] = array[i++];

        while(j <= right)
            tempArray[k++] = array[j++];

        if (tempArray.length >= 0) System.arraycopy(tempArray, 0, array, left, tempArray.length);
    }

    public static void main(String[] args){
        MergeSort mergeSort = new MergeSort();
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        mergeSort.mergeSort(array);
        System.out.println(Arrays.toString(array));
    }
}
