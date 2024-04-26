package leetcode.editor.cn
object MinimumOperationsToReduceXToZero {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def minOperations(nums: Array[Int], x: Int): Int = {
      val prefix = Array.fill(nums.length+1)(0)
      for i <- nums.indices do
        prefix(i+1) = prefix(i) + nums(i)

      val sumToIndex = collection.mutable.Map(0 -> -1)
      var res = Int.MaxValue

      for i <- 0 until prefix.length do

    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
