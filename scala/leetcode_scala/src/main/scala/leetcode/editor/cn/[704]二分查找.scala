package leetcode.editor.cn
object BinarySearch {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def search(nums: Array[Int], target: Int): Int = leftSearch(nums, target)

    def normalSearch(nums: Array[Int], target: Int): Int = {
      @annotation.tailrec
      def impl(left: Int, right: Int): Int = {
        if (left > right) -1
        else {
          val mid = left + (right - left) / 2
          if (nums(mid) == target) mid
          else if (nums(mid) < target) impl(mid + 1, right)
          else impl(left, mid - 1)
        }
      }

      impl(0, nums.length - 1)
    }
    def leftSearch(nums: Array[Int], target: Int): Int = {
      @annotation.tailrec
      def impl(leftInc: Int, rightInc: Int): Int = {
        if (leftInc > rightInc) leftInc
        else {
          val mid = leftInc + (rightInc - leftInc) / 2
          if (nums(mid) == target)
            impl(leftInc, mid - 1)
          else if (nums(mid) > target)
            impl(leftInc, mid - 1)
          else
            impl(mid + 1, rightInc)
        }
      }

      val r = impl(0, nums.length - 1)
      if (r < 0 || r >= nums.length) -1
      else if (nums(r) != target) -1
      else r
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
