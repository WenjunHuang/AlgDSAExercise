package leetcode.editor.cn
object RemoveDuplicatesFromSortedList {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for singly-linked list.
    * class ListNode(_x: Int = 0, _next: ListNode = null) {
    *   var next: ListNode = _next
    *   var x: Int = _x
    * }
    */
  object Solution {
    def deleteDuplicates(head: ListNode): ListNode = {

      @annotation.tailrec
      def impl(slow: ListNode, fast: ListNode): ListNode = {
        if (fast == null) slow
        else {
          if (slow.x == fast.x) {
            impl(slow, fast.next)
          } else {
            slow.next = fast
            impl(fast, fast.next)
          }
        }
      }

      if(head == null) null
      else {
        val r = impl(head, head)
        r.next = null
        head
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
