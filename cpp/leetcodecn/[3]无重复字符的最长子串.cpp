#include <vector>
#include "util.h"
#include <map>
#include <stack>
#include <string>
#include <utility>
#include <memory>
#include <iterator>
#include <unordered_map>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        unordered_map<char, int> window;
        auto left = s.begin();
        auto right = s.begin();
        auto res = 0;

        while (right != s.end()) {
            auto c = *right;
            right++;
            window[c]++;
            while (window[c] > 1) {
                auto d = *left;
                left++;
                window[d]--;
            }
            res = std::max(res, (int)std::distance(left, right));
        }
        return res;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}