package leetcodecn;
// [142]环形链表 II
class LinkedListCycleIi {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null ||head.next.next == null) return null;
        var slow = head.next;
        var fast = head.next.next;

        while (slow != fast && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null && fast.next != null){
            fast = head;
            while (fast != slow){
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        } else {
            return null;
        }
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
}
}
