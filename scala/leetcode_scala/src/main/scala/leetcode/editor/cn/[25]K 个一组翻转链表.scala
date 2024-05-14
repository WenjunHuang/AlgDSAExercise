package leetcode.editor.cn
object ReverseNodesInKGroup {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for singly-linked list. class ListNode(_x: Int = 0, _next: ListNode = null) { var next: ListNode = _next var x: Int = _x }
    */
  object Solution {
    def reverseKGroup(head: ListNode, k: Int): ListNode =
      val dummy = new ListNode(0, head)
      (0 until nodeCount(head) / k).foldLeft((dummy, head)) { case ((prev, head), _) =>
        val (newHead, newTail) = reverseFirstN(head, k)
        prev.next = newHead
        head.next = newTail
        (head, newTail)
      }

      dummy.next

    @annotation.tailrec
    private def nodeCount(node: ListNode, acc: Int = 0): Int =
      if node == null then acc
      else nodeCount(node.next, acc + 1)

    @annotation.tailrec
    private def reverseFirstN(head: ListNode, n: Int, acc: ListNode = null): (ListNode, ListNode) =
      if n == 0 || head == null
      then (acc, head)
      else
        val next = head.next
        head.next = acc
        reverseFirstN(next, n - 1, head)
  }
//leetcode submit region end(Prohibit modification and deletion)

}

@main
def run(): Unit =
  println(ReverseNodesInKGroup.Solution.reverseKGroup(new ListNode(1, new ListNode(2, null)), 2))
