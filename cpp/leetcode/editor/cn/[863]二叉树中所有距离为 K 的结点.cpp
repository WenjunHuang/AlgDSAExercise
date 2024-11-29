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
#include <set>

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
    vector<int> distanceK(TreeNode *root, TreeNode *target, int k) {
        unordered_map<int, TreeNode *> parentRel({{root->val, nullptr}});
        function<void(TreeNode *, TreeNode *)> traverse = [&](TreeNode *node, TreeNode *parent) {
            if (node == nullptr) return;
            parentRel[node->val] = parent;
            traverse(node->left, node);
            traverse(node->right, node);
        };

        traverse(root,nullptr);

        vector<int> res;
        set<int> visited({target->val});
        queue<TreeNode *> q({target});
        int distance = 0;

        while (!q.empty()) {
            auto size = q.size();
            for (auto i = 0; i < size; i++) {
                auto cur = q.front();
                q.pop();

                if (distance == k) {
                    res.push_back(cur->val);
                }

                auto parent = parentRel[cur->val];
                if (parent != nullptr && !visited.contains(parent->val)) {
                    visited.insert(parent->val);
                    q.push(parent);
                }

                if (cur->left != nullptr && !visited.contains(cur->left->val)) {
                    visited.insert(cur->left->val);
                    q.push(cur->left);
                }
                if (cur->right != nullptr && !visited.contains(cur->right->val)) {
                    visited.insert(cur->right->val);
                    q.push(cur->right);
                }
            }
            distance++;
        }

        return res;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
