package leetcode.editor.cn
object SortTransformedArray {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def sortTransformedArray(nums: Array[Int], a: Int, b: Int, c: Int): Array[Int] =
      val res = Array.ofDim[Int](nums.length)
      val vertHyperbola = if (a > 0) true else false
      var p = if (vertHyperbola) nums.length - 1 else 0
      var p1 = 0
      var p2 = nums.length - 1

      while p1 <= p2 do
        val f1 = f(nums(p1), a, b, c)
        val f2 = f(nums(p2), a, b, c)
        if vertHyperbola
        then
          if f1 > f2
          then
            res(p) = f1
            p1 += 1
          else
            res(p) = f2
            p2 -= 1
          p -= 1
        else
          if f1 > f2
          then
            res(p) = f2
            p2 -= 1
          else
            res(p) = f1
            p1 += 1
          p += 1
      res

    private def f(x: Int, a: Int, b: Int, c: Int): Int =
      a * x * x + b * x + c
  }
//leetcode submit region end(Prohibit modification and deletion)

}
