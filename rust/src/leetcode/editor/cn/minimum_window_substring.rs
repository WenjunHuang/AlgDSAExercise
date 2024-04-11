use crate::leetcode::editor::cn::util::TreeNode;
use std::rc::Rc;
use std::cell::RefCell;

pub struct Solution;

//leetcode submit region begin(Prohibit modification and deletion)

use std::collections::HashMap;

impl Solution {
    pub fn min_window(s: String, t: String) -> String {
        let mut need = HashMap::new();
        let mut window = HashMap::new();
        let mut left = 0;
        let chars = s.chars().collect::<Vec<char>>();
        let mut right = 0;
        let mut valid = 0;
        let mut start = 0;
        let mut len = usize::MAX;

        for c in t.chars() {
            *need.entry(c).or_insert(0) += 1;
        }

        while right < chars.len() {
            // c 是将移入窗口的字符
            let c = chars[right];
            // 扩大窗口
            right += 1;

            // 进行窗口内数据的一系列更新
            if let Some(v) = need.get(&c) {
                let r = window.entry(c).and_modify(|e| *e += 1).or_insert(1);
                if *r == *v {
                    valid += 1;
                }
            }

            // 判断左侧窗口是否要收缩
            while valid == need.len() {
                // 更新最小覆盖字串
                if right - left < len {
                    start = left;
                    len = right - left;
                }

                let d = chars[left];

                // 缩小窗口
                left += 1;

                // 进行窗口内数据的一系列更新
                if need.contains_key(&d) {
                    if window[&d] == need[&d] {
                        valid -= 1;
                    }
                    window.entry(d).and_modify(|e| *e -= 1);
                }
            }
        }

        return if len == usize::MAX {
            "".into()
        } else {
            s[start..start + len].into()
        };
    }
}
//leetcode submit region end(Prohibit modification and deletion)

