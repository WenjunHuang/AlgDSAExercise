package leetcodecn;

import java.util.Map;

// [725]分隔链表
class SplitLinkedListInParts {


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
        public ListNode[] splitListToParts(ListNode head, int k) {
            if (head == null) return new ListNode[k];

            // 计算链表长度
            int n = 0;
            var p = head;
            while (p != null) {
                p = p.next;
                n++;
            }

            var arr = new ListNode[k];
            p = head;
            for (int i = 0; i < k; i++) {
                var size = (int) Math.ceil((double) n / (k - i));
                var pair = split(p, size);

                arr[i] = pair.first;
                p = pair.second;

                n = n - size;
            }

            return arr;
        }

        Pair split(ListNode node, int size) {
            if (node == null || size == 0) return new Pair(null, node);

            ListNode cur = node;
            ListNode pre = node;
            while (size > 0 && cur != null) {
                pre = cur;
                cur = cur.next;
                size--;
            }

            if (cur == null) {
                return new Pair(node, null);
            } else {
                pre.next = null;
                return new Pair(node, cur);
            }
        }

        class Pair {
            ListNode first;
            ListNode second;

            Pair(ListNode first, ListNode second) {
                this.first = first;
                this.second = second;
            }
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
