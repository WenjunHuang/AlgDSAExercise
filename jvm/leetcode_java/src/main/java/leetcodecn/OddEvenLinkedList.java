package leetcodecn;

// [328]奇偶链表
class OddEvenLinkedList {


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
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) return head;

            var prevOdd = head;
            var curOdd = head.next.next;
            var prevEven = head.next;
            var curEven = curOdd == null ? null : curOdd.next;

            var evenHead = prevEven;
            var newHead = head;

            while (curOdd != null && curEven != null) {
                var to = curEven.next;
                var te = to == null ? null : to.next;

                prevOdd.next = curOdd;
                prevEven.next = curEven;

                prevOdd = curOdd;
                prevEven = curEven;

                curOdd = to;
                curEven = te;
            }

            if (curOdd != null) {
                prevOdd.next = curOdd;
                prevOdd = curOdd;
            }

            prevOdd.next = evenHead;
            prevEven.next = null;

            return newHead;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
