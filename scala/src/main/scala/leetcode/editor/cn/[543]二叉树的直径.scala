package leetcode.editor.cn

import scala.annotation.static

object DiameterOfBinaryTree {
  //leetcode submit region begin(Prohibit modification and deletion)

  /** Definition for a binary tree node.
    * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    * var value: Int = _value
    * var left: TreeNode = _left
    * var right: TreeNode = _right
    * }
    */
  object Solution {

    def diameterOfBinaryTree(root: TreeNode): Int = {
      val (_,dia) = maxDepth(root,0)
      dia
    }

    def maxDepth(root: TreeNode,currentMaxDiameter:Int): (Int,Int) = {
      if (root == null) (0,currentMaxDiameter)
      else {
        val (leftMax,leftDiameter) = maxDepth(root.left,currentMaxDiameter)
        val (rightMax,rightDiameter) = maxDepth(root.right,currentMaxDiameter)
        (1 + math.max(leftMax, rightMax),math.max(leftMax + rightMax,math.max(leftDiameter,rightDiameter)))
      }
    }
  }

  //leetcode submit region end(Prohibit modification and deletion)
  @main
  def run(): Unit = {
    val leaf = TreeNode(2)
    val root = TreeNode(1, leaf, null)
    println(Solution.diameterOfBinaryTree(root))
  }
}
