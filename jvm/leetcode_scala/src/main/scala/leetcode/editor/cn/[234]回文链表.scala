package leetcode.editor.cn
object PalindromeLinkedList {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for singly-linked list. class ListNode(_x: Int = 0, _next: ListNode = null) { var next: ListNode = _next var x: Int = _x }
    */
  object Solution {
    def isPalindrome(head: ListNode): Boolean = {
      var fast = head
      var slow = head
      while fast != null && fast.next != null do
        fast = fast.next.next
        slow = slow.next

      if fast != null then slow = slow.next

      var right = reverse(slow)
      var left = head
      var res = true
      while right != null && res do
        if left.x == right.x
        then
          left = left.next
          right = right.next
        else res = false

      res
    }

    @annotation.tailrec
    private def reverse(head: ListNode, acc: ListNode = null): ListNode =
      if head == null
      then acc
      else
        val next = head.next
        head.next = acc
        reverse(next, head)

  }
//leetcode submit region end(Prohibit modification and deletion)

}
