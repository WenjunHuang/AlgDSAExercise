use crate::leetcode::editor::cn::util::*;
struct Solution;
//leetcode submit region begin(Prohibit modification and deletion)
use std::collections::HashMap;
struct Answer {
    memo: HashMap<(i32,i32),i32>
}

impl Answer {
    fn new()->Self {
        Answer {
            memo: HashMap::new()
        }
    }

    fn count(&mut self,start:i32,end:i32) -> i32 {
        if start > end {
            1
        } else {
            if self.memo.contains_key(&(start,end)) {
                return self.memo[&(start,end)];
            }
            let mut sum = 0;
            for mid in start..=end {
                let left = self.count(start,mid-1);
                let right = self.count(mid+1,end);
                sum += left * right;
            }
            self.memo.insert((start,end),sum);
            sum
        }
    }
}
impl Solution {
    pub fn num_trees(n: i32) -> i32 {
        let mut answer = Answer::new();
        answer.count(1,n)
    }
}
//leetcode submit region end(Prohibit modification and deletion)
