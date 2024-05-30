package myTree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TreeSolution {

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid);
        root.right = build(nums, mid + 1, right);
        return root;
    }

    /**
     * 构建排序二叉树
     *
     * @param nums 数组
     * @return 根节点
     */
    public TreeNode buildSortedTree(int[] nums) {
        TreeNode root = new TreeNode(nums[0]);
        for (int num : nums) {
            buildSorted(root, num);
        }
        return root;
    }

    private TreeNode buildSorted(TreeNode node, int num) {
        if (node == null) {
            node = new TreeNode(num);
        } else {
            if (num <= node.val) {
                node.left = buildSorted(node.left, num);
            } else {
                node.right = buildSorted(node.right, num);
            }
        }
        return node;
    }

    /**
     * 二叉树的前序遍历
     *
     * @param root 根节点
     * @return 列表
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    /**
     * 二叉树的中序遍历
     *
     * @param root 根节点
     * @return 列表
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    /**
     * 二叉树的后序遍历
     *
     * @param root 根节点
     * @return 列表
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    int[] preorder;
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return rebuild(0, 0, inorder.length - 1);
    }

    TreeNode rebuild(int root, int left, int right) {
        if (left > right) {
            return null;                          // 递归终止
        }
        TreeNode node = new TreeNode(preorder[root]);          // 建立根节点
        int i = map.get(preorder[root]);                       // 划分根节点、左子树、右子树
        node.left = rebuild(root + 1, left, i - 1);              // 开启左子树递归
        node.right = rebuild(root + i - left + 1, i + 1, right); // 开启右子树递归
        return node;                                           // 回溯返回根节点
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        int length = preorder.length;
        int rootOfInorder = 0;
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < length; i++) {
            if (inorder[i] == root.val) {
                rootOfInorder = i;
            }
        }
        int left_l = rootOfInorder;
        int right_l = length - rootOfInorder - 1;
        int[] pre_l = new int[left_l];
        int[] in_l = new int[left_l];
        for (int i = 0; i < left_l; i++) {
            pre_l[i] = preorder[i + 1];
            in_l[i] = inorder[i];
        }
        int[] pre_r = new int[right_l];
        int[] in_r = new int[right_l];
        for (int i = 0; i < right_l; i++) {
            pre_r[i] = preorder[i + left_l + 1];
            in_r[i] = inorder[i + left_l + 1];
        }
        root.left = buildTree2(pre_l, in_l);
        root.right = buildTree2(pre_r, in_r);
        return root;
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node == null) {
                    continue;
                }
                if (!queue.isEmpty() && i != size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    public void flatten(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        pre(root, nodes);
        for (int i = 0; i < nodes.size() - 1; i++) {
            TreeNode node = nodes.get(i);
            node.left = null;
            node.right = nodes.get(i + 1);
        }
    }

    private void pre(TreeNode node, List<TreeNode> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(node);
        pre(node.left, nodes);
        pre(node.right, nodes);
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        TreeSolution treeSolution = new TreeSolution();
        //TreeNode root = treeSolution.buildSortedTree(new int[]{6,3,5,7,2,0,9,11,4});
        TreeNode root = treeSolution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
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

class Node {

    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}