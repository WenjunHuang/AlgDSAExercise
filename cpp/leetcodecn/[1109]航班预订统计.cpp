#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Difference {
public:
    explicit Difference(int size) : diff(size, 0) {}

    void increment(int from, int to, int val) {
        diff[from] += val;
        if (to + 1 < diff.size()) {
            diff[to + 1] -= val;
        }
    }

    vector<int> result() {
        vector<int> res(diff.size(), 0);
        res[0] = diff[0];
        for (int i = 1; i < diff.size(); i++)
            res[i] = res[i - 1] + diff[i];
        return res;
    }

private:
    vector<int> diff;
};

class Solution {
public:
    vector<int> corpFlightBookings(vector<vector<int>> &bookings, int n) {
        Difference dif(n);
        for(auto &booking: bookings) {
            dif.increment(booking[0] - 1, booking[1] - 1, booking[2]);
        }
        return dif.result();

    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}