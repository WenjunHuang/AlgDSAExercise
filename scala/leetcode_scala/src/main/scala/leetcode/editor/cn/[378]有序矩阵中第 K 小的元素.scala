package leetcode.editor.cn
object KthSmallestElementInASortedMatrix {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution:
    import scala.collection.mutable
    type Row = Int
    type Col = Int
    type Item = (Int, Row, Col)
    given Ordering[Item] = Ordering.by[Item, Int](_._1).reverse
    def kthSmallest(matrix: Array[Array[Int]], k: Int): Int =
      val queue =
        mutable.PriorityQueue.empty[Item]

      for (i <- matrix.indices)
        queue.enqueue((matrix(i)(0), i, 0))

      @annotation.tailrec
      def impl(count:Int,lastResult:Int): Int =
        if count < k && queue.nonEmpty then
          queue.dequeue() match
            case (value, row, col) =>
              if (col + 1 < matrix(row).length) queue.enqueue((matrix(row)(col + 1), row, col + 1))
              impl(count + 1,value)
        else lastResult

      impl(0,0)

//leetcode submit region end(Prohibit modification and deletion)

}
