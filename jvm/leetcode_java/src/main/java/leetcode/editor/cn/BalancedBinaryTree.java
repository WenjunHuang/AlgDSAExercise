package leetcode.editor.cn;

public class BalancedBinaryTree {
    public static void main(String[] args) {
        Solution solution = new BalancedBinaryTree().new Solution();
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
        public boolean isBalanced(TreeNode root) {
            maxDepth(root);
            return isBalanced;
        }

        private boolean isBalanced = true;

        private int maxDepth(TreeNode node) {
            if (node == null) return 0;
            if (!isBalanced) return Integer.MIN_VALUE;

            int leftMaxDepth = maxDepth(node.left);
            if (leftMaxDepth == Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            int rightMaxDepth = maxDepth(node.right);
            if (rightMaxDepth == Integer.MIN_VALUE)
                return Integer.MIN_VALUE;

            if (Math.abs(leftMaxDepth - rightMaxDepth) > 1) {
                isBalanced = false;
                return Integer.MIN_VALUE;
            }

            return 1 + Math.max(leftMaxDepth, rightMaxDepth);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}