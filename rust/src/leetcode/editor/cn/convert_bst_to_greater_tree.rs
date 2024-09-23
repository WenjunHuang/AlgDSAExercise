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
    pub fn convert_bst(root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        fn traverse(node: Option<Rc<RefCell<TreeNode>>>,sum:&mut i32) {
            match node {
                None => {}
                Some(node) => {
                    traverse(node.borrow().right.clone(),sum);
                    *sum += node.borrow().val;
                    node.borrow_mut().val = *sum;
                    traverse(node.borrow().left.clone(),sum);
                }
            }
        }

        let mut sum = 0;
        traverse(root.clone(),&mut sum);
        root
    }
}
//leetcode submit region end(Prohibit modification and deletion)
