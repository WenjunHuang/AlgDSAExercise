package leetcode.editor.cn

object ValidateBinarySearchTree{
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
    def isValidBST(root: TreeNode): Boolean = isValidBST(root,null,null)


    def isValidBST(root:TreeNode,min:TreeNode,max:TreeNode):Boolean =
      if root == null then true
      else if min !=null && root.value <= min.value then false
      else if max != null && root.value >= max.value then false
      else
        isValidBST(root.left,min,root) && isValidBST(root.right,root,max)
}
//leetcode submit region end(Prohibit modification and deletion)

}
