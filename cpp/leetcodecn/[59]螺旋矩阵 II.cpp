#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>
#include <iterator>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<int>> generateMatrix(int n) {
        vector<vector<int>> res;
        std::fill_n(std::back_inserter(res), n, vector<int>(n, 0));

        auto left = 0;
        auto right = n - 1;
        auto upper = 0;
        auto lower = n - 1;

        auto f = 1;
        auto s = n * n;
        while (f <= s) {
            if (upper <= lower) {
                for (auto t = left; t <= right; ++t) {
                    res[upper][t] = f++;
                }
                upper++;
            }

            if (left <= right) {
                for (auto r = upper; r <= lower; ++r) {
                    res[r][right] = f++;
                }
                right--;
            }

            if (upper <= lower) {
                for (auto b = right; b >= left; --b) {
                    res[lower][b] = f++;
                }
                lower--;
            }

            if (upper <= lower) {
                for (auto l = lower; l >= upper; --l) {
                    res[l][left] = f++;
                }
                left++;
            }
        }


        return res;

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}