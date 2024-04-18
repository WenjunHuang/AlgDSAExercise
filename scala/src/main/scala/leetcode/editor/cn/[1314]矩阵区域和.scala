package leetcode.editor.cn
object MatrixBlockSum {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    def matrixBlockSum(mat: Array[Array[Int]], k: Int): Array[Array[Int]] =
      val m = mat.length
      val n = mat(0).length
      val matrix = NumMatrix(mat)
      val res = Array.fill(m, n)(0)
      for (i <- 0 until m)
        for (j <- 0 until n)
          res(i)(j) = matrix.sumRegion((i - k).max(0), (j - k).max(0), (i + k).min(m - 1), (j + k).min(n - 1))

      res

    class NumMatrix(_matrix: Array[Array[Int]]) {
      val _accum: Array[Array[Int]] =
        _matrix.zipWithIndex
          .foldLeft(Array.fill[Int](_matrix.length + 1, _matrix(0).length + 1)(0)) { (acc, r) =>
            val (row, rowIdx) = r
            row.zipWithIndex.foreach { cell =>
              val (v, col) = cell
              val left = acc(rowIdx + 1)(col)
              val up = acc(rowIdx)(col + 1)
              val leftUp = acc(rowIdx)(col)
              acc(rowIdx + 1)(col + 1) = v + left + up - leftUp
            }
            acc
          }

      def sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int =
        val left = _accum(row2 + 1)(col1)
        val up = _accum(row1)(col2 + 1)
        val leftUp = _accum(row1)(col1)

        _accum(row2 + 1)(col2 + 1) - left - up + leftUp

    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
