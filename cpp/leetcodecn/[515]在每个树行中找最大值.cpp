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
#include <queue>

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
    vector<int> largestValues(TreeNode *root) {
        if (root == nullptr) return {};

        vector<int> res;
        queue<TreeNode *> q({root});
        while (!q.empty()) {
            auto size = q.size();
            auto maxOfLevel = numeric_limits<int>::min();
            for (auto i = 0; i < size; i++) {
                auto cur = q.front();
                q.pop();
                maxOfLevel = std::max(maxOfLevel, cur->val);

                if (cur->left != nullptr) q.push(cur->left);
                if (cur->right != nullptr)q.push(cur->right);
            }
            res.push_back(maxOfLevel);
        }

        return res;

    }
};
//leetcode submit region end(Prohibit modification and deletion)
