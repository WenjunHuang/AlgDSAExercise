package leetcode.editor.cn
object ContiguousArray {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def findMaxLength(nums: Array[Int]): Int =
      // preSum(i)是nums[0..i-1]的和
      val preSum = Array.fill[Int](nums.length + 1)(0)
      for (i <- 1 until preSum.length)
        preSum(i) = preSum(i - 1) + nums(i - 1).trans

      val map = scala.collection.mutable.Map[Int, Int]()
      var res = 0
      for (j <- preSum.indices)
        if map.contains(preSum(j)) then res = res.max(j - map(preSum(j)))
        else map(preSum(j)) = j

      res

    extension (num: Int) private def trans: Int = if (num == 0) -1 else 1
  }
//leetcode submit region end(Prohibit modification and deletion)

}
