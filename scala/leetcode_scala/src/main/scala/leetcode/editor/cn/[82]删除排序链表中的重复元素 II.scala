package leetcode.editor.cn
object RemoveDuplicatesFromSortedListIi {
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode(_x: Int = 0, _next: ListNode = null) {
 *   var next: ListNode = _next
 *   var x: Int = _x
 * }
 */
object Solution {
    def deleteDuplicates(head: ListNode): ListNode = {
      val dummy = new ListNode(-1)
      var p = dummy
      var q = head

      while (q!=null) {
        if (q.next != null && q.next.x == q.x) {
          while (q.next !=null && q.next.x == q.x) {
            q = q.next
          }
          // 跳过元素重复段的最后一个元素
          q = q.next

          if (q == null) p.next = null
        } else {
          p.next = q
          p = p.next
          q = q.next
        }
      }

      dummy.next
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
