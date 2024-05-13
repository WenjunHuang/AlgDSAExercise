package leetcode.editor.cn
object NQueens {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
    var res = mutable.ListBuffer[List[String]]()
    def solveNQueens(n: Int): List[List[String]] = {
      res.clear()
      val board = mutable.ArrayBuffer.fill(n)("." * n)
      backtrack(board, 0)
      res.toList
    }

    def backtrack(board: mutable.ArrayBuffer[String], row: Int): Unit = {
      if (board.size == row) {
        res.append(board.toList)
      } else {
        for (col <- 0 until board(row).length) {
          // 排除不合法选择
          if (isValid(board, row, col)) {
            // 做选择
            board(row) = board(row).updated(col, 'Q')
            // 进入下一行决策
            backtrack(board, row + 1)
            // 撤销决策
            board(row) = board(row).updated(col, '.')
          }

        }
      }
    }

    def isValid(
        board: mutable.ArrayBuffer[String],
        row: Int,
        col: Int
    ): Boolean = {
      def isQueenAtPosition(i: Int, j: Int): Boolean =
        board(i)(j) == 'Q'
      // 同一列
      def sameColumn = (0 until row).exists(i => isQueenAtPosition(i, col))
      // 左上方
      def upperLeftDiagonal =
        (row - 1 to 0 by -1).zip(col - 1 to 0 by -1).exists { case (i, j) =>
          isQueenAtPosition(i, j)
        }
      // 右上方
      def upperRightDiagonal =
        (row - 1 to 0 by -1).zip(col + 1 until board.length).exists {
          case (i, j) => isQueenAtPosition(i, j)
        }

      !sameColumn && !upperRightDiagonal && !upperLeftDiagonal
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
