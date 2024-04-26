package leetcode.editor.cn
object MaximumSizeSubarraySumEqualsK {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def maxSubArrayLen(nums: Array[Int], k: Int): Int = {
      val n = nums.length
      var preSum = 0
      var maxLength = 0
      val preSumToIndex = scala.collection.mutable.Map(0 -> -1)

      for (i <- 0 until n)
        preSum += nums(i)
        preSumToIndex.getOrElseUpdate(preSum, i)
        val need = preSum - k
        preSumToIndex.get(need).foreach(j => maxLength = maxLength.max(i - j))

      maxLength
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
