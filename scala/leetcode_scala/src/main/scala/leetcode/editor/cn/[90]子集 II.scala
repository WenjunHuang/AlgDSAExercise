package leetcode.editor.cn
object SubsetsIi {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
    def subsetsWithDup(nums: Array[Int]): List[List[Int]] = {
      nums.sortInPlace()
      val res = mutable.ListBuffer[List[Int]]()
      backtrack(nums, 0, res, List.empty[Int])
      res.toList
    }

    def backtrack(
        sortedNums: Array[Int],
        start: Int,
        res: mutable.ListBuffer[List[Int]],
        track: List[Int]
    ): Unit = {
      // 前序位置，每个节点的值都是一个子集
      res.addOne(track)

      for (
        i <- start until sortedNums.length
        if (i == start || (i > start && sortedNums(i) != sortedNums(i - 1))) // 剪枝，去掉相同的元素分支
      ) {
        backtrack(sortedNums, i + 1, res, track :+ sortedNums(i))
      }

    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
