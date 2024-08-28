import 'dart:math';

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
  int maxDiameter = 0;

  int diameterOfBinaryTree(TreeNode? root) {
    maxDepth(root);
    return maxDiameter;
  }

  int maxDepth(TreeNode? root) {
    if (root == null) return 0;
    var leftDepth = maxDepth(root.left);
    var rightDepth = maxDepth(root.right);
    var myDiameter = leftDepth + rightDepth;
    maxDiameter = max(maxDiameter, myDiameter);

    return max(leftDepth, rightDepth) + 1;
  }
}
//leetcode submit region end(Prohibit modification and deletion)
