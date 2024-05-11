package leetcode.editor.cn

object SortAnArray {
//leetcode submit region begin(Prohibit modification and deletion)
  class MergeSort(val nums: Array[Int]) {
    private val temp = Array.ofDim[Int](nums.length)

    def sort(): Array[Int] =
      sortImpl(0, nums.length - 1)
      nums

    private def sortImpl(low: Int, high: Int): Unit =
      if low < high then
        val mid = low + (high - low) / 2
        sortImpl(low, mid)
        sortImpl(mid + 1, high)
        merge(low, mid, high)

    private def merge(low: Int, mid: Int, high: Int): Unit =
      for i <- low to high do temp(i) = nums(i)

      var i = low
      var j = mid + 1
      for p <- low to high do
        if i == mid + 1 then
          // 左半边的数组已经全部被合并
          nums(p) = temp(j)
          j += 1
        else if j == high + 1 then
          // 右半边的数组已经被全部合并
          nums(p) = temp(i)
          i += 1
        else if temp(j) > temp(i) then
          nums(p) = temp(i)
          i += 1
        else
          nums(p) = temp(j)
          j += 1
  }

  object Solution {
    def sortArray(nums: Array[Int]): Array[Int] =
      MergeSort(nums).sort()
  }
//leetcode submit region end(Prohibit modification and deletion)

}
