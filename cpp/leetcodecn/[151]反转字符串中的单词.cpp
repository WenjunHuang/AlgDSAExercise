#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>
#include <iterator>
#include <algorithm>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string reverseWords(string s){
        return reverseWordsWithCopy(std::move(s));
    }

    string reverseWordsWithO1(string s){
        std::reverse(s.begin(),s.end());

        // 去掉前缀空格
        auto p1 = s.begin();
        while (p1 != s.end() && *p1 == ' ') ++p1;
        if (p1 == s.end()) return "";
        std::shift_left(p1,s.end(),p1 - s.begin());

        auto p2 = p1;
        auto p3 = p2;
        while (p2 != s.end()) {
            if (*p2 == ' ') {
                std::reverse(p1,p2);

            } else {
                ++p2;
            }
        }

        return s;
    }

    string reverseWordsWithCopy(string s) {
        std::reverse(s.begin(), s.end());

        // 去掉前缀空格
        auto p1 = s.begin();
        while (p1 != s.end() && *p1 == ' ') ++p1;
        if (p1 == s.end()) return "";

        auto p2 = p1;
        string res;
        while (p2 != s.end()) {
            if (*p2 == ' ') {
                std::reverse_copy(p1, p2, std::back_inserter(res));
                // 跳过后续的空格
                while (p2 != s.end() && *p2 == ' ') ++p2;

                // 如果p2没到末尾，那么插入空格
                if (p2 != s.end()) res.push_back(' ');

                p1 = p2;
            } else {
                p2++;
            }
        }

        // 当没有后缀空格时，存在最后一个单词，需要将其复制进结果
        std::reverse_copy(p1, p2, std::back_inserter(res));

        return res;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}