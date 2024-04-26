package leetcode.editor.cn
object LongestWellPerformingInterval {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def longestWPI(hours: Array[Int]): Int = {
      // preSum[i]是hours[0..i -1]的和
      val preSum = Array.fill[Int](hours.length + 1)(0)
      val valToIndex = scala.collection.mutable.Map[Int, Int]()
      var res = 0

      for (i <- 1 until preSum.length)
        preSum(i) = preSum(i - 1) + (if (hours(i - 1) > 8) 1 else -1)
        valToIndex.getOrElseUpdate(preSum(i), i)
        if preSum(i) > 0 
        then 
          // preSum(i)大于零，表示[0..i]是一个候选时段
          res = math.max(res, i)
        else
          // preSum(i)小于零，那么寻找前面的一个preSum(j)使得preSum(i) - preSum(j) = 1即可满足条件
          valToIndex.get(preSum(i) - 1) match
            case Some(j) => res = math.max(res, i - j)
            case None    => ()

      res
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
