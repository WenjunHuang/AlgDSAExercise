namespace leetcode.editor.cn
{
    namespace contiguousArray
    {
//leetcode submit region begin(Prohibit modification and deletion)
        public class Solution
        {
            public int FindMaxLength(int[] nums)
            {
                var preSum = new int[nums.Length + 1];
                for (var i = 1; i < preSum.Length; ++i)
                {
                    preSum[i] = preSum[i-1] + (nums[i-1] == 0 ? -1 : 1);
                }

                var map = new Dictionary<int, int>();
                var res = 0;
                for (var j = 0; j < preSum.Length; ++j)
                {
                    if (map.TryGetValue(preSum[j], out var value))
                        res = Math.Max(res, j - value);
                    else
                        map[preSum[j]] = j;
                }

                return res;
            }
        }
//leetcode submit region end(Prohibit modification and deletion)
    }
}