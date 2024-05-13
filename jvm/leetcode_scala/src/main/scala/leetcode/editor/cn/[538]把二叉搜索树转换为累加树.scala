package leetcode.editor.cn

object ConvertBstToGreaterTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    import scala.util.control.TailCalls.*
    def convertBST(root: TreeNode): TreeNode =
      var sum = 0

      def traverse(node: TreeNode): TailRec[Unit] =
        node match
          case null => done(())
          case _ =>
            for
              _ <- traverse(node.right)
              _ = sum += node.value
              _ = node.value = sum
              _ <- traverse(node.left)
            yield ()

      traverse(root).result
      root

  }
//leetcode submit region end(Prohibit modification and deletion)

}
