package leetcodecn;

// [141]环形链表
class LinkedListCycle {


//IMPORTANT!! Submit Code Region Begin(Do not remove this line)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) return false;
            var slow = head.next;
            var fast = head.next.next;

            while (slow != fast && fast != null && fast.next !=null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return fast != null && fast.next !=null;


        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
