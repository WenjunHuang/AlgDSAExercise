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
public:
    TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q) {
        auto min = std::min(p->val, q->val);
        auto max = std::max(p->val, q->val);
        return find(root, min, max);
    }

    TreeNode *find(TreeNode *node, int val1, int val2) {
        if (node == nullptr) return nullptr;

        if (node->val > val2) {
            return find(node->left, val1, val2);
        }
        if (node->val < val1) {
            return find(node->right, val1, val2);
        }

        return node;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
