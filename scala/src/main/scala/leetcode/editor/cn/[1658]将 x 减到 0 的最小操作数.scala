package leetcode.editor.cn
object MinimumOperationsToReduceXToZero {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def minOperations(nums: Array[Int], x: Int): Int =
      prefixSum(nums, x)

    def sliddingWindow(nums: Array[Int], x: Int): Int =
      val target = nums.sum - x
      var left = 0
      var right = 0
      var accum = 0
      var maxLength = Int.MinValue

      while right < nums.length do
        val v = nums(right)
        right += 1
        accum += v

        while accum > target && left < right do
          accum -= nums(left)
          left += 1

        if accum == target then maxLength = maxLength.max(right - left)

      if maxLength == Int.MinValue then -1 else nums.length - maxLength

    def prefixSum(nums: Array[Int], x: Int): Int =
      val target = nums.sum - x

      val preSum = Array.fill[Int](nums.length + 1)(0)

      var maxLength = Int.MinValue
      val count = scala.collection.mutable.Map[Int, Int]()
      count.put(0, 0)

      (0 to nums.length).foreach { i =>
        if i != 0 then
          preSum(i) = preSum(i - 1) + nums(i - 1)

        val need = preSum(i) - target

        if count.contains(need)
        then maxLength = maxLength.max(i - count(need))
        if !count.contains(preSum(i)) then count.put(preSum(i), i)
      }

      if maxLength == Int.MinValue then -1 else nums.length - maxLength
  }
//leetcode submit region end(Prohibit modification and deletion)

  @main
  def minimumOperationsToReduceXToZero(): Unit = {
    val nums = Array(8828, 9581, 49, 9818, 9974, 9869, 9991, 10000, 10000, 10000, 9999, 9993, 9904, 8819, 1231, 6309)
    val x = 134365
    println(Solution.minOperations(nums, x))
  }
}
