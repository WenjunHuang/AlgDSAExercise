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
class Counter {
public:
    Counter(const vector<int> &nums, int lower, int upper) : preSum(nums.size() + 1, 0),
                                                             temp(nums.size() + 1, 0),
                                                             lower(lower), upper(upper) {
        for (auto i = 0; i < nums.size(); i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
    }

    int getCount() {
        sort(0, preSum.size() - 1);
        return count;
    }

private:
    vector<int64_t> preSum;
    vector<int64_t> temp;
    int lower;
    int upper;
    int count = 0;

    void sort(int low, int high) {
        if (low >= high) return;

        auto mid = low + (high - low) / 2;
        sort(low, mid);
        sort(mid + 1, high);
        merge(low, mid, high);
    }

    void merge(int low, int mid, int high) {
        for (auto i = low; i <= high; ++i)
            temp[i] = preSum[i];

        auto start = mid + 1;
        auto end = mid + 1;
        for (auto k = low; k <= mid; ++k) {
            while (start <= high && preSum[start] - preSum[k] < lower)++start;
            while (end <= high && preSum[end] - preSum[k] <= upper) ++end;
            count += (end - start);
        }

        auto i = low;
        auto j = mid + 1;
        for (auto p = low; p <= high; p++) {
            if (j == high + 1) {
                preSum[p] = temp[i++];
            } else if (i == mid + 1) {
                preSum[p] = temp[j++];
            } else if (temp[i] > temp[j]) {
                preSum[p] = temp[j++];
            } else {
                preSum[p] = temp[i++];
            }
        }

    }
};

class Solution {
public:
    int countRangeSum(vector<int> &nums, int lower, int upper) {
        return Counter(nums, lower, upper).getCount();
    }
};
//leetcode submit region end(Prohibit modification and deletion)
#include <iostream>

int main() {
    vector<int> nums = {{-2, 5, -1}};
    cout << Solution().countRangeSum(nums, -2, 2);
}