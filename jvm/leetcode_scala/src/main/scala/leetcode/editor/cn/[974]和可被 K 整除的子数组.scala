package leetcode.editor.cn
object SubarraySumsDivisibleByK {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def subarraysDivByK(nums: Array[Int], k: Int): Int = {
      val remainderCache = scala.collection.mutable.Map[Int, Int](0 -> 1)
      var preSum = 0
      var res = 0

      for (i <- nums.indices)
        preSum += nums(i)
        val remainder = preSum % k match
          case x if x < 0 => x + k
          case x          => x

        remainderCache.updateWith(remainder) {
          case None => Some(1)
          case Some(a) =>
            res += a
            Some(a + 1)
        }

      res
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
