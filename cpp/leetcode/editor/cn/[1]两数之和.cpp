#include "util.h"
#include <vector>
#include <iterator>
#include <algorithm>
#include <unordered_map>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<int> twoSum(vector<int> &nums, int target) {
        unordered_map<int, int> cache;
        for (auto p = nums.begin(); p != nums.end(); p++) {
            auto r = target - *p;
            if (cache.contains(r)) {
                return {cache[r], (int) distance(nums.begin(), p)};
            }
            cache[*p] = (int)distance(nums.begin(),p);
        }
        return {-1, -1};
    }
};
//leetcode submit region end(Prohibit modification and deletion)
