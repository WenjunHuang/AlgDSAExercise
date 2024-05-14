package leetcode.editor.cn

object 翻转二叉树 {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def invertTree(root: TreeNode): TreeNode = {
      if root != null then
        val left = invertTree(root.left)
        val right = invertTree(root.right)
        root.left = right
        root.right = left

      root
    }

    def traverse(root: TreeNode): Unit =
      if root != null then
        val temp = root.left
        root.left = root.right
        root.right = temp
        traverse(root.left)
        traverse(root.right)

  }
//leetcode submit region end(Prohibit modification and deletion)

}
