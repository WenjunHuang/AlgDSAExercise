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
#include <functional>
#include <queue>

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
#include <optional>

class Solution {
public:
    int deepestLeavesSum(TreeNode *root) {
        return bfs(root);
    }

private:
    int bfs(TreeNode *root) {
        if (!root) return 0;

        std::queue<TreeNode *> q({root});
        auto res = 0;

        while (!q.empty()) {
            auto size = q.size();
            auto sum = 0;
            for (auto i = 0; i < size; i++) {
                auto cur = q.front();
                q.pop();

                sum += cur->val;

                if (cur->left) q.push(cur->left);
                if (cur->right) q.push(cur->right);
            }

            res = sum;
        }

        return res;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
