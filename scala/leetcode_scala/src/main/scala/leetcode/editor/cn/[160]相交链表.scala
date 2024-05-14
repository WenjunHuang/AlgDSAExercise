package leetcode.editor.cn
object IntersectionOfTwoLinkedLists {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for singly-linked list.
    * class ListNode(var _x: Int = 0) {
    *   var next: ListNode = null
    *   var x: Int = _x
    * }
    */

  object Solution {
    def getIntersectionNode(headA: ListNode, headB: ListNode): ListNode = {
      @annotation.tailrec
      def helper(p1: ListNode, p2: ListNode): ListNode = {
        if (p1 == p2) p1
        else {
          val nextP1 = if (p1 == null) headB else p1.next
          val nextP2 = if (p2 == null) headA else p2.next
          helper(nextP1, nextP2)
        }
      }
      if (headA == null || headB == null) null
      else helper(headA, headB)
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
