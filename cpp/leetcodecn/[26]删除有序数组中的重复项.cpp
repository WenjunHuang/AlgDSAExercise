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
    int removeDuplicates(vector<int> &nums) {
        if (nums.empty()) return 0;
        auto fast = nums.begin();
        auto slow = nums.begin();
        while (fast != nums.end()) {
            if (*fast != *slow) {
                slow++;
                *slow = *fast;
            }
            fast++;
        }

        slow++;
        return distance(nums.begin(), slow);
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}