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

private:
    unordered_map<int, int> valToIndex;

    TreeNode *
    build(const vector<int> &preorder, int preStart, int preEnd, const vector<int> &inorder, int inStart, int inEnd) {
        if (preStart > preEnd) return nullptr;

        auto rootVal = preorder[preStart];
        auto index = valToIndex[rootVal];
        auto leftSize = index - inStart;

        auto root = new TreeNode(rootVal);
        root->left = build(preorder, preStart + 1, preStart + leftSize,
                           inorder, inStart, index - 1);
        root->right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }

public:
    TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder) {
        for (auto i = 0; i < inorder.size(); ++i) valToIndex[inorder[i]] = i;
        return build(preorder, 0, preorder.size() - 1, inorder, 0, inorder.size() - 1);
    }
};
//leetcode submit region end(Prohibit modification and deletion)
