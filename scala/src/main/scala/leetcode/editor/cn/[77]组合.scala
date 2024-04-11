package leetcode.editor.cn
object Combinations {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
    def combine(n: Int, k: Int): List[List[Int]] = {
      val result = mutable.ListBuffer[List[Int]]()
      backtrack(n, 0, Nil, result, k)
      result.toList
    }

    private def backtrack(
        n: Int,
        start: Int,
        track: List[Int],
        res: mutable.ListBuffer[List[Int]],
        k: Int
    ): Unit = {
      // base case
      if (track.length == k) {
        // 遍历到第k层，收集当前节点集合
        res.addOne(track)
      } else {
        // 回溯算法标准框架
        for (i <- start until n) {
          backtrack(n,
            i + 1,
            (i + 1) +: track, // 递归前先选择
            res,
            k)
        }
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
