package leetcode.editor.cn

object ConstructBinaryTreeFromInorderAndPostorderTraversal {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) { var value: Int = _value var left: TreeNode = _left var right: TreeNode =
    * _right }
    */
  object Solution {
    import scala.collection.mutable
    val valToIndex: mutable.Map[Int, Int] = mutable.Map[Int, Int]()
    def buildTree(inorder: Array[Int], postorder: Array[Int]): TreeNode =
      valToIndex.clear()
      for i <- inorder.indices do valToIndex.put(inorder(i), i)
      build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1)

    private def build(inorder: Array[Int], inStart: Int, inEnd: Int, postorder: Array[Int], postStart: Int, postEnd: Int): TreeNode =
      if postStart > postEnd then null
      else
        val rootVal = postorder(postEnd)
        val index = valToIndex(rootVal)
        val leftSize = index - inStart

        val node = TreeNode(rootVal)
        node.left = build(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1)
        node.right = build(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1)
        node

  }
//leetcode submit region end(Prohibit modification and deletion)

}
