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
    void merge(vector<int> &nums1, int m, vector<int> &nums2, int n) {
        auto p1 = nums1.rbegin() + n;
        auto p2 = nums2.rbegin();
        auto insert = nums1.rbegin();
        while (p1 != nums1.rend() && p2 != nums2.rend()) {
            if (*p1 > *p2) {
                *insert = *p1;
                p1++;
            } else {
                *insert = *p2;
                p2++;
            }
            insert++;
        }

        while (p2 != nums2.rend()) {
            *insert = *p2;
            insert++;
            p2++;
        }
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}