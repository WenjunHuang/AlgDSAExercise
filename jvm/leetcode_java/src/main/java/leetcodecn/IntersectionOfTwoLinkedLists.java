package leetcodecn;
// [160]相交链表
class IntersectionOfTwoLinkedLists {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        var first = headA;
        var firstDone = false;
        var second = headB;
        var secondDone = false;


        while (first != second){
            first = first.next;
            if (first == null && !firstDone) {
                first = headB;
                firstDone = true;
            }
            second = second.next;
            if (second == null && !secondDone) {
                second = headA;
                secondDone = true;
            }
        }
        return first;
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
}
}
