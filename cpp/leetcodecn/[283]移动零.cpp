#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <algorithm>
#include <memory>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    void moveZeroes(vector<int> &nums) {
        auto slow = nums.begin();
        for (int & num : nums) {
            if (num != 0) {
                *slow = num;
                slow++;
            }
        }

        for_each(slow, nums.end(), [](auto &v) { v = 0; });

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}