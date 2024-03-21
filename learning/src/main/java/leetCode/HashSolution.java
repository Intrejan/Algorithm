package leetCode;

import java.util.HashMap;
import java.util.Map;

public class HashSolution {

    /**
     * 和可被 K 整除的子数组
     *
     * @param A 数组
     * @param K K值
     * @return 子数组个数
     */
    public int subarraysDivByK(int[] A, int K) {
        //同余定理
        Map<Integer, Integer> record = new HashMap<>();
        //边界情况，当整个数组和能被K整除时
        record.put(0, 1);
        int sum = 0, answer = 0;
        for (int n : A) {
            sum += n;
            int modulus = (sum % K + K) % K;
            //当Map集合中有这个key时，就使用这个key值，如果没有就使用默认值defaultValue
            int same = record.getOrDefault(modulus, 0);
            answer += same;
            record.put(modulus, same + 1);
        }
        return answer;
    }

    /**
     * 和为 K 的子数组
     *
     * @param nums 数组
     * @param k    k
     * @return 子数组个数
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> record = new HashMap<>();
        int count = 0;
        int partialSum = 0;//前缀和
        //初始化，意味着当整个数组刚好满足条件时，前缀和-k=0的时候，可以的得到1
        record.put(0, 1);
        for (int num : nums) {
            //计算前缀和
            partialSum += num;
            //查看记录中是否有满足和为k的子数组的前缀和记录
            if (record.containsKey(partialSum - k)) {
                count += record.get(partialSum - k);
            }
            //添加记录
            record.put(partialSum, record.getOrDefault(partialSum, 0) + 1);
        }
        return count;
    }


    public static void main(String[] args) {
        HashSolution hashSolution = new HashSolution();
        int[] A = {4, 5, 0, -2, -3, 1};
        System.out.println(hashSolution.subarraysDivByK(A, 5));
    }
}
