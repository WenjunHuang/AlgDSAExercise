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
    bool isValidBST(TreeNode *root) {
        return isValidBST(root,nullptr,nullptr);
    }

    bool isValidBST(TreeNode *node, TreeNode *min, TreeNode *max) {
        if (node == nullptr) return true;
        if (min != nullptr && node->val <= min->val) return false;
        if (max != nullptr && node->val >= max->val) return false;

        return isValidBST(node->left,min,node) && isValidBST(node->right,node,max);
    }
};
//leetcode submit region end(Prohibit modification and deletion)
