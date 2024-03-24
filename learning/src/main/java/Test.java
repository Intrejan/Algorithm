import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>() {{
            this.put("I", 1);
            this.put("V", 5);
            this.put("X", 10);
            this.put("L", 50);
            this.put("C", 100);
            this.put("D", 500);
            this.put("M", 1000);
        }};
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(String.valueOf(s.charAt(i)));
            if (i < s.length() - 1 && value < map.get(String.valueOf(s.charAt(i + 1)))) {
                sum -= value;
            } else {
                sum += value;
            }
        }
        return sum;
    }

    public static String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>() {
            {
                this.put(1, "I");
                this.put(4, "IV");
                this.put(5, "V");
                this.put(9, "IX");
                this.put(10, "X");
                this.put(40, "XL");
                this.put(50, "L");
                this.put(90, "XC");
                this.put(100, "C");
                this.put(400, "CD");
                this.put(500, "D");
                this.put(900, "CM");
                this.put(1000, "M");
            }
        };
        List<Integer> values = map.keySet().stream().sorted(Comparator.reverseOrder()).toList();
        StringBuilder result = new StringBuilder();
        for (int value : values) {
            while (num >= value) {
                result.append(map.get(value));
                num -= value;
            }
        }
        return result.toString();
    }

    public int lengthOfLastWord(String s) {
        int l = 0;
        char[] chars = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                if (l == 0) {
                    continue;
                } else {
                    break;
                }
            }
            l++;
        }
        return l;
    }

    public static String longestCommonPrefix(String[] strs) {
        String base = strs[0];
        int end;
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                end = i;
                String temp = strs[j];
                if (temp.length() <= i) {
                    return base.substring(0, end);
                }
                if (temp.charAt(i) != c) {
                    return base.substring(0, end);
                }
            }
        }
        return strs[0];
    }

    public static String reverseWords(String s) {
        List<String> words = Arrays.stream(s.split(" ")).
            filter(word -> !word.isEmpty()).toList();
        StringBuilder result = new StringBuilder();
        for (int i = words.size() - 1; i >= 0; i--) {
            result.append(words.get(i));
            result.append(" ");
        }
        return result.toString();
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int index = 0;

        char[][] matrix = new char[numRows][s.length()];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < s.length(); j++) {
                matrix[i][j] = ' ';
            }
        }
        while (index < s.length()) {
            for (int j = 0; j < s.length(); j++) {
                for (int i = 0; i < numRows; i++) {
                    if (j % (numRows - 1) == 0) {
                        if (index >= s.length()) {
                            break;
                        }
                        matrix[i][j] = s.charAt(index);
                        index++;
                    } else {
                        if (i + (j % (numRows - 1)) == numRows - 1) {
                            if (index >= s.length()) {
                                break;
                            }
                            matrix[i][j] = s.charAt(index);
                            index++;
                        }
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (matrix[i][j] != ' ') {
                    result.append(matrix[i][j]);
                }
            }
        }
        return result.toString();
    }

    public static int strStr(String haystack, String needle) {
        int index = 0;
        int step = needle.length();
        while (index <= haystack.length() - step) {
            if (haystack.substring(index, index + step).equals(needle)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static List<String> test(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (temp.length() + s.length() > maxWidth) {
                result.add(temp.toString());
                temp = new StringBuilder(s + " ");
            } else {
                temp.append(s).append(" ");
            }
            if (i == words.length - 1) {
                result.add(temp.toString());
            }
        }
        for (int i = 0; i < result.size() - 1; i++) {
            String line = buildLine(result.get(i), maxWidth);
            result.set(i, line);
        }
        String lastLine = buildLine(result.get(result.size() - 1), result.get(result.size() - 1).trim().length());
        if (lastLine.length() < maxWidth) {
            lastLine += " ".repeat(Math.max(0, maxWidth - lastLine.length()));
        }
        result.set(result.size() - 1, lastLine);
        return result;
    }

    private static String buildLine(String line, int maxWidth) {
        String[] words = line.split(" ");
        int sum = 0;
        for (String word : words) {
            sum += word.length();
        }
        int space = maxWidth - sum;
        int gap;
        int average;
        int remain = 0;
        List<String> result = new ArrayList<>();
        if (words.length > 1) {
            gap = words.length - 1;
            average = space / gap;
            remain = space % gap;
            for (int i = 0; i < words.length - 1; i++) {
                result.add(words[i]);
                result.add(" ".repeat(Math.max(0, average)));
            }
            result.add(words[words.length - 1]);
        } else {
            result.add(words[0]);
            result.add(" ".repeat(Math.max(0, space)));
        }
        for (int i = 0; i < result.size(); i++) {
            String c = result.get(i);
            if (c.startsWith(" ")) {
                if (remain == 0) {
                    break;
                }
                result.set(i, c + " ");
                remain--;
            }
        }
        StringBuilder res = new StringBuilder();
        for (String s : result) {
            res.append(s);
        }
        return res.toString();
    }

    public static void main(String[] args) {
//        System.out.println(test(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(test(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16));
//        System.out.println(
//            test(new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain",
//                "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"}, 20));
    }

}
