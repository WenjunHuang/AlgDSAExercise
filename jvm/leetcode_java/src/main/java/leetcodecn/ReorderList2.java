package leetcodecn;

// [143]重排链表
class ReorderList2 {


//IMPORTANT!! Submit Code Region Begin(Do not remove this line)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public void reorderList(ListNode head) {
            if (head != null) {
                var mid = splitAtMiddle(head);
                var rev = reverse(mid);
                merge(head, rev);
            }

        }

        ListNode merge(ListNode first, ListNode second) {
            var p1 = first;
            var p2 = second;
            ListNode dummy = new ListNode(-1);
            dummy.next = first;
            var prev = dummy;
            while (p1 != null && p2 != null) {
                var t1 = p1.next;
                var t2 = p2.next;

                prev.next = p1;
                p1.next = p2;

                prev = p2;
                p1 = t1;
                p2 = t2;
            }
            if (p1 != null)
                prev.next = p1;
            else prev.next = p2;

            return dummy.next;
        }

        ListNode splitAtMiddle(ListNode node) {
            ListNode prev = null;
            var slow = node;
            var fast = node;
            while (slow != null && fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            if (prev != null) prev.next = null;
            return slow;
        }

        ListNode reverse(ListNode node) {
            var prev = node;
            var cur = node.next;
            while (cur != null) {
                var t = cur.next;
                cur.next = prev;
                prev = cur;
                cur = t;
            }
            node.next = null;
            return prev;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
