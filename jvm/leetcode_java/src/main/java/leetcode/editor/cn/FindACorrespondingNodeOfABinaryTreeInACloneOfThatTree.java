package leetcode.editor.cn;

public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {
    public static void main(String[] args) {
        Solution solution = new FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    class Solution {
        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            return traverse(original, cloned, target);

//            return getTargetCopyImpl(original, cloned, target);
        }

        /**
         * 分解问题
         */
        private TreeNode getTargetCopyImpl(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            if (original == null) return null;
            if (original == target) return cloned;

            TreeNode left = getTargetCopyImpl(original.left, cloned.left, target);
            if (left != null) return left;
            return getTargetCopyImpl(original.right, cloned.right, target);
        }

        private TreeNode res = null;

        private TreeNode traverse(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            res = null;
            traverseImpl(original, cloned, target);
            return res;
        }

        private void traverseImpl(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            if (original == null || res != null) return;
            if (original == target) {
                res = cloned;
            } else {
                traverseImpl(original.left, cloned.left, target);
                traverseImpl(original.right, cloned.right, target);
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}