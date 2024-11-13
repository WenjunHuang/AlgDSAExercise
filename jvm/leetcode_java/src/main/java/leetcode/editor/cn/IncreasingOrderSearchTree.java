package leetcode.editor.cn;


import java.util.LinkedList;
import java.util.List;

public class IncreasingOrderSearchTree {
    public static void main(String[] args) {
        Solution solution = new IncreasingOrderSearchTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public TreeNode increasingBST(TreeNode root) {
            traverse(root);
            TreeNode dummy = new TreeNode(-1);
            TreeNode cur = dummy;
            for (TreeNode node : result) {
                cur.right = node;
                cur.left = null;
                cur = node;
            }
            cur.right = null;
            cur.left = null;
            return dummy.right;
        }

        private List<TreeNode> result = new LinkedList<>();

        private void traverse(TreeNode node) {
            if (node == null) return;
            else if (node.left == null && node.right == null)
                result.add(node);
            else {
                traverse(node.left);
                result.add(node);
                traverse(node.right);
            }

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}