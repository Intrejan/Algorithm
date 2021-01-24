package leetCode;


public class SortSolution {
    /**
     * 使用堆排序寻找第K大的元素
     * @param nums 目标数组
     * @param k k
     * @return 数值
     */
    public int findKthLargest(int[] nums, int k) {
        buildMaxHeap(nums);
        for(int i=nums.length-1;i>nums.length-k;i--){
            swap(nums,0,i);
            maxHeap(nums,i,0);
        }
        return nums[0];
    }

    private void buildMaxHeap(int[] nums) {
        if(nums==null || nums.length<= 1){
            return;
        }
        int center = (nums.length-1)/2;
        for(int i=center;i>=0;i--){
            maxHeap(nums,nums.length,i);
        }
    }

    private void maxHeap(int[] nums, int heapSize, int index) {
        int left = index*2+1;
        int right = index*2+2;
        int largest = index;
        if(left<heapSize && nums[left]>nums[index]){
            largest = left;
        }
        if(right<heapSize && nums[right]>nums[largest]){
            largest = right;
        }
        if(index!=largest){
            swap(nums,index,largest);
            maxHeap(nums,heapSize,largest);
        }
    }

    private void swap(int[] nums, int index, int largest) {
        int temp = nums[index];
        nums[index] = nums[largest];
        nums[largest] = temp;
    }

    /**
     * 使用快速排序思想寻找第K大的元素
     * @param nums 目标数组
     * @param k k值
     * @return 数值
     */
    public int findKthLargestByQuick(int[] nums, int k) {
        quickSort(nums,0,nums.length-1,k);
        return nums[nums.length-k];
    }

    private void quickSort(int[] nums, int left, int right, int k) {
        if(nums==null || left>=right || nums.length<=1){
            return;
        }
        int mid = partition(nums,left,right);
        if(mid<nums.length-k){
            quickSort(nums, mid+1, right, k);
        }else {
            quickSort(nums, left, mid, k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left<right){
            while (temp<=nums[right] && left<right){
                --right;
            }
            if(left<right){
                nums[left] = nums[right];
                left++;
            }
            while (temp>=nums[left] && left<right){
                left++;
            }
            if(left<right){
                nums[right] = nums[left];
                --right;
            }
        }
        nums[left] = temp;
        return left;
    }

    public static void main(String[] args){
        SortSolution sortSolution = new SortSolution();
        System.out.println(sortSolution.findKthLargest(new int[]{2,1,3,4,5,6,7,8},4));
        System.out.println(sortSolution.findKthLargestByQuick(new int[]{2,1,3,4,5,6,7,8},6));
    }
}
