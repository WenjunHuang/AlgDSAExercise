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
use std::ops::Deref;

impl Solution {
    pub fn flatten(root: &mut Option<Rc<RefCell<TreeNode>>>) {
        match root {
            None => {}
            Some(root) => {
                let mut r = root.borrow_mut();
                Self::flatten(&mut r.left);
                Self::flatten(&mut r.right);

                let left = r.left.take();
                let right = r.right.take();
                r.right = left;

                let mut cur = root.clone();
                while let Some(ref cur_ref) = cur.clone().borrow().right {
                    cur = cur_ref.clone();
                }
                cur.borrow_mut().right = right;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
