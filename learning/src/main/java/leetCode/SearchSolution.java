package leetCode;


import java.lang.reflect.Array;
import java.util.*;

public class SearchSolution {
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<>();
        if(root==null){
            return new ArrayList<>();
        }
        int max_depth = -1;
        //节点栈
        Stack<TreeNode> nodeStack = new Stack<>();
        //最大深度栈
        Stack<Integer> depthStack = new Stack<>();
        nodeStack.push(root);
        depthStack.push(0);
        while(!nodeStack.isEmpty()){
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();
            if(node!=null){
                max_depth = Math.max(max_depth,depth);

                if(!map.containsKey(depth)){
                    map.put(depth,node.val);
                }
                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth+1);
                depthStack.push(depth+1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<=max_depth;i++){
            result.add(map.get(i));
        }
        return result;
    }

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length==0){
            return result;
        }
        dfs(nums,len,0,path,used,result);
        return result;
    }

    private void dfs(int[] nums, int len,int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> result) {
        if(depth==len){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0; i<len; i++){
            if(used[i]){
                continue;
            }
            path.push(nums[i]);
            used[i] = true;
            dfs(nums,len,depth+1, path, used, result);
            path.pop();
            used[i] = false;
        }
    }

    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len==0){
            return -1;
        }
        if(len==1) {
            return target==nums[0]?0:-1;
        }
        int left = 0;
        int right = len-1;

        while (left<=right){
            int mid = (left+right)/2;
            if(target==nums[mid]){
                return mid;
            }
            if(nums[0]<=nums[mid]){
                if(target>=nums[0] && target<nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else{
                if( target>nums[mid] && target <= nums[len-1]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    public boolean exist(char[][] board, String word) {
        int depth = board.length, width = board[0].length;
        boolean[][] used = new boolean[depth][width];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                boolean flag = check(board, used, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] used, int row , int col, String s, int time) {
        if (board[row][col] != s.charAt(time)) {
            return false;
        } else if (time == s.length() - 1) {
            return true;
        }
        used[row][col] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int i = row + dir[0], j = col + dir[1];
            if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
                if (!used[i][j]) {
                    boolean flag = check(board, used, i, j, s, time + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        used[row][col] = false;
        return result;
    }

    public String reSerialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += root.val + ",";
            str = reSerialize(root.left, str);
            str = reSerialize(root.right, str);
        }
        return str;
    }

    public String serialize(TreeNode root) {
        return reSerialize(root, "");
    }

    public TreeNode reDeserialize(List<String> l) {
        if (l.get(0).equals("None")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(l.get(0)));
        l.remove(0);
        root.left = reDeserialize(l);
        root.right = reDeserialize(l);
        return root;
    }

    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
        System.out.println(data_list);
        return reDeserialize(data_list);
    }

    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int right = arr[arr.length-1];
        int[] pre = new int[arr.length+1];
        for(int i=1;i<pre.length;i++){
            pre[i] = pre[i-1]+arr[i-1];
        }
        int diff = target;
        int answer = 0;
        for(int i=1;i<=right;i++){
            int index = Arrays.binarySearch(arr,i);
            if(index<0){
                index = -index-1;
            }
            int cur = pre[index]+(arr.length-index)*i;
            if(Math.abs(cur-target)<diff){
                answer = i;
                diff = Math.abs(cur - target);
            }
        }
        return answer;
    }


    public static void main(String[] args){
        SearchSolution searchSolution = new SearchSolution();
//        searchSolution.permute(new int[]{1,2,3});
//        System.out.println(searchSolution.permute(new int[]{1,2,3}));
        //System.out.println(searchSolution.search(new int[]{3,1},1));
        //System.out.println(searchSolution.exist(new char[][]{{'E','D','C'},{'F','A','B'},{'G','H','I'}},"ABCDEFGHI"));
//        TreeNode node = new TreeNode(1);
//        node.left = new TreeNode(2);
//        node.right = new TreeNode(3);
//        System.out.println(searchSolution.serialize(node));
//        System.out.println(searchSolution.deserialize(searchSolution.serialize(node)).val);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
