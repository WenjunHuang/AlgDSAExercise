package leetcode.editor.cn
object FindFirstAndLastPositionOfElementInSortedArray {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def searchRange(nums: Array[Int], target: Int): Array[Int] = {
      if (nums.isEmpty) return Array(-1, -1)
      val left = leftBound(nums, target)
      if (left == -1) return Array(-1, -1)
      val right = rightBound(nums, target)
      Array(left, right)
    }

    def leftBound(nums: Array[Int], target: Int): Int = {
      @annotation.tailrec
      def impl(left: Int, right: Int): Int = {
        if (left > right) left
        else {
          val mid = left + (right - left) / 2
          if (nums(mid) == target) impl(left, mid - 1)
          else if (nums(mid) > target) impl(left, mid - 1)
          else impl(mid + 1, right)
        }
      }

      val r = impl(0, nums.length - 1)
      if (r < 0 || r >= nums.length) -1
      else if (nums(r) == target) r
      else -1
    }

    def rightBound(nums: Array[Int], target: Int): Int = {
      @annotation.tailrec
      def impl(left: Int, right: Int): Int = {
        if (left > right) right
        else {
          val mid = left + (right - left) / 2
          if (nums(mid) == target) impl(mid + 1, right)
          else if (nums(mid) > target) impl(left, mid - 1)
          else impl(mid + 1, right)
        }
      }

      val r = impl(0, nums.length - 1)
      if (r < 0 || r >= nums.length) -1
      else if (nums(r) == target) r
      else -1
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
