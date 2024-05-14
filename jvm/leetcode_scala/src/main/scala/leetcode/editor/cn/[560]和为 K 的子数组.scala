package leetcode.editor.cn
object SubarraySumEqualsK {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def subarraySum(nums: Array[Int], k: Int): Int = {
      val n = nums.length
      var preSum = 0
      val count = scala.collection.mutable.Map[Int, Int]()
      count.put(0, 1)
      var res = 0

      // 计算nums的前缀和
      for (i <- 1 to n)
        preSum = preSum + nums(i - 1)
        val need = preSum - k
        if count.contains(need) then res = res + count(need)

        count.updateWith(preSum) {
          case None    => Some(1)
          case Some(a) => Some(a + 1)
        }

      res

    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
