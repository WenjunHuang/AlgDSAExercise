package leetcode.editor.cn
object MiddleOfTheLinkedList {
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode(_x: Int = 0, _next: ListNode = null) {
 *   var next: ListNode = _next
 *   var x: Int = _x
 * }
 */
object Solution {
    def middleNode(head: ListNode): ListNode = {
      var fast = head
      var slow = head
      while(fast != null && fast.next != null){
        slow = slow.next
        fast = fast.next.next
      }
      slow
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
