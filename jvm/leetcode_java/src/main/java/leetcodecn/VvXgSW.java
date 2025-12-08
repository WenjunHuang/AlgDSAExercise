package leetcodecn;

import java.util.Comparator;
import java.util.PriorityQueue;

// [1000342]合并 K 个升序链表
class VvXgSW {


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
            if (lists == null || lists.length == 0) return null;

            var queue = new PriorityQueue<ListNode>(lists.length, Comparator.comparingInt(a -> a.val));
            for (var l : lists) {
                if (l != null) queue.add(l);
            }

            ListNode dummy = new ListNode(-1);
            ListNode prev = dummy;
            while (!queue.isEmpty()) {
                var cur = queue.poll();
                if (cur.next != null) queue.add(cur.next);

                prev.next = cur;
                cur.next = null;
                prev = cur;
            }

            return dummy.next;

        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
