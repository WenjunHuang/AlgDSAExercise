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
class CBTInserter {
public:
    CBTInserter(TreeNode *root) : root{root} {
        std::queue<TreeNode *> temp({root});
        while (!temp.empty()) {
            auto cur = temp.front();
            temp.pop();
            if (cur->left != nullptr) temp.push(cur->left);
            if (cur->right != nullptr) temp.push(cur->right);

            if (cur->left == nullptr || cur->right == nullptr)
                q.push(cur);
        }
    }

    int insert(int val) {
        auto newBtNode = new TreeNode(val);
        auto cur = q.front();

        if (cur->left == nullptr)
            cur->left = newBtNode;
        else if (cur->right == nullptr) {
            cur->right = newBtNode;
            q.pop();
        }

        q.push(newBtNode);
        return cur->val;
    }

    TreeNode *get_root() {
        return root;
    }

private:
    std::queue<TreeNode *> q;
    TreeNode *root;
};

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter* obj = new CBTInserter(root);
 * int param_1 = obj->insert(val);
 * TreeNode* param_2 = obj->get_root();
 */
//leetcode submit region end(Prohibit modification and deletion)
