import static mySort.HeapSort.swap;

import java.util.Arrays;

public class Test {

    private static volatile Test uniqueTest;

    private Test() {
    }

    public static Test getUniqueTest() {
        if (uniqueTest == null) {
            synchronized (Test.class) {
                if (uniqueTest == null) {
                    uniqueTest = new Test();
                }
            }
        }
        return uniqueTest;
    }

    public void quickSort(int[] array) {
        quick(array, 0, array.length - 1);
    }

    private void quick(int[] array, int left, int right) {
        if (array == null || array.length <= 1 || left > right) {
            return;
        }
        int mid = partition(array, left, right);
        quick(array, left, mid);
        quick(array, mid + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int temp = array[left];
        while (left < right) {
            while (temp <= array[right] && left < right) {
                --right;
            }
            if (left < right) {
                array[left] = array[right];
                left++;
            }
            while (temp >= array[right] && left < right) {
                left++;
            }
            if (left < right) {
                array[right] = array[left];
                --right;
            }
        }
        array[left] = temp;
        return left;
    }

    public void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (array == null || array.length <= 1 || left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        for (; i <= mid && j <= right; k++) {
            if (array[i] < array[j]) {
                temp[k] = array[i++];
            } else {
                temp[k] = array[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        if (temp.length >= 0) {
            System.arraycopy(temp, 0, array, left, temp.length);
        }
    }

    public void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        buildMaxHeap(array);
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            maxHeap(array, i, 0);
        }
    }

    private void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int mid = (array.length - 1) / 2;
        for (int i = mid; i >= 0; i--) {
            maxHeap(array, array.length, i);
        }
    }

    private void maxHeap(int[] array, int heapSize, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;
        if (left < heapSize && array[left] > array[largest]) {
            largest = left;
        }

        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }
        if (index != largest) {
            swap(array, index, largest);
            maxHeap(array, heapSize, largest);
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

    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int l = 1;
        int r = 1;
        left[0] = l;
        right[nums.length - 1] = r;
        for (int i = 1; i < nums.length; i++) {
            left[i] = l * nums[i - 1];
            l = l * nums[i - 1];
        }
        System.out.println(Arrays.toString(left));
        for (int j = nums.length - 2; j >= 0; j--) {
            right[j] = r * nums[j + 1];
            r = r * nums[j + 1];
        }
        System.out.println(Arrays.toString(right));
        for (int k = 0; k < nums.length; k++) {
            answer[k] = left[k] * right[k];
        }
        return answer;
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int l = gas.length;
        int[] remain = new int[l];
        for (int i = 0; i < l; i++) {
            remain[i] = gas[i] - cost[i];
        }
        int debt = 0;
        int min = Integer.MAX_VALUE;
        int start = 0;
        for (int i = 0; i < l; i++) {
            debt += remain[i];
            if (debt < min && debt < 0) {
                min = debt;
                start = i + 1;
            }
        }
        if (debt >= 0) {
            return start >= l ? 0 : start;
        }
        return -1;
    }

    public static int candy(int[] ratings) {
        int left_base = 1;
        int l = ratings.length;
        int[] left = new int[l];
        for (int i = 0; i < l - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                left[i + 1] = left_base;
                left_base += 1;
            }
            if (ratings[i + 1] == ratings[i]) {
                left_base = 1;
                left[i + 1] = 0;
            }
            if (ratings[i + 1] < ratings[i]) {
                left_base = 1;
                left[i + 1] = 0;
            }
        }
        System.out.println(Arrays.toString(left));
        int right_base = 1;
        int[] right = new int[l];
        for (int i = l - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {
                right[i - 1] = right_base;
                right_base += 1;
            }
            if (ratings[i - 1] == ratings[i]) {
                right_base = 1;
                right[i - 1] = 0;
            }
            if (ratings[i - 1] < ratings[i]) {
                right_base = 1;
                right[i - 1] = 0;
            }
        }
        System.out.println(Arrays.toString(right));
        int sum = 0;
        for (int i = 0; i < l; i++) {
            sum += (Math.max(left[i], right[i]) + 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] ratings = {0,1,2,5,3,2,7};
        System.out.println(candy(ratings));
        int[] ratings1 = {1,2,2};
        System.out.println(candy(ratings1));
    }

}
