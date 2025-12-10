#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>
#include <unordered_map>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int findMaxLength(vector<int> &nums) {
        auto l = nums.size();
        vector<int> prefix(l + 1, 0);
        for (auto i = 1; i < prefix.size(); i++) {
            prefix[i] = prefix[i - 1] + (nums[i - 1] == 0 ? -1 : 1);
        }

        int res = 0;
        unordered_map<int, int> cache;
        for (auto j = 0; j < prefix.size(); j++) {
            if (cache.contains(prefix[j])) res = max(res, j - cache[prefix[j]]);
            else cache[prefix[j]] = j;
        }

        return res;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}