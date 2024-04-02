package leetcode.editor.cn
object RemoveNthNodeFromEndOfList {

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for singly-linked list.
    */
  object Solution {
    def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
      val dummy = ListNode(-1)
      dummy.next = head
      val x = findFromEnd(dummy, n + 1)
      x.next = x.next.next
      dummy.next
    }

    def findFromEnd(head: ListNode, k: Int): ListNode = {
      var p1 = head
      var p2 = head
      
      for (_ <- 0 until k) p1 = p1.next

      while (p1 != null) {
        p2 = p2.next
        p1 = p1.next
      }
      p2
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
