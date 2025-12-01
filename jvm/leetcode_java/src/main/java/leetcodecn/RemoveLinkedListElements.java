package leetcodecn;
// [203]移除链表元素
class RemoveLinkedListElements {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)return null;

        ListNode dump = new ListNode(0);
        dump.next = head;

        ListNode cur = head;
        ListNode pre = dump;

        while (cur != null){
            if (cur.val == val){
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return dump.next;
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
}
}
