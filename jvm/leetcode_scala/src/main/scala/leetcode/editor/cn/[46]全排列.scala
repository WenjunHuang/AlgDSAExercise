package leetcode.editor.cn
object Permutations {
//leetcode submit region begin(Prohibit modification and deletion)
  object Solution {
    import scala.collection.mutable
    var res = mutable.ListBuffer[List[Int]]()
    def permute(nums: Array[Int]): List[List[Int]] = {
      res = mutable.ListBuffer[List[Int]]()

      val used = Array.fill(nums.length)(false)
      backtrack(nums, Nil, used)
      res.toList
    }

    private def backtrack(
        nums: Array[Int],
        track: List[Int],
        used: Array[Boolean]
    ): Unit = {
      // 触发结束条件
      if (track.length == nums.length) {
        res += track
      } else {
        nums.indices.filterNot(used(_)).foreach { i =>
          // 做选择
          used(i) = true

          // 进入下一层决策树
          backtrack(nums, track :+ nums(i), used)

          // 取消选择
          used(i) = false
        }
      }
    }

  }
//leetcode submit region end(Prohibit modification and deletion)

}
