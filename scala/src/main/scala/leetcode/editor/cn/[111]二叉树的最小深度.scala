package leetcode.editor.cn

object MinimumDepthOfBinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node.
    * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    *   var value: Int = _value
    *   var left: TreeNode = _left
    *   var right: TreeNode = _right
    * }
    */
  object Solution {

    import scala.collection.mutable

    def minDepth(root: TreeNode): Int = {
      if (root == null) 0
      else {
        @annotation.tailrec
        def impl(queue: mutable.Queue[(TreeNode, Int)]): Int = {
          if (queue.isEmpty) 0
          else {
            queue.dequeue() match {
              case (node, depth) if node.left == null && node.right == null =>
                depth
              case (node, depth) =>
                if (node.left != null) queue.enqueue(node.left -> (depth + 1))
                if (node.right != null) queue.enqueue(node.right -> (depth + 1))
                impl(queue)
            }
          }
        }

        val queue = mutable.Queue[(TreeNode, Int)]()
        queue.enqueue(root -> 1)
        impl(queue)
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
