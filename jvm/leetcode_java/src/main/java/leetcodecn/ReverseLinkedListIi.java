package leetcodecn;

// [92]反转链表 II
class ReverseLinkedListIi {


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
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            // 先找到left之前和right之后的节点
            ListNode preLeft = null;
            ListNode leftBound = null;
            ListNode afterRight = null;
            var prev = dummy;
            for (var cur = head; cur != null; cur = cur.next) {
                left--;
                right--;
                if (left == 0 && preLeft == null) {
                    preLeft = prev;
                    leftBound = cur;
                }
                if (right == 0) {
                    afterRight = cur.next;
                    break;
                }

                prev = cur;
            }

            // 左边界超出链表长度，不反转任何节点
            if (leftBound == null) return head;

            // 翻转
            prev = null;
            var cur = leftBound;
            while (cur != afterRight && cur != null) {
                var t = cur.next;
                cur.next = prev;
                prev = cur;
                cur = t;
            }

            preLeft.next = prev;
            leftBound.next = afterRight;

            return dummy.next;

        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
