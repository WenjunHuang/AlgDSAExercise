package leetcodecn;
// [100187]删除中间节点
class DeleteMiddleNodeLcci {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        if (node == null) return;

        var fast = node.next;
        var mid = node;
        while (mid!=null && fast !=null && fast.next !=null){
            mid = mid.next;
            fast = fast.next.next;
        }

        //slow在中点
        var cur = node;
        var pre = node;
        while (cur != mid){
            pre = cur;
            cur = cur.next;
        }

        pre.next = mid.next;
        mid.next = null;
        
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    ListNode one = new ListNode(4);
    ListNode two = new ListNode(5);
    ListNode three = new ListNode(1);
    ListNode four = new ListNode(9);
    one.next = two;
    two.next = three;
    three.next = four;
    (new DeleteMiddleNodeLcci().new Solution()).deleteNode(one);
    System.out.println("ok");

}
}
