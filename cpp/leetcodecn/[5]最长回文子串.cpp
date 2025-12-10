#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string longestPalindrome(string s) {
        string_view res;
        for (auto left = s.begin(); left != s.end(); left++) {
            auto p1 = palindrome(s, left, left);
            if (p1.length() > res.length()) res = p1;

            auto p2 = palindrome(s, left, left + 1);
            if (p2.length() > res.length()) res = p2;
        }

        return string(res);
    }

    static string_view palindrome(const string &s, string::iterator left, string::iterator right) {
        while (left >= s.begin() && right <= s.end() && *left == *right) {
            left--;
            right++;
        }
        return {left + 1, right};
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}