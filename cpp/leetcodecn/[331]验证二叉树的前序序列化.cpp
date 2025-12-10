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
#include <sstream>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool isValidSerialization(string preorder) {
        auto nodes = splitString<list<string>>(preorder, ',');
        return deserialize(nodes) && nodes.empty();
    }

private:
    bool deserialize(list<string> &nodes) {
        if (nodes.empty()) return false;

        auto first = nodes.front();
        nodes.pop_front();
        if (first == "#") return true;

        return deserialize(nodes) && deserialize(nodes);
    }

    template<typename Container>
    Container splitString(const typename Container::value_type &str, char delimiter) {
        Container tokens;
        stringstream ss(str);
        typename Container::value_type token;

        while (std::getline(ss, token, delimiter)) {
            tokens.push_back(token);
        }
        return tokens;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
