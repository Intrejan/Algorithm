package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> dist = new HashMap<>();
        for (Character c : magazine.toCharArray()) {
            dist.put(c, dist.getOrDefault(c, 0) + 1);
        }
        for (Character c : ransomNote.toCharArray()) {
            if (dist.getOrDefault(c, 0) == 0) {
                return false;
            }
            dist.put(c, dist.get(c) - 1);
        }
        return true;
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else if (map.containsValue(c2)) {
                for (Map.Entry<Character, Character> entry : map.entrySet()) {
                    if (entry.getValue().equals(c2) && !entry.getKey().equals(c1)) {
                        return false;
                    }
                }
            } else {
                map.put(c1, c2);
            }
        }
        return true;
    }

    public static boolean wordPattern(String pattern, String s) {
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (map1.containsKey(c)) {
                if (!map1.get(c).equals(word)) {
                    return false;
                }
            } else if (map2.containsKey(word)) {
                if (!map2.get(word).equals(c)) {
                    return false;
                }
            } else {
                map1.put(c, word);
                map2.put(word, c);
            }
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int j = 0; j < t.length(); j++) {
            char c = t.charAt(j);
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }
        return map.isEmpty();
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs) {
            String key = buildKey(s);
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
            } else {
                map.get(key).add(s);
            }
        }
        return map.values().stream().toList();
    }

    private static String buildKey(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int position = c - 'a';
            count[position]++;
        }
        return Arrays.toString(count);
    }

    public static void main(String[] args) {
        HashSolution hashSolution = new HashSolution();
        int[] A = {4, 5, 0, -2, -3, 1};
        System.out.println(hashSolution.subarraysDivByK(A, 5));
    }
}
