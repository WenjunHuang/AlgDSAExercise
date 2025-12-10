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
using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        auto left = nums.begin();
        auto right = nums.begin();
        auto count = 0;
        auto product = 1;

        while (right != nums.end()){
            auto v = *right++;

            product *= v;
            while (product >= k && left != right){
                auto u = *left++;
                product /= u;
            }
            count += (int)distance(left,right);
        }
        return count;

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv,char *argc[]){
}