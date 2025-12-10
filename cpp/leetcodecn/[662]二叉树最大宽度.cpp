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
    int widthOfBinaryTree(TreeNode *root) {
        if (root == nullptr) return 0;
        else return bfs(root);
    }

    int bfs(TreeNode *root) {
        int maxWidth = 1;
        queue<Point> q;
        q.push(Point{root, 1});
        while (!q.empty()) {
            auto size = q.size();
            auto start = 0, end = 0;

            for (auto i = 0; i < size; i++) {
                auto [cur, id] = q.front();
                q.pop();

                if (i == 0) {
                    start = id;
                }
                if (i == size - 1) {
                    end = id;
                }

                if (cur->left != nullptr)
                    q.emplace(cur->left, id * 2);
                if (cur->right != nullptr)
                    q.emplace(cur->right, id * 2 + 1);
            }

            maxWidth = max(maxWidth, end - start + 1);
        }
        return maxWidth;
    }

    int dfs(TreeNode *root) {
        vector<int> firstIdOfLevel;
        int maxWidth = 1;
        function<void(TreeNode *, int, int)> traverse = [&](TreeNode *node, int id, int depth) {
            if (node == nullptr) return;
            if (firstIdOfLevel.size() == depth) {
                firstIdOfLevel.push_back(id);
            } else {
                maxWidth = max(maxWidth, id - firstIdOfLevel[depth] + 1);
            }

            traverse(node->left, id * 2, depth + 1);
            traverse(node->right, id * 2 + 1, depth + 1);
        };

        traverse(root, 1, 0);
        return maxWidth;
    }

private:
    struct Point {
        TreeNode *node;
        int id;
    };
};
//leetcode submit region end(Prohibit modification and deletion)
