package leetcode.editor.cn
object UglyNumberIii {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution:
    def nthUglyNumber(n: Int, a: Int, b: Int, c: Int): Int = // 最大公因数
      @annotation.tailrec
      def gcd(a: Long, b: Long): Long = if (b == 0) a else gcd(b, a % b)

      // 最小公倍数
      def lcm(a: Long, b: Long): Long = a * b / gcd(a, b)

      // 计算在x以内能被a、b、c整除的数的个数
      def count(x: Long, a: Long, b: Long, c: Long): Long =
        val setA = x / a
        val setB = x / b
        val setC = x / c
        val setAB = x / lcm(a, b)
        val setAC = x / lcm(a, c)
        val setBC = x / lcm(b, c)
        val setABC = x / lcm(a, lcm(b, c))

        // 集合论定理：A + B + C - A ∩ B - A ∩ C - B ∩ C + A ∩ B ∩ C
        setA + setB + setC - setAB - setAC - setBC + setABC

      // 二分法+并集公式
      @annotation.tailrec
      def impl(leftInc: Int, rightInc: Int): Int =
        if leftInc <= rightInc then
          val mid = leftInc + (rightInc - leftInc) / 2 // 避免溢出
          val countNum = count(mid, a, b, c)
          if countNum < n then impl(mid + 1, rightInc)
          else impl(leftInc, mid - 1)
        else leftInc

      impl(1, 2000000000)

    // 暴力穷举法
    def bruteForce(n: Int, a: Int, b: Int, c: Int): Int =
      var result: Long = 1
      var pa: Long = 1
      var productA: Long = a
      var pb: Long = 1
      var productB: Long = b
      var pc: Long = 1
      var productC: Long = c

      var p = 1
      while p <= n do
        val min = Math.min(productA, Math.min(productB, productC))
        result = min
        p += 1

        if (min == productA)
          pa += 1
          productA = a * pa

        if (min == productB)
          pb += 1
          productB = b * pb

        if (min == productC)
          pc += 1
          productC = c * pc

      result.toInt

//leetcode submit region end(Prohibit modification and deletion)

}
