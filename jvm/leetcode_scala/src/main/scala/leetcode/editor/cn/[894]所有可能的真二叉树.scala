package leetcode.editor.cn

object AllPossibleFullBinaryTrees {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {

    import scala.collection.mutable

    def allPossibleFBT(n: Int): List[TreeNode] =
      memo.clear()
      if n % 2 == 0 then Nil
      else impl(n)

    private def impl(n: Int): List[TreeNode] =
      if n == 1 then List(TreeNode(0))
      else
        memo.get(n) match
          case Some(trees) => trees
          case None =>
            val res = for i <- 1 until n by 2 yield
              val j             = n - i - 1
              val leftSubTrees  = impl(i)
              val rightSubTrees = impl(j)
              for
                left  <- leftSubTrees
                right <- rightSubTrees
              yield
                val root = TreeNode(0)
                root.left = left
                root.right = right
                root
            val result = res.toList.flatten
            memo(n) = result
            result

    private val memo = mutable.Map[Int, List[TreeNode]]()
  }
//leetcode submit region end(Prohibit modification and deletion)

}
