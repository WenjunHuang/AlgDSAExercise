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
// Definition for a Node.

class Solution {
public:
    Node *lowestCommonAncestor(Node *p, Node *q) {
        auto a = p;
        auto b = q;

        while (a != b) {
            if (a == nullptr) a = q;
            else a = a->parent;

            if (b == nullptr) b = p;
            else b = b->parent;
        }
        return a;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
