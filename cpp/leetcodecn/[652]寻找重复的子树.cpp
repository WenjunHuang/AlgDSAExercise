#include <vector>
#include <sstream>
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
    vector<TreeNode *> findDuplicateSubtrees(TreeNode *root) {
        serialize(root);
        return res;
    }

private:
    unordered_map<string, int> subTrees;
    vector<TreeNode *> res;

    string serialize(TreeNode *node) {
        if (node == nullptr) return "#";

        auto left = serialize(node->left);
        auto right = serialize(node->right);
        auto mySelf = left + "," + right + "," + to_string(node->val);

        auto freq = subTrees[mySelf];
        if (freq == 1) res.push_back(node);

        subTrees[mySelf] = freq + 1;

        return mySelf;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
