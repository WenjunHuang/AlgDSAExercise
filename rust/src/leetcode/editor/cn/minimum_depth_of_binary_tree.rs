use crate::leetcode::editor::cn::util::TreeNode;

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

impl Solution {
    pub fn min_depth(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        return match root {
            None => 0,
            Some(node) => {
                let mut q = std::collections::VecDeque::new();
                q.reserve(10);
                q.push_back(node);
                let mut depth = 1;
                while !q.is_empty() {
                    let sz = q.len();
                    for _ in 0..sz {
                        let node = q.pop_front().unwrap();
                        let node = node.borrow();
                        if node.left.is_none() && node.right.is_none() {
                            return depth;
                        }
                        if node.left.is_some() {
                            q.push_back(node.left.as_ref().unwrap().clone());
                        }
                        if node.right.is_some() {
                            q.push_back(node.right.as_ref().unwrap().clone());
                        }
                    }

                    depth = depth + 1;
                }
                depth
            }
        };
    }
}
//leetcode submit region end(Prohibit modification and deletion)

