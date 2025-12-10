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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode *invertTree(TreeNode *root) {
        traverse(root);
        return root;
    }

    void traverse(TreeNode *node) {
        if (node != nullptr) {
            traverse(node->left);
            traverse(node->right);
            auto temp = node->left;
            node->left = node->right;
            node->right = temp;
        }
    }
};
//leetcode submit region end(Prohibit modification and deletion)
