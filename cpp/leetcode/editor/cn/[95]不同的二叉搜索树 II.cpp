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
    vector<TreeNode*> generateTrees(int n) {
        return build(1,n);
    }

    vector<TreeNode*> build(int low,int high) {
        if (low > high) {
            return {nullptr};
        }

        vector<TreeNode*> result;
        for (auto i = low;i<=high;i++) {
            auto left = build(low,i - 1);
            auto right = build(i + 1,high);
            for (auto l : left){
                for (auto r: right){
                    auto t = new TreeNode(i);
                    t->left = l;
                    t->right = r;
                    result.push_back(t);
                }
            }
        }
        return result;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
