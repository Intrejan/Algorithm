package leetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class StackSolution {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for (Character c : s.toCharArray()) {
            if (!stack.empty() && map.get(c) == stack.peek()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }

    public static String simplifyPath(String path) {
        String[] paths = path.split("/");
        StringBuilder result = new StringBuilder();
        Stack<String> stack = new Stack<>();
        for (String p : paths) {
            if (p.isEmpty()) {
                continue;
            }
            if (p.equals(".")) {
                continue;
            }
            if (p.equals("..")) {
                if (!stack.empty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(p);
        }
        if (stack.empty()) {
            stack.push("");
        }
        while (!stack.empty()) {
            String s = stack.pop();
            result.insert(0, "/" + s);
        }
        return result.toString();
    }

    public static int evalRPN(String[] tokens) {
        List<String> signs = List.of("+", "-", "*", "/");
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (signs.contains(token)) {
                int v1 = stack.pop();
                int v2 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(v1 + v2);
                        break;
                    case "-":
                        stack.push(v2 - v1);
                        break;
                    case "*":
                        stack.push(v2 * v1);
                        break;
                    case "/":
                        stack.push(v2 / v1);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }

    public static int calculate(String s) {
        Stack<String> stack = new Stack<>();
        s = s.replaceAll(" ", "");
        s = "(" + s + ")";
        int i = 0;
        while (i < s.length()) {
            Character c = s.charAt(i);
            if (c.equals('(')) {
                stack.push(String.valueOf(c));
            } else if (c.equals(')')) {
                int sum = 0;
                while (!stack.peek().equals("(")) {
                    String v = stack.pop();
                    int value = Integer.parseInt(v);
                    if (stack.peek().equals("-")) {
                        value = -value;
                        stack.pop();
                    }
                    sum += value;
                }
                stack.pop();
                stack.push(String.valueOf(sum));
            } else {
                if (c.equals('-')) {
                    if (s.charAt(i + 1) == '(') {
                        stack.push(c + "");
                        i++;
                        continue;
                    }
                }
                if (c.equals('+')) {
                    if (s.charAt(i + 1) == '(') {
                        i++;
                        continue;
                    }
                }
                int next = i + 1;
                while (next < s.length() && Character.isDigit(s.charAt(next))) {
                    next++;
                }
                String sub = s.substring(i, next);
                stack.push(sub);
                i = next;
                continue;
            }
            i++;
        }
        return Integer.parseInt(stack.peek());
    }


    public static void main(String[] args) {
        System.out.println(calculate("(7)-(0)+(4)"));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }

}
