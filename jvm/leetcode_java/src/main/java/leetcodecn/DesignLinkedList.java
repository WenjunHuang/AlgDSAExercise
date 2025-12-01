package leetcodecn;

// [838]设计链表
class DesignLinkedList {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class MyLinkedList {
        class Node {
            Node prev;
            Node next;
            int val;

            Node(int val) {
                this.val = val;
            }
        }

        Node head;
        Node tail;
        int size;

        public MyLinkedList() {
        }

        public int get(int index) {
            if (head == null || index < 0) return -1;
            Node r = head;
            while (r != null && index > 0) {
                r = r.next;
                index--;
            }
            if (r == null) return -1;
            else return r.val;
        }

        public void addAtHead(int val) {
            if (head == null) {
                head = new Node(val);
                tail = head;
                size = 1;
            } else {
                var newHead = new Node(val);
                newHead.next = head;
                head.prev = newHead;
                head = newHead;
                size++;
            }

        }

        public void addAtTail(int val) {
            if (tail == null) {
                tail = new Node(val);
                head = tail;
                size = 1;
            } else {
                var newTail = new Node(val);
                tail.next = newTail;
                newTail.prev = tail;
                tail = newTail;
                size++;
            }

        }

        public void addAtIndex(int index, int val) {
            if (index >= 0 && index <= size) {
                if (index == size) {
                    addAtTail(val);
                } else if (index == 0) {
                    addAtHead(val);
                } else {
                    var cur = head;
                    while (cur != null && index > 0) {
                        cur = cur.next;
                        index--;
                    }
                    var newNode = new Node(val);
                    cur.prev.next = newNode;
                    newNode.prev = cur.prev;
                    newNode.next = cur;
                    cur.prev = newNode;
                    size++;
                }
            }
        }


        public void deleteAtIndex(int index) {
            if (index >= 0 && index < size) {
                if (index == 0) {
                    head = head.next;
                    size--;
                    if (size == 0) {
                        tail = null;
                    } else {
                        head.prev = null;
                    }
                } else if (index == size - 1) {
                    tail = tail.prev;
                    tail.next = null;
                    size--;
                } else {
                    var cur = head;
                    while (cur != null && index > 0) {
                        cur = cur.next;
                        index--;
                    }
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                    size--;
                }
            }
        }
    }

    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */
//IMPORTANT!! Submit Code Region End(Do not remove this line)
    public static void main(String[] args) {
        // add your test code
    }
}
