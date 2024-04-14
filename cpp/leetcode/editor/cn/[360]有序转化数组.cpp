#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>
#include <algorithm>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)

class IterHelper {
public:
    IterHelper(vector<int> &vec, bool reverse) : _reverse(reverse) {
        if (reverse)
            _right = vec.rbegin();
        else
            _left = vec.begin();
    }

    void operator++(int n) {
        if (_reverse)
            _right++;
        else
            _left++;
    }

    int &operator*() {
        if (_reverse)
            return *_right;
        else
            return *_left;
    }

    bool _reverse{};
    vector<int>::iterator _left;
    vector<int>::reverse_iterator _right;
};

class Solution {
public:
    vector<int> sortTransformedArray(vector<int> &nums, int a, int b, int c) {
        vector<int> res(nums.size());

        if (a == 0) {
            if (b >= 0) {
                transform(nums.begin(), nums.end(),
                          res.begin(),
                          [&b, &c](int x) { return b * x + c; });
                return res;
            } else {
                transform(nums.begin(), nums.end(), res.rbegin(), [&b, &c](int x) { return b * x + c; });
                return res;
            }
        } else {
            float mid = -(float) b / (2.0f * (float) a);
            auto left = nums.begin();
            auto right = nums.end() - 1;
            auto p = IterHelper(res, a > 0);

            while (left <= right) {
                if (abs((float) *left - mid) > abs((float) *right - mid)) {
                    *p = a * (*left) * (*left) + b * (*left) + c;
                    left++;
                } else {
                    *p = a * (*right) * (*right) + b * (*right) + c;
                    right--;
                }
                p++;
            }
            return res;
        }
    }

};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
    Solution s;
    vector<int> v = {-4, -2, 2, 4};
    s.sortTransformedArray(v, 1, 3, 5);
}