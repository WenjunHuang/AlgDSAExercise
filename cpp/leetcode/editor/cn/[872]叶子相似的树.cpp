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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool leafSimilar(TreeNode *root1, TreeNode *root2) {
        LeafContainer cnt1(root1);
        LeafContainer cnt2(root2);

        return std::equal(cnt1.begin(), cnt1.end(), cnt2.begin(), cnt2.end(), [](auto left, auto right) {
            if (left == nullptr || right == nullptr) return false;
            return left->val == right->val;
        });
    }

private:
    class LeafContainer {
    public:
        class LeafIterator {
        public:
            using iterator_category = std::forward_iterator_tag;
            using difference_type = std::ptrdiff_t;
            using value_type = TreeNode *;
            using pointer = value_type *;
            using reference = value_type &;


            LeafIterator(LeafContainer *container, bool end) : container{container}, current{nullptr} {
                if (container != nullptr && !end)
                    this->operator++();
            }

            bool operator==(const LeafIterator &other) const {
                return container == other.container && current == other.current;
            }

            reference operator*() {
                return current;
            }

            LeafIterator &operator++() {
                if (container == nullptr) return *this;
                while (!container->stack.empty()) {
                    auto cur = container->stack.top();
                    container->stack.pop();
                    if (cur->left == nullptr && cur->right == nullptr) {
                        current = cur;
                        return *this;
                    } else {
                        if (cur->right != nullptr) container->stack.push(cur->right);
                        if (cur->left != nullptr) container->stack.push(cur->left);
                    }
                }
                current = nullptr;
                return *this;
            }


        private:
            value_type current;
            LeafContainer *container;
        };

        LeafContainer(TreeNode *root) {
            if (root != nullptr)
                stack.push(root);
        }

        LeafIterator begin() {
            return LeafIterator(this, false);
        }

        LeafIterator end() {
            return LeafIterator(this, true);
        }

    private:
        std::stack<TreeNode *> stack;
    };
};
//leetcode submit region end(Prohibit modification and deletion)

#include <iostream>

int main() {
    auto root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    auto root2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    Solution s;
    std::cout << s.leafSimilar(root1, root2);
}