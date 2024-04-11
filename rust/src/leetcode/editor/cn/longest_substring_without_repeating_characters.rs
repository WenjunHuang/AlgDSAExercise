use crate::leetcode::editor::cn::util::TreeNode;
use std::rc::Rc;
use std::cell::RefCell;

pub struct Solution;

//leetcode submit region begin(Prohibit modification and deletion)
use std::cmp::max;
use std::collections::HashMap;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let mut window = HashMap::new();
        let mut left = 0;
        let mut right = 0;
        let mut res = 0;
        let chars = s.chars().collect::<Vec<char>>();

        while right < chars.len() {
            let c = chars[right];
            right += 1;

            window.entry(c).and_modify(|e| *e += 1).or_insert(1);

            while window.get(&c).is_some_and(|e| *e > 1) {
                let d = chars[left];
                left += 1;
                window.entry(d).and_modify(|e| *e -= 1);
            }

            res = max(res, right - left);
        }

        return res as i32;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

