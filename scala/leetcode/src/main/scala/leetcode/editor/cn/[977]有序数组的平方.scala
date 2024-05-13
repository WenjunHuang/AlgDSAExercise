package leetcode.editor.cn
object SquaresOfASortedArray{
//leetcode submit region begin(Prohibit modification and deletion)
object Solution {
    def sortedSquares(nums: Array[Int]): Array[Int] = {
      val res = Array.ofDim[Int](nums.length)
      var left = 0
      var right = nums.length - 1
      var p = nums.length - 1

      while left <= right do
        if math.abs(nums(left)) > math.abs(nums(right)) then
          res(p) = nums(left) * nums(left)
          left += 1
        else
          res(p)= nums(right) * nums(right)
          right -= 1
        p -= 1

      res
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
