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
public:
    int maxDepth(TreeNode *root) {
        return traverse(root, 0);
    }

private:
    int traverse(TreeNode *node, int depth) {
        if (node == nullptr) return depth;
        else {
            auto left_depth = traverse(node->left, depth + 1);
            auto right_depth = traverse(node->right, depth + 1);
            return std::max(left_depth, right_depth);
        }
    }
};
//leetcode submit region end(Prohibit modification and deletion)
