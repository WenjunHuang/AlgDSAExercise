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
    vector<int> twoSum(vector<int> &numbers, int target) {
        auto left = numbers.begin();
        auto right = numbers.end() - 1;
        while (left < right) {
            auto sum = *left + *right;
            if (sum == target) {
                return {static_cast<int>(distance(numbers.begin(), left) + 1),
                        static_cast<int>(distance(numbers.begin(), right) + 1)};
            } else if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            }
        }
        return {-1, -1};
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}