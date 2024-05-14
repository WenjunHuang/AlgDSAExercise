package leetcode.editor.cn

object FlattenBinaryTreeToLinkedList{
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
 *   var value: Int = _value
 *   var left: TreeNode = _left
 *   var right: TreeNode = _right
 * }
 */
object Solution {
    def flatten(root: TreeNode): Unit = {
      if root != null then
        flatten(root.left)
        flatten(root.right)

        val left = root.left
        val right = root.right

        root.left = null
        root.right = left

        var p = root
        while p.right != null do
          p = p.right

        p.right = right

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
