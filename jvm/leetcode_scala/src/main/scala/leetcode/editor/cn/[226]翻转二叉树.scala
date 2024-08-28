package leetcode.editor.cn

object InvertBinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def invertTree(root: TreeNode): TreeNode =
      traverse(root)
      root

    def traverse(node: TreeNode): Unit =
      if node != null then
        traverse(node.left)
        traverse(node.right)
        val temp = node.left
        node.left = node.right
        node.right = temp
  }
//leetcode submit region end(Prohibit modification and deletion)

}
