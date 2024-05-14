from typing import *
from util import *


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        preSum = [0] * (len(nums) + 1)
        for i in range(1, len(preSum)):
            preSum[i] = preSum[i - 1] + (-1 if nums[i - 1] == 0 else 1)

        cache = {}
        res = 0
        for i in range(0, len(preSum)):
            if preSum[i] in cache:
                res = max(res, i - cache[preSum[i]])
            else:
                cache[preSum[i]] = i

        return res


# leetcode submit region end(Prohibit modification and deletion)

s = Solution()
s.findMaxLength([0, 1])
