#include <vector>
#include "util.h"
#include <map>
#include <unordered_map>
#include <stack>
#include <string>
#include <utility>
#include <memory>
#include <iterator>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string minWindow(string s, string t) {
        unordered_map<char, int> need, window;
        for (auto c: t) need[c]++;

        int left = 0;
        int right = 0;
        int valid = 0;
        int start = 0, len = numeric_limits<int>::max();
        while (right < s.size()) {
            auto c = s[right];
            ++right;

            if (need.count(c)) {
                window[c]++;
                if (window[c] == need[c])
                    ++valid;
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                auto d = s[left];
                ++left;
                if (need.count(d)) {
                    if (window[d] == need[d])
                        --valid;
                    window[d]--;
                }
            }
        }

        return len == numeric_limits<int>::max() ? "" : s.substr(start, len);

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}