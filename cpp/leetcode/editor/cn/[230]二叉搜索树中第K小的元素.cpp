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
class Answer {
public:
    Answer(TreeNode* root,int k):root(root),k(k){}

    int solution() {
        traverse(root);
        return res;
    }

    void traverse(TreeNode *node) {
        if (node == nullptr) return;
        traverse(node->left);

        ++rank;
        if (rank == k){
            res = node->val;
            return;
        } else
            traverse(node->right);

    }
private:
    int res = 0;
    int rank = 0;
    TreeNode* root;
    int k;
};
class Solution {
public:
    int kthSmallest(TreeNode* root, int k) {
        return Answer(root,k).solution();
    }
};
//leetcode submit region end(Prohibit modification and deletion)
