package leetcodecn;
// [100282]图书整理 I
class CongWeiDaoTouDaYinLianBiaoLcof {

    
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
    public int[] reverseBookList(ListNode head) {
        if (head == null) return new int[0];

        int n = 0;
        var p = head;
        while (p!=null) {p = p.next;n++;}
        var arr = new int[n];
        
        p = head;
        while (p!=null){
            arr[--n] = p.val;
            p = p.next;
        }

        return arr;

    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
}
}
