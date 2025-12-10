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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
private:
    bool foundQ = false;
    bool foundP = false;
public:
    TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q) {
        auto res = find(root, p->val, q->val);
        if (!foundP || !foundQ) return nullptr;
        return res;
    }

    TreeNode *find(TreeNode *node, int val1, int val2) {
        if (node == nullptr) return nullptr;

        auto left = find(node->left, val1, val2);
        auto right = find(node->right, val1, val2);

        if (left != nullptr and right != nullptr) {
            return node;
        }


        if (node->val == val1 || node->val == val2) {
            if (node->val == val1) foundP = true;
            if (node->val == val2) foundQ = true;
            return node;
        }

        return left != nullptr ? left : right;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
