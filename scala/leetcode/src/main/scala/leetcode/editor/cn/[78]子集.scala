package leetcode.editor.cn
object Subsets {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
    def subsets(nums: Array[Int]): List[List[Int]] = {
      val result = mutable.ListBuffer[List[Int]]()
//      backtrack(nums,0,Nil,result)
      backtrackWithNumPers(nums, 0, Nil, result)
      result.toList
    }

    private def backtrack(
        nums: Array[Int],
        start: Int,
        track: List[Int],
        res: mutable.ListBuffer[List[Int]]
    ): Unit = {
      res.addOne(track)
      for (i <- start until nums.length) {
        backtrack(nums, i + 1, nums(i) +: track, res)
      }
    }

    private def backtrackWithNumPers(
        nums: Array[Int],
        start: Int,
        track: List[Int],
        res: mutable.ListBuffer[List[Int]]
    ): Unit = {
      if (start == nums.length) {
        res.append(track)
      } else {
        backtrackWithNumPers(nums, start + 1, track, res)
        backtrackWithNumPers(nums, start + 1, nums(start) :: track, res)
      }

    }

  }
//leetcode submit region end(Prohibit modification and deletion)

}
