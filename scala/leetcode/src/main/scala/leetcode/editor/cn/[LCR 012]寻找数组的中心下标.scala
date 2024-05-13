package leetcode.editor.cn
object Tvdfij {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def pivotIndex(nums: Array[Int]): Int =
      // preSum(i) == nums[0..i-1]的和
      val preSum = Array.fill[Int](nums.length + 1)(0)
      for i <- 1 to nums.length do preSum(i) = preSum(i - 1) + nums(i - 1)

      val result = nums.indices.find { i =>
        val leftSum = preSum(i)
        val rightSum = preSum.last - preSum(i + 1)
        if leftSum == rightSum then true
        else false
      }

      result match
        case None    => -1
        case Some(i) => i

  }
//leetcode submit region end(Prohibit modification and deletion)

}
