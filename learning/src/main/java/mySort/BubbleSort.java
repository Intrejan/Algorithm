package mySort;

import java.util.Arrays;

public class BubbleSort {
    private void bubbleSort(int[] array){
        if(array == null || array.length <= 1){
            return;
        }
        int length = array.length;
        for(int i=0;i<length;i++){
            for(int j = 0;j < length-1-i;j++){
                // 前面的数大于后面的数就进行交换
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args){
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
