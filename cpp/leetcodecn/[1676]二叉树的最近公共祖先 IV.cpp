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
#include <set>
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
    TreeNode *lowestCommonAncestor(TreeNode *root, vector<TreeNode *> &nodes) {
        set<int> values;
        for (auto node: nodes) {
            values.insert(node->val);
        }
        return find(root, values);
    }

private:
    TreeNode *find(TreeNode *node, const set<int> &values) {
        if (node == nullptr) return nullptr;

        if (values.contains(node->val)) {
            return node;
        }

        auto left = find(node->left, values);
        auto right = find(node->right, values);
        if (left != nullptr && right != nullptr) return node;

        return left != nullptr ? left : right;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
