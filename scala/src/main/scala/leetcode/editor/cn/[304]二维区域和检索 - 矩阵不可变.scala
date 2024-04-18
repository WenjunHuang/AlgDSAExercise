package leetcode.editor.cn
object RangeSumQuery2dImmutable {
//leetcode submit region begin(Prohibit modification and deletion)
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

      _accum(row2+1)(col2+1) - left - up + leftUp

  }

  /** Your NumMatrix object will be instantiated and called as such: val obj = new NumMatrix(matrix) val param_1 = obj.sumRegion(row1,col1,row2,col2)
    */
//leetcode submit region end(Prohibit modification and deletion)

}
