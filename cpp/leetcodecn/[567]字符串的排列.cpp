#include <vector>
#include "util.h"
#include <map>
#include <stack>
#include <string>
#include <unordered_map>
#include <utility>
#include <memory>
#include <iterator>
using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool checkInclusion(string t, string s) {
        unordered_map<char,int> need;
        for (auto c : t) need[c]++;

        unordered_map<char,int> window;
        int valid = 0;
        int left = 0;
        int right = 0;

        while (right < s.size()){
            auto c = s[right];
            ++right;

            if (need.count(c)) {
                window[c]++;
                if (window[c] == need[c]) ++valid;
            }

            while (right - left >= t.size()){
                if (valid == need.size())
                    return true;

                auto d = s[left];
                ++left;

                if (need.count(d)) {
                    if (window[d] == need[d]) --valid;
                    window[d]--;
                }
            }
        }

        return false;

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv,char *argc[]){
    Solution s;
    s.checkInclusion("ab","eidboaoo");
}