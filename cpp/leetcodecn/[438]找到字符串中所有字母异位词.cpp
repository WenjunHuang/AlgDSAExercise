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
    vector<int> findAnagrams(string s, string p) {
        unordered_map<char, int> need;
        for (auto c: p) need[c]++;

        unordered_map<char, int> window;
        vector<int> res;
        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < s.size()) {
            auto ch = s[right];
            ++right;

            if (need.count(ch)) {
                window[ch]++;
                if (window[ch] == need[ch])
                    ++valid;
            }

            while (right - left >= p.size()) {
                if (valid == need.size()) res.push_back(left);

                auto d = s[left];
                ++left;

                if (need.contains(d)) {
                    if (window[d] == need[d]) --valid;
                    window[d]--;
                }
            }
        }

        return res;

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}