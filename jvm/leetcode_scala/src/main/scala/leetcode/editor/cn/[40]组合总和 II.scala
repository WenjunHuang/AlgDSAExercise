package leetcode.editor.cn
object CombinationSumIi {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
    def combinationSum2(
        candidates: Array[Int],
        target: Int
    ): List[List[Int]] = {
      candidates.sortInPlace()
      val res = mutable.ListBuffer[List[Int]]()

      backtrack(candidates, target, 0, List(), 0, res)
      res.toList

    }

    def backtrack(
        candidates: Array[Int],
        target: Int,
        start: Int,
        track: List[Int],
        sum: Int,
        res: mutable.ListBuffer[List[Int]]
    ): Unit = {
      if (sum == target) {
        res.addOne(track)
      } else if (sum > target) {} else {
        for (
          i <- start until candidates.length
          if (i == start || (i > start && candidates(i) != candidates(i - 1)))
        ) {
          backtrack(
            candidates,
            target,
            i + 1,
            track :+ candidates(i),
            sum + candidates(i),
            res
          )
        }
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
