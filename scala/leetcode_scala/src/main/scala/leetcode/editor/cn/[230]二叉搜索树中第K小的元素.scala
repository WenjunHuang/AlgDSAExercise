package leetcode.editor.cn

object KthSmallestElementInABst {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    class Answer(val root: TreeNode, val k: Int) {
      var res = 0
      var rank = 0

      def solution:Int =
        traverse(root)
        res
      def traverse(node: TreeNode): Unit =
        if node != null then
          traverse(node.left)
          rank += 1
          if k == rank then res = node.value
          else traverse(node.right)
    }

    def kthSmallest(root: TreeNode, k: Int): Int =
      Answer(root,k).solution

  }
//leetcode submit region end(Prohibit modification and deletion)

}
