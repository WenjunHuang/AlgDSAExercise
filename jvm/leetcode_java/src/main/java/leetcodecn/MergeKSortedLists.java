package leetcodecn;

// [23]合并 K 个升序链表
class MergeKSortedLists {


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
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length < 1) return null;
            if (lists.length == 1) return lists[0];

            ListNode newHead = null;
            for (ListNode list : lists) {
                newHead = merge(newHead, list);
            }

            return newHead;

        }


        private ListNode merge(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;

            ListNode dummy = new ListNode(-1);

            var prev = dummy;
            var p1 = l1;
            var p2 = l2;
            while (p1 != null && p2 != null) {
                if (p1.val <= p2.val) {
                    prev.next = p1;
                    prev = p1;
                    p1 = p1.next;
                } else {
                    prev.next = p2;
                    prev = p2;
                    p2 = p2.next;
                }
            }

            if (p1 == null) {
                prev.next = p2;
            }
            if (p2 == null) {
                prev.next = p1;
            }

            return dummy.next;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
