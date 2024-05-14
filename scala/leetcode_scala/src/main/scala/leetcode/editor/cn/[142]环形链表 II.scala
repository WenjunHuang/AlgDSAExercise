package leetcode.editor.cn

import scala.annotation.tailrec

object LinkedListCycleIi {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for singly-linked list.
    * class ListNode(var _x: Int = 0) {
    *   var next: ListNode = null
    *   var x: Int = _x
    * }
    */

  object Solution {
    def detectCycle(head: ListNode): ListNode = {
      @annotation.tailrec
      def findStartOfCycle(slow: ListNode, fast: ListNode): ListNode = {
        (slow,fast) match {
          case (s,f) if s == f => s
          case _ => findStartOfCycle(slow.next, fast.next)
        }
      }

      @annotation.tailrec
      def findIntersection(slow:ListNode,fast:ListNode):ListNode = {
        (slow,fast) match {
          case (_,f) if f == null || f.next == null => null
          case (s,f) =>
            val currentSlow = s.next
            val currentFast = f.next.next
            if (currentFast == currentSlow)
              findStartOfCycle(head,currentFast)
            else findIntersection(currentSlow,currentFast)
        }
      }
      findIntersection(head,head)
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
