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
    vector<int> productExceptSelf(vector<int> &nums) {
        // prefix[i]是nums[0..i]的积
        auto l = nums.size();
        vector<int> prefix(l, 0);
        // suffix[i]是nums[i..nums.size()-1]的积
        vector<int> suffix(l, 0);

        prefix[0] = *nums.begin();
        suffix[l - 1] = *nums.rbegin();
        for (auto i = 1; i < l; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
            suffix[l - 1 - i] = suffix[l - i] * nums[l - 1 - i];
        }

        // 计算结果
        vector<int> res(l, 0);
        res[0] = suffix[1];
        res[l - 1] = prefix[l - 2];
        for (auto k = 1; k < l - 1; k++) {
            res[k] = prefix[k - 1] * suffix[k + 1];
        }

        return res;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}