package leetcode.editor.cn

object CountOfRangeSum {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    class Counter(nums: Array[Int], val lower: Int, val upper: Int) {
      var count = 0
      val preSum = Array.fill[Long](nums.length + 1)(0)
      val temp = Array.ofDim[Long](preSum.length)
      for i <- nums.indices do preSum(i + 1) = preSum(i) + nums(i)

      def getCount: Int =
        sort(0, preSum.length - 1)
        count

      def sort(low: Int, high: Int): Unit =
        if low < high then
          val mid = low + (high - low) / 2
          sort(low, mid)
          sort(mid + 1, high)
          merge(low, mid, high)

      def merge(low: Int, mid: Int, high: Int): Unit =
        for i <- low to high do temp(i) = preSum(i)

        var start = mid + 1
        var end = mid + 1
        for i <- low to mid do
          while start <= high && preSum(start) - preSum(i) < lower do start += 1

          while end <= high && preSum(end) - preSum(i) <= upper do end += 1

          count += end - start

        var i = low
        var j = mid + 1
        for p <- low to high do
          if j == high + 1 then
            preSum(p) = temp(i)
            i += 1
          else if i == mid + 1 then
            preSum(p) = temp(j)
            j += 1
          else if temp(i) > temp(j) then
            preSum(p) = temp(j)
            j += 1
          else
            preSum(p) = temp(i)
            i += 1
    }
    def countRangeSum(nums: Array[Int], lower: Int, upper: Int): Int =
      Counter(nums,lower,upper).getCount
  }
//leetcode submit region end(Prohibit modification and deletion)

  @main
  def runSum():Unit =
    Solution.countRangeSum(Array(-2,5,-1),-2,2)
}
