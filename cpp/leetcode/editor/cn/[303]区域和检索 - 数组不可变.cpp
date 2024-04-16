#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>
#include <iterator>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class NumArray {
public:
    NumArray(vector<int> &nums) {
        std::fill_n(back_inserter(_preSum), nums.size() + 1, 0);
        for (auto i = 1; i < _preSum.size(); ++i) {
            _preSum[i] = _preSum[i - 1] + nums[i - 1];
        }

    }

    int sumRange(int left, int right) {
        return _preSum[right + 1] - _preSum[left];
    }

private:
    vector<int> _preSum;
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * int param_1 = obj->sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}