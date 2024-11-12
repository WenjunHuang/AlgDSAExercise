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
    TreeNode *constructFromPrePost(vector<int> &preorder, vector<int> &postorder) {
        for (auto i = 0; i < postorder.size(); ++i) _valToIndex[postorder[i]] = i;
        return build(preorder, 0, preorder.size() - 1, postorder, 0, postorder.size() - 1);

    }

private:
    std::map<int, int> _valToIndex;

    TreeNode *build(const vector<int> &preorder, int preorderStart, int preorderEnd, const vector<int> &postorder,
                    int postorderStart, int postorderEnd) {
        if (preorderStart > preorderEnd || postorderStart > postorderEnd) return nullptr;
        if (preorderStart == preorderEnd) return new TreeNode(preorder[preorderStart]);

        auto rootVal = preorder[preorderStart];
        auto leftRootVal = preorder[preorderStart+1];
        auto index = _valToIndex[leftRootVal];
        auto leftSize = index - postorderStart + 1;

        auto node = new TreeNode(rootVal);
        node->left = build(preorder, preorderStart + 1, preorderStart + leftSize,
                           postorder, postorderStart, index);
        node->right = build(preorder, preorderStart + leftSize + 1, preorderEnd,
                            postorder, index + 1, postorderEnd - 1);
        return node;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
