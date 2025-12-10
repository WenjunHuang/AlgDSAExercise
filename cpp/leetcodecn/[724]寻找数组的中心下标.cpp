#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
#include <iterator>

class Solution {
public:
    int pivotIndex(vector<int> &nums) {
        vector<int> preSums(nums.size()+1,0);

        for (int i = 1; i < preSums.size(); ++i) {
            preSums[i] = preSums[i - 1] + nums[i - 1];
        }

        for (int j = 0;j < nums.size();++j){
            if (preSums[j] == *preSums.rbegin() - preSums[j + 1])
                return j;
        }
        return -1;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}