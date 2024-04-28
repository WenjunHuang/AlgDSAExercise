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
    string reverseWords(string s) {
        std::reverse(s.begin(), s.end());
        auto p1 = s.begin();
        auto p2 = s.begin();
        while (p2 != s.end()) {
            if (*p2 == ' ') {
                std::reverse(p1,p2);
                // 跳过后续的空格
                while (*p2 == ' ' && p2 != s.end()) p2++;
                p1 = p2;
            } else {
                p2++;
            }
        }
        std::reverse(p1, p2);

        return s;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv,char *argc[]){
}