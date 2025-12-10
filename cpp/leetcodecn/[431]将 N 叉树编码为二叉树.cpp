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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

class Codec {
public:
    // Encodes an n-ary tree to a binary tree.
    TreeNode *encode(Node *root) {
        if (root == nullptr) return nullptr;
        auto btRoot = new TreeNode(root->val);
        std::queue<std::pair<Node *, TreeNode *>> q({{root, btRoot}});

        while (!q.empty()) {
            auto [ntNode, btNode] = q.front();
            q.pop();

            TreeNode dummy(-1);
            TreeNode *p = &dummy;
            for (auto &child: ntNode->children) {
                auto newBtChild = new TreeNode(child->val);
                p->right = newBtChild;
                p = newBtChild;

                q.push({child, newBtChild});
            }
            btNode->left = dummy.right;
        }
        return btRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    Node *decode(TreeNode *root) {
        if (root == nullptr) return nullptr;

        Node *ntRoot = new Node(root->val);
        std::queue<std::pair<Node *, TreeNode *>> q({{ntRoot, root}});
        while (!q.empty()) {
            auto [ntNode, btNode] = q.front();
            q.pop();

            std::vector<Node *> children;
            auto p = btNode->left;
            while (p != nullptr) {
                auto ntChild = new Node(p->val);
                children.push_back(ntChild);

                q.push({ntChild, p});
                p = p->right;
            }
            ntNode->children = std::move(children);
        }

        return ntRoot;

    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.decode(codec.encode(root));
//leetcode submit region end(Prohibit modification and deletion)
