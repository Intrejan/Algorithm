package myTree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeSolution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums,0,nums.length-1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if(left>right){
            return null;
        }
        int mid = (left+right)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums,left,mid);
        root.right = build(nums,mid+1,right);
        return root;
    }

    /**
     * 构建排序二叉树
     * @param nums 数组
     * @return 根节点
     */
    public TreeNode buildSortedTree(int[] nums){
        TreeNode root = new TreeNode(nums[0]);
        for (int num : nums) {
            buildSorted(root, num);
        }
        return root;
    }

    private TreeNode buildSorted(TreeNode node, int num) {
        if(node==null){
            node = new TreeNode(num);
        }else{
            if(num<=node.val){
                node.left = buildSorted(node.left,num);
            }else {
                node.right = buildSorted(node.right,num);
            }
        }
        return node;
    }

    /**
     * 二叉树的前序遍历
     * @param root 根节点
     * @return 列表
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root,result);
        return result;
    }

    private void preorder(TreeNode root, List<Integer> result) {
        if(root==null){
            return;
        }
        result.add(root.val);
        preorder(root.left,result);
        preorder(root.right,result);
    }

    /**
     * 二叉树的中序遍历
     * @param root 根节点
     * @return 列表
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root,result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if(root==null){
            return;
        }
        inorder(root.left,result);
        result.add(root.val);
        inorder(root.right,result);
    }

    /**
     * 二叉树的后序遍历
     * @param root 根节点
     * @return 列表
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root,result);
        return result;
    }

    private void postorder(TreeNode root, List<Integer> result) {
        if(root==null){
            return;
        }
        postorder(root.left,result);
        postorder(root.right,result);
        result.add(root.val);
    }

    int[] preorder;
    HashMap<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for(int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        return rebuild(0, 0, inorder.length - 1);
    }

    TreeNode rebuild(int root, int left, int right) {
        if(left > right) return null;                          // 递归终止
        TreeNode node = new TreeNode(preorder[root]);          // 建立根节点
        int i = map.get(preorder[root]);                       // 划分根节点、左子树、右子树
        node.left = rebuild(root + 1, left, i - 1);              // 开启左子树递归
        node.right = rebuild(root + i - left + 1, i + 1, right); // 开启右子树递归
        return node;                                           // 回溯返回根节点
    }

    public static void main(String[] args){
        TreeSolution treeSolution = new TreeSolution();
        //TreeNode root = treeSolution.buildSortedTree(new int[]{6,3,5,7,2,0,9,11,4});
        TreeNode root = treeSolution.buildTree(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
        System.out.println(treeSolution.postorderTraversal(root));
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