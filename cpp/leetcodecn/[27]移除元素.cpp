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
    int removeElement(vector<int> &nums, int val) {
        auto fast = nums.begin();
        auto slow = nums.begin();
        while (fast != nums.end()) {
            if (*fast != val) {
                *slow = *fast;
                slow++;
            }
            fast++;
        }
        return static_cast<int>(distance(nums.begin(), slow));

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}