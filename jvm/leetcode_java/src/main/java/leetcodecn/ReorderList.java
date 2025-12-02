package leetcodecn;

import java.util.Stack;

// [143]重排链表
class ReorderList {


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
            var stack = new Stack<ListNode>();
            var size = 0;
            for (var p = head; p != null; p = p.next) {
                stack.push(p);
                size++;
            }

            int n = (int) Math.ceil((double) size / 2);

            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            var cur = head;
            var prev = dummy;
            for (var i = 0; i < n; i++) {
                var t = cur.next;
                var s = stack.pop();

                prev.next = cur;
                cur.next = s;
                prev = s;

                cur = t;
            }
            prev.next = null;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
