package leetcode.editor.cn

object ConvertBstToGreaterTree {

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
        var sum = 0
        fun convertBST(root: TreeNode?): TreeNode? {
            traverse(root)
            return root
        }

        fun traverse(node:TreeNode?) {
            if (node == null) return

            traverse(node.right)
            sum += node.`val`
            node.`val` = sum
            traverse(node.left)

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
