package leetcodecn;

import java.util.Arrays;

// [148]排序链表
class SortList {


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
        record Pair(ListNode first, ListNode second) {
        }

        public ListNode sortList(ListNode head) {
            if (head == null) return head;

            int n = 0;
            for (var p = head; p != null; p = p.next) {
                n++;
            }

            ListNode newHead = head;
            ListNode newEnd = null;
            ListNode nextGroupStart = null;
            ListNode lastGroupEnd = null;
            for (var step = 1; step < n; step <<= 1) {
                var l1 = newHead;
                var r1 = findEnd(l1, step);

                var l2 = r1.next;
                var r2 = findEnd(l2, step);
                nextGroupStart = r2.next;
                var r = merge(l1, r1, l2, r2);
                newHead = r.first;
                lastGroupEnd = r.second;

                while (nextGroupStart != null) {
                    var nextL1 = nextGroupStart;
                    var nextR1 = findEnd(nextL1, step);
                    var nextL2 = nextR1.next;
                    if (nextL2 != null) {

                        var nextR2 = findEnd(nextL2, step);

                        nextGroupStart = nextR2.next;

                        var arr = merge(nextL1, nextR1, nextL2, nextR2);
                        lastGroupEnd.next = arr.first;
                        lastGroupEnd = arr.second;
                    } else {
                        lastGroupEnd.next = nextL1;
                        lastGroupEnd = nextR1;
                        break;
                    }
                }

                newEnd = lastGroupEnd;
                newEnd.next = null;
            }

            return newHead;
        }

        private ListNode findEnd(ListNode s, int d) {
            while (s.next != null && --d > 0) {
                s = s.next;
            }
            return s;
        }

        private Pair merge(ListNode l1, ListNode r1, ListNode l2, ListNode r2) {
            ListNode dummy = new ListNode(-1);
            ListNode end = null;

            var prev = dummy;
            var p1 = l1;
            var p1End = r1.next;
            var p2 = l2;
            var p2End = r2.next;
            while (p1 != p1End && p2 != p2End) {
                if (p1.val <= p2.val) {
                    prev.next = p1;
                    prev = p1;
                    p1 = p1.next;
                } else {
                    prev.next = p2;
                    prev = p2;
                    p2 = p2.next;
                }
            }

            if (p1 == p1End) {
                prev.next = p2;
                prev = p2;
                var cur = p2.next;
                while (cur != p2End) {
                    var t = cur.next;
                    prev = cur;
                    cur = t;
                }
                end = prev;
            }
            if (p2 == p2End) {
                prev.next = p1;
                prev = p1;
                var cur = p1.next;
                while (cur != p1End) {
                    var t = cur.next;
                    prev = cur;
                    cur = t;
                }
                end = prev;
            }

            end.next = null;
            return new Pair(dummy.next, end);
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
        two.next = three;
        three.next = four;
        four.next = five;

        var r = (new SortList().new Solution()).sortList(one);
        System.out.println("ok");
    }
}
