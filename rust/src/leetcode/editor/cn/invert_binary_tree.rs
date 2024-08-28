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
    pub fn invert_tree(mut root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        match root {
            None => None,
            Some(node) =>{
                {
                    let mut node_mut = node.borrow_mut();
                    let left = Self::invert_tree(node_mut.left.take());
                    let right = Self::invert_tree(node_mut.right.take());

                    node_mut.left = right;
                    node_mut.right = left;
                }
                Some(node)
            }
        }
    }

    fn traverse(node: &mut Option<Rc<RefCell<TreeNode>>>) {
        match node {
            None => {}
            Some(node) => {
                let mut node = node.borrow_mut();
                Self::traverse(&mut node.left);
                Self::traverse(&mut node.right);

                let temp = node.left.take();
                node.left = node.right.take();
                node.right = temp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
