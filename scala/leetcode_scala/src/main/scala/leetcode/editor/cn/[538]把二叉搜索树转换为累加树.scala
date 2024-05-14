package leetcode.editor.cn

object ConvertBstToGreaterTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    var sum = 0
    def convertBST(root: TreeNode): TreeNode =
      sum = 0
      traverse(root)
      root

    def traverse(node: TreeNode): Unit =
      if node != null then
        traverse(node.right)
        sum += node.value
        node.value = sum
        traverse(node.left)

  }
//leetcode submit region end(Prohibit modification and deletion)

}
