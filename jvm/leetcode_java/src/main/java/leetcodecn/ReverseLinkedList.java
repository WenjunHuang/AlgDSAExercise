package leetcodecn;
// [206]反转链表
class ReverseLinkedList {

    
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
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = head;
        ListNode cur = head.next;
        head.next = null;
        while (cur != null){
            var t = cur.next;
            
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
}
}