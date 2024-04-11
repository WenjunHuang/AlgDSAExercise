package leetcode.editor.cn
object PermutationsIi {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
    def permuteUnique(nums: Array[Int]): List[List[Int]] = {
      nums.sortInPlace()
      val res = mutable.ListBuffer[List[Int]]()
      backtrack(nums, List(), Array.fill(nums.length)(false), res)
      res.toList
    }

    def backtrack(
        sortedNums: Array[Int],
        track: List[Int],
        used: Array[Boolean],
        res: mutable.ListBuffer[List[Int]]
    ): Unit = {
      if (track.length == sortedNums.length) {
        res.append(track.reverse)
      } else {
        var preNum = Int.MinValue
        for (
          i <- sortedNums.indices if (!used(i) && sortedNums(i)!=preNum)
        ) {
          val v = sortedNums(i)
          used(i) = true
          backtrack(sortedNums,v +: track , used, res)
          used(i) = false
          preNum = v
        }
      }

    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
