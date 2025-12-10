#include <vector>
#include <queue>
#include "util.h"

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
    int minDepth(TreeNode *root) {
        if (root == nullptr) return 0;

        queue<TreeNode *> q;
        q.push(root);

        int depth = 1;
        while (!q.empty()) {
            auto len = q.size();
            for (auto i = 0; i < len; i++) {
                auto front = q.front();
                q.pop();
                if (front->left == nullptr && front->right == nullptr) return depth;
                if (front->left != nullptr) q.push(front->left);
                if (front->right != nullptr) q.push(front->right);
            }
            depth++;
        }

        return depth;

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}