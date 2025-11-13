package leetcodecn;

// [83]删除排序链表中的重复元素
class RemoveDuplicatesFromSortedList {


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
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummyHead = new ListNode(-999);

            var p = dummyHead;
            var p1 = head;
            while (p1 != null) {
                if (p.val == p1.val){
                    p1 = p1.next;
                } else {
                    p.next = p1;
                    p = p.next;
                }
            }
            p.next = null;
            return dummyHead.next;

        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
