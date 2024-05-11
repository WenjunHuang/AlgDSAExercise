package leetcode.editor.cn

object SearchInABinarySearchTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def searchBST(root: TreeNode, `val`: Int): TreeNode =
      if root == null then null
      else if root.value > `val` then searchBST(root.left, `val`)
      else if root.value < `val` then searchBST(root.right, `val`)
      else root

  }
//leetcode submit region end(Prohibit modification and deletion)

}
