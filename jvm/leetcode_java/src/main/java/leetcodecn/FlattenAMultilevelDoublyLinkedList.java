package leetcodecn;

// [766]扁平化多级双向链表
class FlattenAMultilevelDoublyLinkedList {


//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

    class Solution {
        public Node flatten(Node head) {
            if (head == null) return null;
            flattenNode(head);
            return head;
        }

        Node flattenNode(Node node) {
            Node pre = null;
            var cur = node;
            while (cur != null) {
                var t = cur.next;
                if (pre != null) pre.next = cur;
                cur.prev = pre;

                var child = cur.child;
                if (child == null) {
                    pre = cur;
                } else {
                    var tail = flattenNode(child);
                    cur.child = null;
                    cur.next = child;
                    child.prev = cur;

                    pre = tail;

                }
                cur = t;
            }

            pre.next = null;
            return pre;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node twoOne = new Node(21);
        Node twoTwo = new Node(22);
        Node three = new Node(3);

        one.next = two;
        two.prev = one;
        two.next = three;
        three.prev = two;

        twoOne.next = twoTwo;
        twoTwo.prev = twoOne;
        two.child = twoOne;

        (new FlattenAMultilevelDoublyLinkedList().new Solution()).flatten(one);
        System.out.println("ok");
    }
}
