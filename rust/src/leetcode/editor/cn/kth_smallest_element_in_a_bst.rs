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
    pub fn kth_smallest(root: Option<Rc<RefCell<TreeNode>>>, k: i32) -> i32 {
        fn traverse(node: Option<Rc<RefCell<TreeNode>>>, k: &mut i32) -> Option<i32> {
            match node {
                None => None,
                Some(node) => {
                    let left = traverse(node.borrow().left.clone(), k);
                    if let Some(val) = left {
                        return Some(val);
                    }
                    *k -= 1;
                    if *k == 0 {
                        Some(node.borrow().val)
                    } else {
                        traverse(node.borrow().right.clone(), k)
                    }
                }
            }
        }

        let mut k = k;
        traverse(root, &mut k)
            .unwrap()
    }
}
//leetcode submit region end(Prohibit modification and deletion)
