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
#include <set>
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
    TreeNode *correctBinaryTree(TreeNode *root) {
        return withIteration(root);
    }

    struct Item {
        TreeNode *&position;
        TreeNode *node;
    };

    static TreeNode *withIteration(TreeNode *root) {
        if (root == nullptr) return nullptr;

        queue<Item> queue;
        set<TreeNode *> visited;
        TreeNode *dump;
        queue.emplace(dump, root);
        bool found = false;

        while (!queue.empty()) {
            auto [position, node] = queue.front();
            queue.pop();

            if (!found && node->right != nullptr && visited.contains(node->right)) {
                if (position != nullptr)
                    position = nullptr;
                found = true;
            } else {
                visited.insert(node);
                if (node->right != nullptr)
                    queue.emplace(node->right, node->right);
                if (node->left != nullptr)
                    queue.emplace(node->left, node->left);
            }
        }
        return root;
    }

    TreeNode *correctBinaryTreeWithRecursion(TreeNode *root) {
        if (root == nullptr) return nullptr;

        if (root->right != nullptr && _visited.contains(root->right)) return nullptr;

        _visited.insert(root);
        root->right = correctBinaryTree(root->right);
        root->left = correctBinaryTree(root->left);
        return root;
    }

private:
    set<TreeNode *> _visited;
};
//leetcode submit region end(Prohibit modification and deletion)
