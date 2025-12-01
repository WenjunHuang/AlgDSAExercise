package leetcodecn;

// [860]设计循环队列
class DesignCircularQueue {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class MyCircularQueue {
        int[] data;
        int size;
        int top;
        int bottom;


        public MyCircularQueue(int k) {
            this.data = new int[k];
        }

        public boolean enQueue(int value) {
            if (size < data.length) {
                data[top] = value;
                size++;
                top = (++top) % data.length;
                return true;
            } else {
                return false;
            }

        }

        public boolean deQueue() {
            if (size > 0) {
                size--;
                bottom = (++bottom) % data.length;
                return true;
            } else {
                return false;
            }

        }

        public int Front() {
            if (size > 0) {
                return data[bottom];
            } else {
                return -1;
            }
        }

        public int Rear() {
            if (size > 0) {
                if (top == 0)
                    return data[data.length - 1];
                else
                    return data[top - 1];
            } else {
                return -1;
            }

        }

        public boolean isEmpty() {
            return size == 0;

        }

        public boolean isFull() {
            return size == data.length;
        }
    }

    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */
//IMPORTANT!! Submit Code Region End(Do not remove this line)
    public static void main(String[] args) {
        System.out.println(-1 % 5);
    }
}
