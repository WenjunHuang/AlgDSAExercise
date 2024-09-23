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
use std::collections::HashMap;
impl Solution {
    pub fn build_tree(preorder: Vec<i32>, inorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut val_index: HashMap<i32, usize> = HashMap::new();
        for v in inorder.iter().enumerate() {
            val_index.insert(v.1.clone(), v.0);
        }

        Self::build(&preorder, &inorder, &val_index, 0, preorder.len() - 1, 0, inorder.len() - 1)
    }

    fn build(preorder: &[i32], inorder: &[i32], val_index: &HashMap<i32, usize>, pre_start: usize, pre_end: usize, in_start: usize, in_end: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if pre_start > pre_end {
            return None;
        }
        let root_val = preorder[pre_start];
        let mut root = TreeNode::new(root_val);
        let root_index = val_index.get(&root_val).unwrap();
        let left_size = root_index - in_start;
        root.left = Self::build(preorder, inorder, val_index, pre_start + 1, pre_start + left_size, in_start, root_index - 1);
        root.right = Self::build(preorder, inorder, val_index, pre_start + left_size + 1, pre_end, root_index + 1, in_end);
        Some(Rc::new(RefCell::new(root)))
    }
}
//leetcode submit region end(Prohibit modification and deletion)
