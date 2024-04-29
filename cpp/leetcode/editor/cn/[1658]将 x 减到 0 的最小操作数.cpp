#include <vector>
#include "util.h"
#include <map>
#include <stack>
#include <string>
#include <utility>
#include <memory>
#include <iterator>
#include <unordered_map>
#include <algorithm>
#include <numeric>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int minOperations(vector<int> &nums, int x) {
        return slidingWindow(nums, x);
    }

    int slidingWindow(vector<int> &nums, int x) {
        auto target = accumulate(nums.begin(), nums.end(), 0) - x;
        auto maxLength = numeric_limits<int>::min();
        auto left = nums.begin();
        auto right = nums.begin();
        auto accum = 0;

        while (right != nums.end()) {
            auto v = *right;
            right++;

            accum += v;
            while (accum > target && left != right) {
                auto u = *left;
                left++;
                accum -= u;
            }
            if (accum == target)
                maxLength = max(maxLength, (int) distance(left, right));

        }
        if (maxLength == numeric_limits<int>::min())
            return -1;
        else
            return nums.size() - maxLength;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}