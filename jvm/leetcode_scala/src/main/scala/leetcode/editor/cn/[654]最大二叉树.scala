package leetcode.editor.cn

object MaximumBinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    def constructMaximumBinaryTree(nums: Array[Int]): TreeNode =
      build(nums, 0, nums.length - 1)

    private def build(nums: Array[Int], from: Int, to: Int): TreeNode =
      if from > to then null
      else
        var index = -1
        var maxVal = Int.MinValue
        for (i <- from to to)
          if maxVal < nums(i) then
            maxVal = nums(i)
            index = i

        val root = TreeNode(maxVal)
        root.left = build(nums, from, index - 1)
        root.right = build(nums, index + 1, to)

        root

  }
//leetcode submit region end(Prohibit modification and deletion)

}
