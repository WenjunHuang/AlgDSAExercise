package leetcodecn;

// [445]两数相加 II
class AddTwoNumbersIi {


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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) return null;

            var rl1 = reverse(l1);
            var rl2 = reverse(l2);
            var carry = 0;
            ListNode dummy = new ListNode(-1);
            var prev = dummy;
            while (rl1 != null || rl2 != null) {
                var val = carry + ((rl1 == null) ? 0 : rl1.val) + ((rl2 == null) ? 0 : rl2.val);
                carry = val / 10;
                var node = new ListNode(val % 10);
                prev.next = node;
                prev = node;

                rl1 = (rl1 == null) ? null : rl1.next;
                rl2 = (rl2 == null) ? null : rl2.next;
            }

            if (carry != 0) {
                prev.next = new ListNode(carry);
            }

            return reverse(dummy.next);
        }

        private ListNode reverse(ListNode node) {
            ListNode prev = null;
            var cur = node;
            while (cur != null) {
                var t = cur.next;
                cur.next = prev;
                prev = cur;
                cur = t;
            }
            return prev;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
