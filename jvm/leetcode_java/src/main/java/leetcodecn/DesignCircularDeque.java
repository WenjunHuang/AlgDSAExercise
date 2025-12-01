package leetcodecn;

import java.util.LinkedList;

// [859]设计循环双端队列
class DesignCircularDeque {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class MyCircularDeque {
        final int capacity;
        int size;
        LinkedList<Integer> data = new LinkedList<>();

        public MyCircularDeque(int k) {
            this.capacity = k;
        }

        public boolean insertFront(int value) {
            if (size == capacity) return false;

            data.addFirst(value);
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (size == capacity) return false;

            data.addLast(value);
            size++;
            return true;

        }

        public boolean deleteFront() {
            if (size > 0) {
                data.removeFirst();
                size--;
                return true;
            } else {
                return false;
            }

        }

        public boolean deleteLast() {
            if (size > 0) {
                data.removeLast();
                size--;
                return true;
            } else {
                return false;
            }
        }

        public int getFront() {
            if (size > 0) {
                return data.getFirst();
            } else {
                return -1;
            }

        }

        public int getRear() {
            if (size > 0) {
                return data.getLast();
            } else {
                return -1;
            }

        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }
    }

    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */
//IMPORTANT!! Submit Code Region End(Do not remove this line)
    public static void main(String[] args) {
        // add your test code
    }
}
