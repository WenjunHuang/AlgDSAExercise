package leetcodecn;

// [234]回文链表
class PalindromeLinkedList {


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
        public boolean isPalindrome(ListNode head) {
            if (head == null) return true;

            ListNode slow = head, fast = head;
            while (slow != null && fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            // slow在中点,逆序剩余
            ListNode pre = slow;
            ListNode cur = pre.next;
            slow.next = null;
            while (cur != null) {
                var t = cur.next;
                cur.next = pre;
                pre = cur;
                cur = t;
            }
            // pre指向最后一个元素
            boolean result = true;
            var left = head;
            var right = pre;
            while (left != null && right != null) {
                if (left.val != right.val) {
                    result = false;
                    break;
                }
                left = left.next;
                right = right.next;
            }

            // 恢复链表
            cur = pre.next;
            pre.next = null;
            while (cur != null) {
                var t = cur.next;
                cur.next = pre;
                pre = cur;
                cur = t;
            }

            return result;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(2);
        ListNode four = new ListNode(2);
        ListNode tail = new ListNode(1);
        tail.next = null;
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = tail;

        var p = new PalindromeLinkedList();
        var s = p.new Solution();
        var r = s.isPalindrome(head);
        System.out.println(r);
    }
}
