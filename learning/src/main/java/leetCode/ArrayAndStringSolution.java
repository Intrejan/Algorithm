package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static java.lang.Character.getNumericValue;

/**
 * 数组与字符串
 */
public class ArrayAndStringSolution {

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<Character>();
        //左指针
        int left = 0;
        //右指针
        int right = -1;
        char[] array = s.toCharArray();
        int length = s.length();
        int maxLength = 0;
        for(;left<length;left++){
            if(left!=0) {
                set.remove(array[left-1]);
            }
            while(right< length-1 && !set.contains(array[right+1])){
                set.add(array[right+1]);
                right++;
            }
            maxLength = Math.max(maxLength,right-left+1);
        }
        return maxLength;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] nums = new int[nums1.length+nums2.length];
        //类似归并算法合并两个有序数组
        for(;i<nums1.length && j<nums2.length;k++){
            if(nums1[i] <= nums2[j]){
                nums[k] = nums1[i++];
            }
            else{
                nums[k] = nums2[j++];
            }
        }
        while (i<nums1.length){
            nums[k++] = nums1[i++];
        }
        while (j<nums2.length){
            nums[k++] = nums2[j++];
        }
        if(nums.length%2 != 0){
            return nums[nums.length/2];
        }else{
            return (double) (nums[nums.length/2-1]+nums[nums.length/2])/2;
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        //移动第一个指针i
        for(int i=0;i<nums.length;i++){
            //跳过重复的元素
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            //初始化第三个指针k在最右端
            int k = nums.length-1;
            int sum = -nums[i];
            //移动第二个指针j
            for(int j = i+1;j<nums.length;j++){
                //同样跳过重复元素
                if(j>i+1 && nums[j]==nums[j-1]){
                    continue;
                }
                //移动第三个指针k直到相遇
                while(j<k && nums[j]+nums[k]>sum){
                    --k;
                }
                //相遇时仍不满足，直接结束
                if(j==k){
                    break;
                }
                //满足条件加入list
                if(nums[j]+nums[k]==sum){
                    List<Integer> oneAnswer = new ArrayList<Integer>();
                    oneAnswer.add(nums[i]);
                    oneAnswer.add(nums[j]);
                    oneAnswer.add(nums[k]);
                    answer.add(oneAnswer);
                }
            }
        }
        return answer;
    }

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        int[] left = new int[length];
        int[] right = new int[length];
        int L = 1,R = 1;
        for(int i=0;i<length;i++){
            left[i] = L;
            L = L*nums[i];
        }
        for(int j=length-1;j>=0;j--){
            right[j] = R;
            R = R * nums[j];
        }
        for(int k=0;k<length;k++){
            result[k] = left[k]*right[k];
        }
        return result;
    }

    public String addStrings(String num1, String num2) {
        char[] nums1 = num1.toCharArray();
        char[] nums2 = num2.toCharArray();
        StringBuilder result = new StringBuilder();
        int i=nums1.length-1,j=nums2.length-1;
        boolean flag = false;
        while(i>=0 || j>=0){
            int sum;
            int a = i>=0?getNumericValue(nums1[i]):0;
            int b = j>=0?getNumericValue(nums2[j]):0;
            if(flag){
                sum = a+b+1;
            }else {
                sum = a+b;
            }
            if(sum>=10){
                flag = true;
                result.append(sum-10);
            }else {
                flag = false;
                result.append(sum);
            }
            i--;
            j--;
        }
        if(flag){
            result.append(1);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args){
        ArrayAndStringSolution solution = new ArrayAndStringSolution();
//        System.out.println(solution.addStrings("1234","9545"));
//        int[] nums1 = {1,2,4};
//        int[] nums2 = {3,5,7};
//        System.out.println(solution.findMedianSortedArrays(nums1,nums2));
//        System.out.println(solution.lengthOfLongestSubstring("asdgadfgf"));
    }
}
