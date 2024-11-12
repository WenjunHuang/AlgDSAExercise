package leetcode.editor.cn
object ConvertBinarySearchTreeToSortedDoublyLinkedList {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a Node. class Node(var _value: Int) { var value: Int = _value var left: Node = null var right: Node = null }
    */

  object Solution {
    import scala.util.control.TailCalls.*
    def treeToDoublyList(root: Node): Node =
      impl(root).result

    private def impl(node: Node): TailRec[Node] =
      if node == null then done(null)
      else
        for
          lh <- impl(node.left)
          rh <- impl(node.right)
        yield
          var leftHead        = lh
          var rightHead       = rh
          var leftTail: Node  = null
          var rightTail: Node = null
          if leftHead != null then
            leftTail = leftHead.left
            node.left = leftTail
            leftTail.right = node
          else
            leftTail = node
            leftHead = node

          if rightHead != null then
            rightTail = rightHead.left
            node.right = rightHead
            rightHead.left = node
          else
            rightTail = node
            rightHead = node

          leftHead.left = rightTail
          rightTail.right = leftHead
          leftHead
  }
//leetcode submit region end(Prohibit modification and deletion)

}
