use crate::leetcode::editor::cn::util::*;
struct Solution;
//leetcode submit region begin(Prohibit modification and deletion)
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
use std::cmp::max;

impl Solution {
    pub fn construct_maximum_binary_tree(nums: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        Self::build(&nums[..])
    }

    fn build(nums: &[i32]) -> Option<Rc<RefCell<TreeNode>>> {
        if nums.is_empty() {
            return None;
        }
        let mut max_val = i32::MIN;
        let mut index = 0;
        for (i,&num) in nums.iter().enumerate() {
            if num > max_val {
                max_val = num;
                index = i;
            }
        }

        let left = Self::build(&nums[..index]);
        let right = Self::build(&nums[index+1..]);

        Some(Rc::new(RefCell::new(TreeNode {
            val: max_val,
            left,
            right,
        })))
    }
}
//leetcode submit region end(Prohibit modification and deletion)
