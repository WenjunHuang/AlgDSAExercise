package leetcode.editor.cn
object ReverseLinkedList {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for singly-linked list. class ListNode(_x: Int = 0, _next: ListNode = null) { var next: ListNode = _next var x: Int = _x }
    */
  object Solution {
    def reverseList(head: ListNode): ListNode =
      if head == null
      then head
      else reverseN(head, Int.MaxValue)

    @annotation.tailrec
    private def reverseN(head: ListNode, n: Int, acc: ListNode = null): ListNode =
      if n == 0 || head == null
      then acc
      else
        val next = head.next
        head.next = acc
        reverseN(next, n - 1, head)
  }
//leetcode submit region end(Prohibit modification and deletion)

}
