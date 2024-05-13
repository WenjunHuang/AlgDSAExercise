package leetcode.editor.cn

object ValidateBinarySearchTree {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
//leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Example:
     * var ti = TreeNode(5)
     * var v = ti.`val`
     * Definition for a binary tree node.
     * class TreeNode(var `val`: Int) {
     *     var left: TreeNode? = null
     *     var right: TreeNode? = null
     * }
     */
    class Solution {
        fun isValidBST(root: TreeNode?): Boolean = isValidBST(root,null,null)

        fun isValidBST(root: TreeNode?, min: TreeNode?, max: TreeNode?): Boolean {
            return if (root == null) true
            else if (min != null && root.`val` <= min.`val`) false
            else if (max != null && root.`val` >= max.`val`) false
            else isValidBST(root.left, min, root) && isValidBST(root.right, root, max)
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
