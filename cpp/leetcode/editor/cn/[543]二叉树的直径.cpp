#include <vector>
#include "util.h"
#include <map>
#include <stack>
#include <string>
#include <utility>
#include <memory>
#include <iterator>
#include <unordered_map>
#include <algorithm>
#include <numeric>

using namespace std;
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
private:
    int maxDiameter = 0;
public:
    int diameterOfBinaryTree(TreeNode *root) {
        maxDepth(root);
        return maxDiameter;
    }

private:

    int maxDepth(TreeNode *root) {
        if (root == nullptr) return 0;

        auto leftMax = maxDepth(root->left);
        auto rightMax = maxDepth(root->right);
        auto myDiameter = leftMax + rightMax;
        maxDiameter = max(myDiameter, maxDiameter);
        return 1 + max(leftMax, rightMax);
    }
};
//leetcode submit region end(Prohibit modification and deletion)
