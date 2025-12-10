#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <memory>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int nthUglyNumber(int n) {
        int p2 = 1, p3 = 1, p5 = 1;
        int product2 = 1, product3 = 1, product5 = 1;

        auto ugly = make_unique<int[]>(n + 1);
        auto p = 1;

        while (p <= n) {
            auto min = std::min(std::min(product2, product3), product5);
            ugly[p] = min;
            p++;

            if (min == product2) {
                product2 = ugly[p2] * 2;
                p2++;
            }
            if (min == product3) {
                product3 = 3 * ugly[p3];
                p3++;
            }
            if (min == product5) {
                product5 = 5 * ugly[p5];
                p5++;
            }
        }

        return ugly[n];
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}