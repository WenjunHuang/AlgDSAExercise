import 'util.dart';

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *   int val;
 *   TreeNode? left;
 *   TreeNode? right;
 *   TreeNode([this.val = 0, this.left, this.right]);
 * }
 */
class Solution {
  int sum = 0;

  TreeNode? convertBST(TreeNode? root) {
    _traverse(root);
    return root;
  }

  void _traverse(TreeNode? node) {
    if (node == null) return;
    _traverse(node.right);
    sum += node.val;
    node.val = sum;
    _traverse(node.left);
  }
}
//leetcode submit region end(Prohibit modification and deletion)
