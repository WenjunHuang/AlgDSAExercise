package leetcode.editor.cn
object RemoveDuplicatesFromSortedArray {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def removeDuplicates(nums: Array[Int]): Int = {
      var slow = 0
      var fast = 0
      while (fast < nums.length) {
        if (nums(fast) != nums(slow)) {
          slow += 1
          nums(slow) = nums(fast)
        }
        fast += 1
      }
      slow + 1
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
