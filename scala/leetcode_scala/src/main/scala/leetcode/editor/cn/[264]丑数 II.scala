package leetcode.editor.cn
object UglyNumberIi {
//leetcode submit region begin(Prohibit modification and deletion)
object Solution {
    def nthUglyNumber(n: Int): Int = {
      var product2 = 1
      var p2 = 1
      var product3 = 1
      var p3 = 1
      var product5 = 1
      var p5 = 1

      val uglies = Array.ofDim[Int](n + 1)
      var p = 1
      while (p <= n) {
        val min = Math.min(product2,Math.min(product3,product5))
        uglies(p) = min
        p += 1

        if (min == product2) {
          product2 = 2 * uglies(p2)
          p2 += 1
        }
        if (min == product3){
          product3 = 3 * uglies(p3)
          p3 += 1
        }
        if (min == product5){
          product5 = 5*uglies(p5)
          p5 += 1
        }
      }

      uglies(n)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
