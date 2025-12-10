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
    vector<int> spiralOrder(const vector<vector<int>>& matrix) {
        auto row = matrix.size();
        auto col = matrix[0].size();
        auto total = row * col;

        int upperBound = 0;
        int rightBound = col - 1;
        int leftBound = 0;
        int lowerBound = row - 1;

        vector<int> res;
        while (res.size() < total) {
            if (upperBound <= lowerBound) {
                for (auto t = leftBound; t <= rightBound; t++) {
                    res.push_back(matrix[upperBound][t]);
                }
                upperBound++;
            }

            if (leftBound <= rightBound) {
                for (auto l = upperBound; l <= lowerBound; l++) {
                    res.push_back(matrix[l][rightBound]);
                }
                rightBound--;
            }

            if (upperBound <= lowerBound) {
                for (auto b = rightBound; b >= leftBound; b--) {
                    res.push_back(matrix[lowerBound][b]);
                }
                lowerBound--;
            }

            if (leftBound <= rightBound) {
                for (auto l = lowerBound; l >= upperBound; l--) {
                    res.push_back(matrix[l][leftBound]);
                }
                leftBound++;
            }
        }

        return res;

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv,char *argc[]){
    Solution s;
    s.spiralOrder({{1,2,3},{4,5,6},{7,8,9}});
}