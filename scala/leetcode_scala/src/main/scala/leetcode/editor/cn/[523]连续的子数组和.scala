package leetcode.editor.cn
object ContinuousSubarraySum {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def checkSubarraySum(nums: Array[Int], k: Int): Boolean = {
      val prefix = Array.fill[Int](nums.length + 1)(0)
      for i <- 1 until prefix.length do prefix(i) = prefix(i - 1) + nums(i - 1)

      var res = false
      val valToIndex = scala.collection.mutable.Map[Int, Int]()
      for i <- prefix.indices if !res do
        val mod = prefix(i) % k
        if valToIndex.contains(mod) then
          if i - valToIndex(mod) >= 2 then res = true
        else valToIndex(mod) = i

      res
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
