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
    vector<int> sortedSquares(vector<int> &nums) {
        vector<int> r(nums.size());

        auto left = nums.begin();
        auto right = nums.end() - 1;
        auto p = r.rbegin();
        while (distance(left, right) >= 0) {
            if (abs(*left) > abs(*right)) {
                *p = *left * *left;
                left++;
            } else {
                *p = *right * *right;
                right--;
            }
            p++;
        }
        return r;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
    Solution s;
    vector<int> v = {-4, -1, 0, 3, 10};
    s.sortedSquares(v);
}