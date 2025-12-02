package leetcodecn;

// [147]对链表进行插入排序
class InsertionSortList {


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
        public ListNode insertionSortList(ListNode head) {
            if (head == null) return head;

            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            var pre = head;
            var cur = head.next;
            while (cur != null) {
                var t = cur.next;
                if (cur.val < pre.val) {
                    var p = dummy;
                    var c = dummy.next;
                    while (c != cur) {
                        var n = c.next;
                        if (cur.val < c.val) {
                            p.next = cur;
                            cur.next = c;
                            break;
                        }
                        p = c;
                        c = n;
                    }
                    pre.next = t;
                } else {
                    pre = cur;
                }
                cur = t;
            }

            return dummy.next;

        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        ListNode one = new ListNode(8);
        ListNode two = new ListNode(12);
        ListNode three = new ListNode(-7);
        ListNode four = new ListNode(-7);
        ListNode five = new ListNode(-6);
        one.next = two;
        two.next= three;
        three.next = four;
        four.next = five;

        var r = (new InsertionSortList().new Solution()).insertionSortList(one);
        System.out.println("ok");
    }
}
