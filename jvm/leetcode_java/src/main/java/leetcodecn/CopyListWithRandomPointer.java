package leetcodecn;

// [138]随机链表的复制
class CopyListWithRandomPointer {


//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) return null;

            var ptr = head;
            // 将新节点插入原链表
            while (ptr != null) {
                var node = new Node(ptr.val);
                var temp = ptr.next;

                node.next = temp;
                ptr.next = node;

                ptr = temp;
            }

            // 更新random节点
            ptr = head;
            while (ptr != null) {
                var node = ptr.next;
                Node random = null;
                if (ptr.random != null) {
                    random = ptr.random.next;
                }
                node.random = random;

                ptr = node.next;
            }

            //分离新旧链表
            var newList = head.next;

            var lastOld = head;
            var lastNew = head.next;
            for (ptr = head.next.next; ptr != null; ptr = ptr.next.next) {
                lastOld.next = ptr;
                lastOld = ptr;

                lastNew.next = ptr.next;
                lastNew = ptr.next;

            }
            lastOld.next = null;
            lastNew.next = null;


            return newList;

        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
