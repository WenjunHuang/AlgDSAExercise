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
    void reverseString(vector<char>& s) {
        auto left = s.begin();
        auto right = s.end() - 1;

        while (left < right){
            iter_swap(left,right);
            left++;
            right--;
        }

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv,char *argc[]){
}