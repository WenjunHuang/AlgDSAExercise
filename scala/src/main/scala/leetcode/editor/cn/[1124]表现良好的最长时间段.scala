package leetcode.editor.cn
object LongestWellPerformingInterval {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def longestWPI(hours: Array[Int]): Int = {
      // preSum[i]是hours[0..i -1]的和
      val preSum = Array.fill[Int](hours.length + 1)(0)

      for (i <- 1 until preSum.length)
        preSum(i) = preSum(i - 1) + (if (hours(i - 1) > 8) 1 else -1)

      var res = 0
      for (k <- preSum.indices)
        (preSum.length - 1)
          .to(k, -1)
          .find(j =>
            if preSum(j) - preSum(k) > 0
            then
              res = res.max(j - k)
              true
            else false
          )
      res
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
