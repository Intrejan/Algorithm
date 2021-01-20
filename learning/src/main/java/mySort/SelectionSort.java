package mySort;

import java.util.Arrays;

public class SelectionSort {
    private void selectionSort(int[] array){
        if(array == null || array.length<=1){
            return;
        }
        int length = array.length;

        for(int i=0;i<length-1;i++){
            //保存最小数的索引
            int minIndex = i;

            for(int j=i+1;j<length;j++){
                //找到最小数
                if(array[j]<array[minIndex]){
                    minIndex = j;
                }
            }
            //交换位置
            if(i !=minIndex){
                swap(array,minIndex,i);
            }
        }
    }

    private void swap(int[] array, int a , int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args){
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

        SelectionSort selectionSort = new SelectionSort();
        selectionSort.selectionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
