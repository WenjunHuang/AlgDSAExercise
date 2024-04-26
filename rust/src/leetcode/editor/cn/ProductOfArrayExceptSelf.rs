use crate::leetcode::editor::cn::util::*;
use std::rc::Rc;
use std::cell::RefCell;

struct Solution;

//leetcode submit region begin(Prohibit modification and deletion)
impl Solution {
    pub fn product_except_self(nums: Vec<i32>) -> Vec<i32> {
        let l = nums.len();
        // prefix[i] = nums[0..i]的积
        let mut prefix = vec![0; l];
        // suffix[i] = nums[i..nums.len()-1]的积
        let mut suffix = vec![0; l];

        prefix[0] = nums[0];
        suffix[l - 1] = nums[l - 1];
        for i in 1..l {
            prefix[i] = prefix[i - 1] * nums[i];
            suffix[l - 1 - i] = suffix[l - i] * nums[l - 1 - i];
        }

        let mut res = vec![0; l];
        res[0] = suffix[1];
        res[l - 1] = prefix[l - 2];
        for k in 1 .. (l - 1){
            res[k] = prefix[k - 1] * suffix[k + 1];
        }

        res
    }
}
//leetcode submit region end(Prohibit modification and deletion)

