package leetcode.editor.cn
object MaximumBinaryTreeIi {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def insertIntoMaxTree(root: TreeNode, value: Int): TreeNode =
      if root == null then TreeNode(value)
      else if root.value < value then
        val newRoot = TreeNode(value)
        newRoot.left = root
        newRoot
      else
        root.right = insertIntoMaxTree(root.right, value)
        root

  }
//leetcode submit region end(Prohibit modification and deletion)

}
