package leetcode.editor.cn
object TwoSumIiInputArrayIsSorted {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def twoSum(numbers: Array[Int], target: Int): Array[Int] = {
      @annotation.tailrec
      def impl(left: Int, right: Int): Array[Int] = {
        (left, right) match {
          case (l, r) if l >= r => Array(-1, -1)
          case (l, r) if numbers(l) + numbers(r) == target =>
            Array(l + 1, r + 1)
          case (l, r) if numbers(l) + numbers(r) < target => impl(l + 1, r)
          case (l, r)                                     => impl(l, r - 1)
        }
      }

      impl(0, numbers.length - 1)

    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
