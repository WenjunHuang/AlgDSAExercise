package leetcode.editor.cn
object FindPivotIndex{
//leetcode submit region begin(Prohibit modification and deletion)
object Solution {
    def pivotIndex(nums: Array[Int]): Int =
      val preSums = Array.fill[Int](nums.length+1)(0)
      for (i <- 1 until preSums.length)
        preSums(i) = preSums(i-1) + nums(i-1)

      nums.indices
        .find(i => preSums(i) == preSums.last - preSums(i+1))
        .getOrElse(-1)
}
//leetcode submit region end(Prohibit modification and deletion)

}
