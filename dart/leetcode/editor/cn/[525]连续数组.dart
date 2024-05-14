//leetcode submit region begin(Prohibit modification and deletion)
import 'dart:math';

class Solution {
  int findMaxLength(List<int> nums) {
    List<int> preSum = List<int>.filled(nums.length + 1, 0);
    for (int i = 1; i < preSum.length; ++i) {
      preSum[i] = preSum[i - 1] + (nums[i - 1] == 0 ? -1 : 1);
    }

    var cache = <int, int>{};
    int res = 0;

    for (int i = 0; i < preSum.length; ++i) {
      if (cache.containsKey(preSum[i])) {
        res = max(res, i - cache[preSum[i]]!);
      } else {
        cache[preSum[i]] = i;
      }
    }

    return res;
  }
}
//leetcode submit region end(Prohibit modification and deletion)
