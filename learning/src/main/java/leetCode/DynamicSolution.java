package leetCode;


import java.util.*;

public class DynamicSolution {

    public String longestPalindrome(String s) {
        int len = s.length();
        StringBuilder str = new StringBuilder();
        for(int i=0;i < len;i++){
            str.append('#');
            str.append(s.charAt(i));
        }
        str.append('#');
        int maxRadius = 0;
        int index = 0;
        for(int i=0;i<str.length();i++){
            int radius = countRadius(str.toString().toCharArray(),i);
            if(maxRadius<radius){
                maxRadius = radius;
                index = i;
            }
        }
        return s.substring((index-maxRadius)/2,(index-maxRadius)/2+maxRadius);
    }

    /**
     * 计算扩散半径
     * @param array 数组
     * @param i 下标
     * @return 扩散半径
     */
    private Integer countRadius(char[] array, int i) {
        int radius = 0;
        int l=i-1;
        int r=i+1;
        while(l>=0 && r<=array.length-1){
            if(array[l]!=array[r]){
                break;
            }else {
                ++radius;
            }
            --l;
            ++r;
        }
        return radius;
    }

    /**
     * 使用动态规划
     * @param s 字符串
     * @return 最长回文子串
     */
    public String longestPalindrome2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else {
                    boolean b = s.charAt(i) == s.charAt(j);
                    if (l == 1) {
                        dp[i][j] = b;
                    } else {
                        dp[i][j] = (b && dp[i + 1][j - 1]);
                    }
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }

    /**
     * 动态规划求解
     * @param nums 输入数组
     * @return 最大子串和
     */
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int partialSum =0;
        for(int num:nums){
            partialSum = Math.max(partialSum+num,num);
            maxSum = Math.max(partialSum,maxSum);
        }
        return maxSum;
    }

    public int trap(int[] height) {
        int len = height.length;
        if(len <1){
            return 0;
        }
        int max_l = height[0];
        int max_r = height[len-1];
        int[] l = new int[len];
        int[] r = new int[len];
        for(int i=0;i<len;i++){
            if(height[i]>max_l){
                max_l = height[i];
            }
            l[i] = max_l;
        }
        for(int j=len-1;j>=0;j--){
            if(height[j]>max_r){
                max_r = height[j];
            }
            r[j] = max_r;
        }
        int sum = 0;
        for(int i =0;i<len;i++){
            l[i] = Math.min(l[i],r[i]);
            sum+=(l[i] -height[i]);
        }
        return sum;
    }

    public int trapByStack(int[] height) {
        int ans = 0, current = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }

    public int trapByTwoPointer(int[] height){
        int result = 0;
        int left = 0;
        int right = height.length-1;
        int max_l = 0;
        int max_r = 0;
        while (left<right){
            if(height[left]<height[right]){
                if(height[left]>max_l){
                    max_l = height[left];
                }else {
                    result+=(max_l-height[left]);
                }
                ++left;
            }else {
                if(height[right]>max_r){
                    max_r = height[right];
                }else {
                    result+=(max_r-height[right]);
                }
                --right;
            }
        }
        return result;
    }

    public int maxScoreSightseeingPair(int[] A) {
        int max = A[0];
        int result = 0;
        for(int i=1;i<A.length;i++){
            if(max+A[i]-i>result){
                result = max+A[i]-i;
            }
            if(A[i]+i>max){
                max = A[i]+i;
            }
        }
        return  result;
    }

    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else {
                if (price - min > max) {
                    max = price - min;
                }
            }
        }
        return max;
    }



    public static void main(String[] args){
        DynamicSolution dynamicSolution = new DynamicSolution();
//        System.out.println(dynamicSolution.longestPalindrome2("babab"));
    }
}
