package leetcode.editor.cn
object CarPooling {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    class Difference(size: Int):
      val diff = Array.fill[Int](size)(0)

      def increment(i: Int, j: Int, v: Int) =
        diff(i) += v
        if j + 1 < diff.length then diff(j + 1) -= v

      def result: Array[Int] =
        val res = Array.fill[Int](diff.length)(0)
        res(0) = diff(0)
        for (i <- 1 until diff.length) do res(i) = res(i - 1) + diff(i)
        res
    end Difference
    def carPooling(trips: Array[Array[Int]], capacity: Int): Boolean = {
      val dif = Difference(1001)
      for (trips <- trips) do
        dif.increment(trips(1), trips(2) - 1, trips(0))
        
      val res = dif.result
      res.forall(_ <= capacity)
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
