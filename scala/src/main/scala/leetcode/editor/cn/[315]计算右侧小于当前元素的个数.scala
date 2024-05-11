package leetcode.editor.cn

object CountOfSmallerNumbersAfterSelf {
//leetcode submit region begin(Prohibit modification and deletion)

  object Solution {
    case class Pair(value: Int, id: Int)

    class Counter(nums: Array[Int]) {
      val arr = nums.zipWithIndex.map { case (value, index) => Pair(value, index) }
      val temp = Array.ofDim[Pair](nums.length)
      val count = Array.fill[Int](nums.length)(0)

      def sort() = sortImpl(0, arr.length - 1)

      def sortImpl(low: Int, high: Int): Unit =
        if low < high then
          val mid = low + (high - low) / 2
          sortImpl(low, mid)
          sortImpl(mid + 1,high)
          merge(low, mid, high)

      def merge(low: Int, mid: Int, high: Int): Unit =
        for i <- low to high do temp(i) = arr(i)

        var i = low
        var j = mid + 1
        for p <- low to high do
          if i == mid + 1 then
            arr(p) = temp(j)
            j += 1
          else if j == high + 1 then
            arr(p) = temp(i)
            i += 1
            count(arr(p).id) += j - mid - 1
          else if temp(i).value > temp(j).value then
            arr(p) = temp(j)
            j += 1
          else
            arr(p) = temp(i)
            i += 1
            count(arr(p).id) += j - mid - 1
    }

    def countSmaller(nums: Array[Int]): List[Int] =
      val counter = Counter(nums)
      counter.sort()
      counter.count.toList
  }
//leetcode submit region end(Prohibit modification and deletion)

}
