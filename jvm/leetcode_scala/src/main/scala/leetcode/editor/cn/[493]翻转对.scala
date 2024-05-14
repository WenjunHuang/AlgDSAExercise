package leetcode.editor.cn

object ReversePairs {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    class Counter(val nums: Array[Int]) {
      var count = 0
      val temp = Array.ofDim[Int](nums.length)

      def sort() = sortImpl(0, nums.length - 1)

      def sortImpl(low: Int, high: Int): Unit =
        if low < high then
          val mid = low + (high - low) / 2
          sortImpl(low, mid)
          sortImpl(mid + 1, high)
          merge(low, mid, high)

      def merge(low: Int, mid: Int, high: Int): Unit =
        for i <- low to high do temp(i) = nums(i)

        var end = mid + 1
        for i <- low to mid do
          while end <= high && nums(i).toLong > nums(end).toLong * 2 do end += 1
          count += end - (mid + 1)

        var i = low
        var j = mid + 1
        for p <- low to high do
          if i == mid + 1 then
            nums(p) = temp(j)
            j += 1
          else if j == high + 1 then
            nums(p) = temp(i)
            i += 1
          else if temp(i) > temp(j) then
            nums(p) = temp(j)
            j += 1
          else
            nums(p) = temp(i)
            i += 1
    }
    def reversePairs(nums: Array[Int]): Int =
      val counter = Counter(nums)
      counter.sort()
      counter.count
  }
//leetcode submit region end(Prohibit modification and deletion)

}
