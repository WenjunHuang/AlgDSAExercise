#include <vector>
#include "util.h"
#include <iostream>
#include <map>
#include <stack>
#include <string>
#include <utility>
#include <memory>
#include <iterator>
#include <unordered_map>
#include <algorithm>
#include <numeric>
#include <format>
#include <ranges>
#include <queue>
#include <deque>
#include <sstream>

using namespace std;
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

class Serializer {
public:
    explicit Serializer(TreeNode *root) : _root(root) {}

    string serializeWithPreorder() {
        preorderImpl(_root);
        string res;
        for (auto p = _sb.cbegin(); p != _sb.cend(); ++p) {
            res += *p + ",";
        }
        res.pop_back();
        return res;
    }

    string serializeWithPostorder() {
        postorderImpl(_root);
        string res;
        for (auto p = _sb.cbegin(); p != _sb.cend(); ++p) {
            res += *p + ",";
        }
        res.pop_back();
        return res;
    }

    string serializeWithLevelOrder() {
        if (_root == nullptr) return "";
        queue<TreeNode *> q;
        q.push(_root);
        while (!q.empty()) {
            auto cur = q.front();
            q.pop();
            if (cur == nullptr) {
                _sb.emplace_back("#");
            } else {
                _sb.push_back(to_string(cur->val));
                q.push(cur->left);
                q.push(cur->right);
            }
        }

        string res;
        for (auto p = _sb.cbegin(); p != _sb.cend(); ++p) {
            res += *p + ",";
        }
        res.pop_back();
        return res;
    }

private:
    TreeNode *_root;
    vector<string> _sb;

    void preorderImpl(TreeNode *node) {
        if (node == nullptr) _sb.emplace_back("#");
        else {
            _sb.push_back(to_string(node->val));
            preorderImpl(node->left);
            preorderImpl(node->right);
        }
    }

    void postorderImpl(TreeNode *node) {
        if (node == nullptr) _sb.emplace_back("#");
        else {
            postorderImpl(node->left);
            postorderImpl(node->right);
            _sb.push_back(to_string(node->val));
        }
    }
};

class Deserializer {
public:
    explicit Deserializer(const string &encoded) : _nodes(std::move(split(encoded))) {}

    TreeNode *deserializeWithPreorder() {
        if (_nodes.empty()) return nullptr;

        auto first = _nodes.front();
        _nodes.pop_front();
        if (first == "#") {
            return nullptr;
        } else {
            auto v = stoi(first);
            auto node = new TreeNode(v);
            node->left = deserializeWithPreorder();
            node->right = deserializeWithPreorder();
            return node;
        }
    }

    TreeNode *deserializeWithPostorder() {
        if (_nodes.empty()) return nullptr;

        auto last = std::move(_nodes.back());
        _nodes.pop_back();
        if (last == "#") {
            return nullptr;
        }

        auto val = stoi(last);
        auto node = new TreeNode(val);
        node->right = deserializeWithPostorder();
        node->left = deserializeWithPostorder();
        return node;
    }

    TreeNode *deserializeWithLevelOrder() {
        if (_nodes.empty()) return nullptr;
        auto first = _nodes.front();
        _nodes.pop_front();
        if (first == "#") return nullptr;

        auto root = new TreeNode(stoi(first));
        queue<TreeNode *> q;
        q.push(root);

        while (!q.empty()) {
            auto parent = q.front();
            q.pop();
            auto left = _nodes.front();
            _nodes.pop_front();
            if (left != "#") {
                parent->left = new TreeNode(stoi(left));
                q.push(parent->left);
            }

            auto right = _nodes.front();
            _nodes.pop_front();
            if (right != "#") {
                parent->right = new TreeNode(stoi(right));
                q.push(parent->right);
            }
        }
        return root;
    }

private:
    static deque<string> split(const string &str, const char delimiter = ',') {
        deque<string> result;
        istringstream iss(str);
        string token;

        while (getline(iss, token, delimiter)) {
            if (!token.empty()) {
                result.push_back(std::move(token));
            }
        }
        return result;
    }

    deque<string> _nodes;
};

class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode *root) {
        if (root == nullptr) return "#";
        return Serializer(root).serializeWithPostorder();
    }

    // Decodes your encoded data to tree.
    TreeNode *deserialize(string data) {
        return Deserializer(data).deserializeWithPostorder();
    }
};

// Your Codec object will be instantiated and called as such:
// Codec ser, deser;
// TreeNode* ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)


int main() {
    auto str = Serializer(new TreeNode(1, new TreeNode(2), new TreeNode(3))).serializeWithPreorder();
    std::cout << str;
    Deserializer(str).deserializeWithPreorder();
}