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
    TreeNode *buildTree(vector<int> &inorder, vector<int> &postorder) {
        for (auto i = 0; i < inorder.size(); ++i) _valToIndex[inorder[i]] = i;

        return build(inorder, 0, inorder.size() - 1, postorder, 0, postorder.size() - 1);
    }


private:
    unordered_map<int, int> _valToIndex;

    TreeNode *build(const vector<int> &inorder, int inStart, int inEnd,
                    const vector<int> &postorder, int postStart, int postEnd) {
        if (postStart > postEnd || inStart > inEnd) return nullptr;

        // root 节点的值是后序数组的最后一个元素
        auto rootVal = postorder[postEnd];
        // rootVal 在中序遍历中的位置
        auto index = _valToIndex[rootVal];
        // 左子树的节点数量
        auto leftSize = index - inStart;


        auto root = new TreeNode(rootVal);
        root->left = build(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1);
        root->right = build(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
        return root;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
