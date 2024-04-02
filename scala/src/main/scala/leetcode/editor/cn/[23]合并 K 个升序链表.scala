package leetcode.editor.cn

import scala.annotation.tailrec

object MergeKSortedLists {
  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for singly-linked list.
    */
  object Solution {

    import scala.collection.mutable

    def mergeKLists(lists: Array[ListNode]): ListNode = {
      val pq: mutable.PriorityQueue[ListNode] =
        mutable.PriorityQueue.from(lists.filter(_ != null))(
          Ordering.by[ListNode, Int](_.x).reverse
        )

      @annotation.tailrec
      def helper(n: ListNode): Unit = {
        if (pq.nonEmpty) {
          val node = pq.dequeue()
          n.next = node
          if (node.next != null) {
            pq.addOne(node.next)
          }
          helper(node)
        }
      }

      val dump = ListNode(-1)
      helper(dump)
      dump.next
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
