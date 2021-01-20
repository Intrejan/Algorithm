package leetCode;


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

    public static void main(String[] args){
        DynamicSolution dynamicSolution = new DynamicSolution();
        System.out.println(dynamicSolution.longestPalindrome2("babab"));
    }
}
