import static mySort.HeapSort.swap;

public class Test {

    private static volatile Test uniqueTest;
    private Test(){ }
    public static Test getUniqueTest(){
        if(uniqueTest==null){
            synchronized (Test.class){
                if(uniqueTest==null){
                    uniqueTest = new Test();
                }
            }
        }
        return uniqueTest;
    }

    public void quickSort(int[] array){
        quick(array,0,array.length-1);
    }

    private void quick(int[] array, int left, int right) {
        if(array==null ||array.length<=1 || left>right){
            return;
        }
        int mid = partition(array,left,right);
        quick(array,left,mid);
        quick(array, mid+1, right);
    }

    private int partition(int[] array, int left, int right) {
        int temp = array[left];
        while (left<right){
            while (temp<=array[right] && left<right){
                --right;
            }
            if(left<right){
                array[left] = array[right];
                left++;
            }
            while (temp>=array[right] && left<right){
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

    public void mergeSort(int[] array){
        mergeSort(array,0,array.length-1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if(array==null || array.length<=1 || left>=right){
            return;
        }
        int mid = (left+right)/2;
        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array,left,mid,right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right-left+1];
        int i = left;
        int j = mid+1;
        int k=0;
        for(;i<=mid && j<=right;k++){
            if(array[i]<array[j]){
                temp[k] = array[i++];
            }else {
                temp[k] = array[j++];
            }
        }
        while (i<=mid){
            temp[k++] = array[i++];
        }
        while (j<=right){
            temp[k++] = array[j++];
        }
        if (temp.length >= 0) System.arraycopy(temp, 0, array, left, temp.length);
    }

    public void heapSort(int[] array){
        if( array==null||array.length<=1){
            return;
        }
        buildMaxHeap(array);
        for(int i=array.length-1;i>=0;i--){
            swap(array,0,i);
            maxHeap(array,i,0);
        }
    }

    private void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int mid = (array.length-1)/2;
        for(int i =mid;i>=0;i--){
            maxHeap(array,array.length,i);
        }
    }

    private void maxHeap(int[] array, int heapSize, int index) {
        int left = 2*index+1;
        int right = 2*index+2;
        int largest = index;
        if (left < heapSize && array[left] > array[largest]) {
            largest = left;
        }

        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }
        if (index!=largest){
            swap(array,index,largest);
            maxHeap(array,heapSize,largest);
        }
    }

    public void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int h_l = height[0];
        int h_r = height[right];
        int sum = 0;
        while (left < right) {
            if (h_l < h_r) {
                if (height[left + 1] < h_l) {
                    sum += h_l - height[left + 1];
                } else {
                    h_l = height[left + 1];
                }
                left++;
            } else {
                if (height[right - 1] < h_r) {
                    sum += h_r - height[right - 1];
                } else {
                    h_r = height[right - 1];
                }
                right--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

}
