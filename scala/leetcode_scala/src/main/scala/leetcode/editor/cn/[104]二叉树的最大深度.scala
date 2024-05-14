package leetcode.editor.cn
object MaximumDepthOfBinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    private var depth = 0
    private var res = 0
    def maxDepth(root: TreeNode): Int =
      depth = 0
      res = 0
      traverse(root)
      res

    private def traverse(root: TreeNode): Unit =
      if root != null
      then
        depth += 1
        if root.left == null && root.right == null then res = math.max(res, depth)

        traverse(root.left)
        traverse(root.right)

        depth -= 1

  }
//leetcode submit region end(Prohibit modification and deletion)

}
