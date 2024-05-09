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

    string serialize() {
        impl(_root);
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

    void impl(TreeNode *node) {
        if (node == nullptr) _sb.emplace_back("#");
        else {
            _sb.push_back(to_string(node->val));
            impl(node->left);
            impl(node->right);
        }

    }
};

class Deserializer {
public:
    explicit Deserializer(const string &encoded) : _nodes(std::move(split(encoded))) {}

    TreeNode *deserialize() {
        if (_nodes.empty()) return nullptr;

        auto first = *_nodes.begin();
        _nodes.erase(_nodes.begin());
        if (first == "#") {
            return nullptr;
        } else {
            auto v = stoi(first);
            auto node = new TreeNode(v);
            node->left = deserialize();
            node->right = deserialize();
            return node;
        }

    }

private:
    static list<string> split(const string &str, char delimiter = ',') {
        list<string> result;
        istringstream iss(str);
        string token;

        while (getline(iss, token, delimiter)) {
            if (!token.empty()) {
                result.push_back(token);
            }
        }
        return result;
    }

    list<string> _nodes;
};

class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode *root) {
        if (root == nullptr) return "#";
        return Serializer(root).serialize();
    }

    // Decodes your encoded data to tree.
    TreeNode *deserialize(string data) {
        return Deserializer(data).deserialize();
    }
};

// Your Codec object will be instantiated and called as such:
// Codec ser, deser;
// TreeNode* ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)


int main() {
    auto str = Serializer(new TreeNode(1, new TreeNode(2), new TreeNode(3))).serialize();
    std::cout << str;
    Deserializer(str).deserialize();
}