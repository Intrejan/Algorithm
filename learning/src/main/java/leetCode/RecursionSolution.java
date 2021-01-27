package leetCode;

import java.util.*;

public class RecursionSolution {

    /**
     * 二叉树中的最大路径和
     */
    int sum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return sum;
    }

    private int dfs(TreeNode root) {
        if(root == null){return 0;}
        int left_value = Math.max(0,dfs(root.left));
        int right_value = Math.max(0,dfs(root.right));
        int value = root.val+left_value+right_value;
        sum = Math.max(sum,value);
        return root.val+Math.max(left_value,right_value);
    }

    /**
     * 括号生成
     * @param n 括号数目
     * @return 结果
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        Deque<Character> stack = new ArrayDeque<>();
        dfsAdd(0,0,stack,result,n);
        return result;
    }

    private void dfsAdd(int left, int right, Deque<Character> stack, List<String> result,int size) {
        if(left==size && right==size){
            List<Character> characters = new ArrayList<>(stack);
            StringBuilder s= new StringBuilder();
            for(char c:characters){
                s.append(c);
            }
            result.add(s.toString());
            return;
        }
        if(right<size){
            stack.push(')');
            dfsAdd(left,right+1,stack,result,size);
            stack.pop();
        }
        if(right>left){
            stack.push('(');
            dfsAdd(left+1,right,stack,result,size);
            stack.pop();
        }
    }

    /**
     * 字符串编码
     */
    String result;
    int index;
    public String decodeString(String s) {
        result = s;
        index = 0;
        return getString();
    }

    public String getString() {
        if (index == result.length() || result.charAt(index) == ']') {
            return "";
        }
        char cur = result.charAt(index);
        int repTime = 1;
        StringBuilder temp = new StringBuilder();

        if (Character.isDigit(cur)) {
            // 解析 Digits
            repTime = getDigits();
            // 过滤左括号
            ++index;
            // 解析 String
            String str = getString();
            // 构造字符串
            for(int i=0;i<repTime;i++){
                temp.append(str);
            }
            // 过滤右括号
            ++index;
        } else if (Character.isLetter(cur)) {
            // 解析 Char
            temp = new StringBuilder(String.valueOf(result.charAt(index++)));
        }

        return temp + getString();
    }

    public int getDigits() {
        int num;
        StringBuilder s = new StringBuilder();
        while (Character.isDigit(result.charAt(index))){
            s.append(result.charAt(index));
            index++;
        }
        num = Integer.parseInt(s.toString());
        return num;
    }

    /**
     * 最近公共父节点
     */
    List<TreeNode> path_p;
    List<TreeNode> path_q;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        dfs(root,stack,p,q);
//        for(TreeNode treeNode:path_p){
//            System.out.print(treeNode.val);
//        }
//        System.out.println();
//        for(TreeNode treeNode:path_q){
//            System.out.print(treeNode.val);
//        }
//        System.out.println();
        TreeNode result = null;
        if(path_q.size()>path_p.size()){
            for(int i=0;i<path_p.size();i++){
                if(path_p.get(i).val==path_q.get(i).val){
                    result = path_p.get(i);
                }
            }
        }else {
            for(int i=0;i<path_q.size();i++){
                if(path_q.get(i).val==path_p.get(i).val){
                    result = path_q.get(i);
                }
            }
        }
        return result;
    }

    private void dfs(TreeNode root, Deque<TreeNode> stack,TreeNode p,TreeNode q) {
        if(root==null){
            return;
        }
        if(root.val==p.val){
            stack.push(root);
            path_p = new ArrayList<>(stack);
            Collections.reverse(path_p);
            stack.pop();
        }if(root.val==q.val){
            stack.push(root);
            path_q = new ArrayList<>(stack);
            Collections.reverse(path_q);
            stack.pop();
        }
        stack.push(root);
        if(root.left!=null){
            dfs(root.left,stack,p,q);
        }
        if(root.right!=null){
            dfs(root.right,stack,p,q);
        }
        stack.pop();
    }

    /**
     * 括号是否符合规范
     * @param s 字符串
     * @return true or false
     */
    public boolean isValid(String s) {
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Deque<Character> stack = new ArrayDeque<>();
        for(int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if(stack.isEmpty() && !map.containsKey(c)){
                return false;
            }
            if(map.containsKey(c)){
                stack.push(c);
            }
            else if(!stack.isEmpty() && c==map.get(stack.peek())){
                stack.pop();
            }else if(!stack.isEmpty() && c!=map.get(stack.peek())){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        RecursionSolution recursionSolution = new RecursionSolution();
        //System.out.println(recursionSolution.decodeString("3[a]2[bc]"));
        TreeNode root = new TreeNode(3);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(6);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(0);
        TreeNode e = new TreeNode(8);
        TreeNode f = new TreeNode(7);
        root.left = p;
        root.right = a;
        p.left = b;
        p.right = c;
        c.left = f;
        c.right = q;
        a.left = d;
        a.right = e;
        //System.out.println(recursionSolution.lowestCommonAncestor(root,c,a).val);
        System.out.println(recursionSolution.isValid("([{}])"));
    }
}
