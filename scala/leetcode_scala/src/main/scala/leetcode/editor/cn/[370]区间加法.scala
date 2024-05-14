package leetcode.editor.cn
object RangeAddition {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution:
    class Difference(nums: Array[Int]):
      val diff = Array.fill[Int](nums.length)(0)
      diff(0) = nums(0)
      for (i <- 1 until nums.length) do diff(i) = nums(i) - nums(i - 1)

      def increment(i: Int, j: Int, v: Int) =
        diff(i) += v
        if j + 1 < diff.length then diff(j + 1) -= v

      def result: Array[Int] =
        val res = Array.fill[Int](diff.length)(0)
        res(0) = diff(0)
        for (i <- 1 until diff.length) do res(i) = res(i - 1) + diff(i)
        res
    end Difference

    def getModifiedArray(length: Int, updates: Array[Array[Int]]): Array[Int] =
      val dif = Difference(Array.fill[Int](length)(0))
      for update <- updates do dif.increment(update(0), update(1), update(2))
      dif.result

  end Solution

//leetcode submit region end(Prohibit modification and deletion)

}
