package leetcodecn;

import java.util.PriorityQueue;

// [215]数组中的第K个最大元素
class KthLargestElementInAnArray {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k > nums.length) return -1;
//            var heap = new PriorityQueue<Integer>(nums.length, (a, b) -> b - a);
//            for (var n : nums) heap.add(n);
//
//            var result = 0;
//            while (k-- > 0) {
//                result = heap.poll();
//            }
//            return result;
            for (var i = nums.length - 1; i >= 0; i--) {
                heapify(nums, i, nums.length - 1);
            }

            var result = 0;
            for (var j = 0; j < k; j++) {
                result = nums[0];
                swap(nums, 0, nums.length - 1 - j);
                heapify(nums, 0, nums.length - 1 - j - 1);
            }
            return result;
        }

        private void heapify(int[] nums, int from, int to) {
            var cur = from;
            while (cur <= to) {
                var left = cur * 2 + 1;
                var right = cur * 2 + 2;

                var largest = cur;
                if (left <= to && nums[left] > nums[cur]) largest = left;
                if (right <= to && nums[right] > nums[largest]) largest = right;

                if (largest == cur) break;

                swap(nums, cur, largest);
                cur = largest;
            }
        }

        private void swap(int[] nums, int one, int two) {
            var t = nums[one];
            nums[one] = nums[two];
            nums[two] = t;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        var result = (new KthLargestElementInAnArray().new Solution()).findKthLargest(new int[]{3,1,2,4},2);
        System.out.println(result);
    }
}
