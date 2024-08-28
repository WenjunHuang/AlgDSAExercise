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


impl Solution {
    pub fn max_depth(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        fn traverse(node: Option<&Rc<RefCell<TreeNode>>>, depth: i32) -> i32 {
            match node {
                None => depth,
                Some(node) => {
                    let node = node.borrow();
                    let left_depth = traverse(node.left.as_ref(), depth + 1);
                    let right_depth = traverse(node.right.as_ref(), depth + 1);
                    left_depth.max(right_depth)
                }
            }
        }
        traverse(root.as_ref(), 0)
    }
}
//leetcode submit region end(Prohibit modification and deletion)
