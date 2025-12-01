package leetcodecn;

// [1267]从链表中删去总和值为零的连续节点
class RemoveZeroSumConsecutiveNodesFromLinkedList {


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
        public ListNode removeZeroSumSublists(ListNode head) {
            var from = head;

            while (from != null) {
                var count = 0;
                boolean deleted = false;
                var cur = from;
                while (cur != null) {
                    count += cur.val;
                    if (count == 0) {
                        head = deleteRange(head, from, cur);
                        from = head;
                        deleted = true;
                        break;
                    }
                    cur = cur.next;
                }

                if (!deleted)
                    from = from.next;
            }
            return head;
        }

        ListNode deleteRange(ListNode node, ListNode from, ListNode to) {
            var dump = new ListNode(-1);
            dump.next = node;
            var pre = dump;
            var cur = node;
            while (cur != from) {
                var t = cur.next;
                pre = cur;
                cur = t;
            }
            pre.next = to.next;
            return dump.next;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
