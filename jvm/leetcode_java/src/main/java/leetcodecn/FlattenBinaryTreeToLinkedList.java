package leetcodecn;

// [114]二叉树展开为链表
class FlattenBinaryTreeToLinkedList {


//IMPORTANT!! Submit Code Region Begin(Do not remove this line)

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
        public void flatten(TreeNode root) {
            if (root != null) {
                traverse(root);
            }
        }

        private TreeNode traverse(TreeNode node) {
            TreeNode leftEnd = null;
            TreeNode rightEnd = null;
            TreeNode left = node.left;
            TreeNode right = node.right;

            if (left != null) {
                leftEnd = traverse(left);
            }
            if (right != null) {
                rightEnd = traverse(right);
            }

            // 左子树不为空，那么将左子树的尾部连接到右子树
            // 否则因为右子树本来就连接着，所以啥都不用做
            if (left != null) {
                node.right = left;
                leftEnd.right = right;
            }
            node.left = null;

            return (rightEnd != null) ? rightEnd : ((leftEnd != null) ? leftEnd : node);
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
