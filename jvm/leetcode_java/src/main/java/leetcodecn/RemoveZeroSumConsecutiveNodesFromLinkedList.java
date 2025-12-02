package leetcodecn;

import java.util.HashMap;

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
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            var seen = new HashMap<Integer, ListNode>();
            int prefix = 0;
            for (var p = dummy; p != null; p = p.next) {
                prefix += p.val;
                seen.put(prefix, p);
            }

            prefix = 0;
            for (var p = dummy; p != null; p = p.next) {
                prefix += p.val;
                p.next = seen.get(prefix).next;
            }

            return dummy.next;

        }


    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
