package leetcode.editor.cn

object ConstructBinaryTreeFromPreorderAndInorderTraversal {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    var valToIndex: Map[Int, Int] = Map[Int, Int]()
    def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode =
      if preorder.isEmpty then null
      else
        valToIndex = Map[Int, Int]()
        for i <- inorder.indices do valToIndex = valToIndex + (inorder(i) -> i)

        build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1)

    private def build(preorder: Array[Int], preStart: Int, preEnd: Int, inorder: Array[Int], inStart: Int, inEnd: Int): TreeNode =
      if preStart > preEnd then null
      else
        val rootVal = preorder(preStart)
        val index = valToIndex(rootVal)
        val leftSize = index - inStart

        val root = TreeNode(rootVal)
        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1)
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd)

        root
  }
//leetcode submit region end(Prohibit modification and deletion)

  @main
  def run2(): Unit =
    Solution.buildTree(Array(3, 9, 20, 15, 7), Array(9, 3, 15, 20, 7))
}
