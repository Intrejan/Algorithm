package leetCode;


import java.util.*;

public class DynamicSolution {

    /**
     * 最长回文子串
     *
     * @param s 字符串
     * @return 子串
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str.append('#');
            str.append(s.charAt(i));
        }
        str.append('#');
        int maxRadius = 0;
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            int radius = countRadius(str.toString().toCharArray(), i);
            if (maxRadius < radius) {
                maxRadius = radius;
                index = i;
            }
        }
        return s.substring((index - maxRadius) / 2, (index - maxRadius) / 2 + maxRadius);
    }

    /**
     * 计算扩散半径
     *
     * @param array 数组
     * @param i     下标
     * @return 扩散半径
     */
    private Integer countRadius(char[] array, int i) {
        int radius = 0;
        int l = i - 1;
        int r = i + 1;
        while (l >= 0 && r <= array.length - 1) {
            if (array[l] != array[r]) {
                break;
            } else {
                ++radius;
            }
            --l;
            ++r;
        }
        return radius;
    }

    /**
     * 使用动态规划
     *
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
     *
     * @param nums 输入数组
     * @return 最大子串和
     */
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int partialSum = 0;
        for (int num : nums) {
            partialSum = Math.max(partialSum + num, num);
            maxSum = Math.max(partialSum, maxSum);
        }
        return maxSum;
    }

    /**
     * 接雨水
     *
     * @param height 高度数组
     * @return 雨水值
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len < 1) {
            return 0;
        }
        int max_l = height[0];
        int max_r = height[len - 1];
        int[] l = new int[len];
        int[] r = new int[len];
        for (int i = 0; i < len; i++) {
            if (height[i] > max_l) {
                max_l = height[i];
            }
            l[i] = max_l;
        }
        for (int j = len - 1; j >= 0; j--) {
            if (height[j] > max_r) {
                max_r = height[j];
            }
            r[j] = max_r;
        }
        int sum = 0;
        for (int i = 0; i < len; i++) {
            l[i] = Math.min(l[i], r[i]);
            sum += (l[i] - height[i]);
        }
        return sum;
    }

    /**
     * 使用栈解答
     */
    public int trapByStack(int[] height) {
        int ans = 0, current = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }

    /**
     * 使用双指针解答
     */
    public int trapByTwoPointer(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        int max_l = 0;
        int max_r = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > max_l) {
                    max_l = height[left];
                } else {
                    result += (max_l - height[left]);
                }
                ++left;
            } else {
                if (height[right] > max_r) {
                    max_r = height[right];
                } else {
                    result += (max_r - height[right]);
                }
                --right;
            }
        }
        return result;
    }

    /**
     * 最佳观光组合
     *
     * @param A 评分数组
     * @return 最佳组合
     */
    public int maxScoreSightseeingPair(int[] A) {
        int max = A[0];
        int result = 0;
        for (int i = 1; i < A.length; i++) {
            if (max + A[i] - i > result) {
                result = max + A[i] - i;
            }
            if (A[i] + i > max) {
                max = A[i] + i;
            }
        }
        return result;
    }

    /**
     * 买卖股票的最佳时机
     *
     * @param prices 价格数组
     * @return 最佳时机
     */
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

    /**
     * 单词拆分
     *
     * @param s        字符串
     * @param wordDict 字典
     * @return 能否拆分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.isEmpty()) {
            return false;
        }
        //计算出单词的最长长度和最短长度
        int min = wordDict.get(0).length();
        int max = wordDict.get(0).length();
        for (String word : wordDict) {
            if (word.length() < min) {
                min = word.length();
            }
            if (word.length() > max) {
                max = word.length();
            }
        }
        //将字典转化为集合
        Set<String> set = new HashSet<>(wordDict);
        //每个位置是否满足
        boolean[] booleans = new boolean[s.length() + 1];
        booleans[0] = true;
        for (int i = 0; i <= s.length() - min; i++) {
            for (int j = i + min; j <= i + max && j <= s.length(); j++) {
                if (set.contains(s.substring(i, j)) && booleans[i]) {
                    booleans[j] = true;
                }
            }
        }
        return booleans[s.length()];
    }

    /**
     * 爬楼梯（滚动数组）
     *
     * @param n 阶数
     * @return 不同爬法
     */
    public int climbStairs(int n) {
        int p = 0;
        int q = 0;
        int r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    public static void main(String[] args) {
        DynamicSolution dynamicSolution = new DynamicSolution();
//        System.out.println(dynamicSolution.longestPalindrome2("babab"));
//        dynamicSolution.wordBreak("applepenapple",Arrays.asList("apple", "pe"));
    }
}
