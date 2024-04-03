package leetcode.editor.cn
object MaximumDepthOfBinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node.
    * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    *   var value: Int = _value
    *   var left: TreeNode = _left
    *   var right: TreeNode = _right
    * }
    */
  object Solution {
    def maxDepth(root: TreeNode): Int = {
      if (root == null) 0
      else {
        val left = maxDepth(root.left)
        val right = maxDepth(root.right)
        1 + Math.max(left, right)
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
