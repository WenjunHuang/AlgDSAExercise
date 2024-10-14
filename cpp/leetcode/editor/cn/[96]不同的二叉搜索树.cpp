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
    int numTrees(int n) {
//        return (int) catalanNumber(n);
        for (int i = 0; i < n + 1; i++) {
            memo.emplace_back(n + 1, 0);
        }
        return count(1, n);
    }

    int64_t catalanNumber(int n) {
        auto numer = combinatoric(2*n,n);
        auto frag = n + 1;
        return numer / frag;
    }

    int64_t combinatoric(int64_t n, int64_t m) {
        if (n == m || m == 0) return 1;
        return combinatoric(n, m - 1) * (n - m + 1) / m;
    }

    int count(int low, int high) {
        if (low > high) return 1;

        if (memo[low][high] != 0) return memo[low][high];

        int res = 0;
        for (auto mid = low; mid <= high; mid++) {
            int left = count(low, mid - 1);
            int right = count(mid + 1, high);
            res += left * right;
        }
        memo[low][high] = res;
        return res;
    }

private:
    vector<vector<int>> memo;
};
//leetcode submit region end(Prohibit modification and deletion)
