package leetcode.editor.cn
object CorporateFlightBookings {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    class Difference(nums: Array[Int]):
      val diff = Array.fill[Int](nums.length)(0)
      diff(0) = nums(0)
      for (i <- 1 until nums.length) do diff(i) = nums(i) - nums(i - 1)

      def increment(i: Int, j: Int, v: Int) =
        diff(i) += v
        if j + 1 < diff.length then diff(j + 1) -= v

      def result: Array[Int] =
        val res = Array.fill[Int](diff.length)(0)
        res(0) = diff(0)
        for (i <- 1 until diff.length) do res(i) = res(i - 1) + diff(i)
        res
    end Difference
    def corpFlightBookings(bookings: Array[Array[Int]], n: Int): Array[Int] =
      val dif = Difference(Array.fill[Int](n)(0))
      for booking <- bookings do dif.increment(booking(0) - 1, booking(1) - 1, booking(2))
      dif.result
  }
//leetcode submit region end(Prohibit modification and deletion)

}
