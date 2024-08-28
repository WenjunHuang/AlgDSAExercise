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

struct Answer{
    max_diameter:i32,
}

impl Answer {
    fn max_depth(&mut self,node: Option<&Rc<RefCell<TreeNode>>>) -> i32 {
        match node {
            None => 0,
            Some(node) => {
                let node = node.borrow();
                let left_depth = self.max_depth(node.left.as_ref());
                let right_depth = self.max_depth(node.right.as_ref());
                let diameter = left_depth+right_depth;
                self.max_diameter = self.max_diameter.max(diameter);
                left_depth.max(right_depth)+1
            }
        }
    }

    fn new() ->Answer{
        Answer{
            max_diameter:0,
        }
    }
}
impl Solution {
    pub fn diameter_of_binary_tree(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut answer = Answer::new();
        answer.max_depth(root.as_ref());
        answer.max_diameter
    }
}
//leetcode submit region end(Prohibit modification and deletion)
