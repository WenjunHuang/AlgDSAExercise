package leetcodecn;

// [24]两两交换链表中的节点
class SwapNodesInPairs {


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
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) return head;

            // 先调换开头两个节点
            ListNode newHead = head.next;
            var k = newHead.next;
            newHead.next = head;
            head.next = k;

            // 调换剩余节点
            var prev = head;
            var pairStart = prev.next;
            while (pairStart != null) {
                var pairEnd = pairStart.next;
                if (pairEnd == null) break;

                var t = pairEnd.next;
                pairEnd.next = pairStart;
                pairStart.next = t;
                prev.next = pairEnd;

                prev = pairStart;
                pairStart = t;
            }

            return newHead;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
