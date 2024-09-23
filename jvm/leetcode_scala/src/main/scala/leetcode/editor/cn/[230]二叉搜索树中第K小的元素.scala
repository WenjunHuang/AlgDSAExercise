package leetcode.editor.cn

object KthSmallestElementInABst {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  import scala.util.control.TailCalls.*
  object Solution {
    def kthSmallest(root: TreeNode, k: Int): Int = {
      var rank = 0
      var res  = -1
      def traverse(node: TreeNode): TailRec[Unit] =
        if node == null then done(())
        else
          for
            _ <- traverse(node.left)
            _ <- tailcall { rank += 1; done(()) }
            _ <-
              if rank == k then tailcall { res = node.value; done(()) }
              else { traverse(node.right) }
          yield ()

      traverse(root).result
      res
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}
