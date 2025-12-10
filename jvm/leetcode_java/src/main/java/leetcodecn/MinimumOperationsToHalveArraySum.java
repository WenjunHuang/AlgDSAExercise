package leetcodecn;

import java.util.PriorityQueue;

// [2310]将数组和减半的最少操作次数
class MinimumOperationsToHalveArraySum {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class Solution {
        public int halveArray(int[] nums) {
            double total = 0.0;
            for (var n : nums) {
                total += n;
            }

            var heap = new PriorityQueue<Double>(nums.length, (a, b) -> (b - a) > 0 ? 1 : ((b - a) == 0 ? 0 : -1));
            for (var n : nums) {
                heap.add((double) n);
            }

            double accum = 0;
            var count = 0;
            while (accum < total / 2) {
                var num = heap.poll();
                var t = num / 2;
                accum += t;
                count++;
                heap.add(t);
            }

            return count;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
