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

struct MySerializer {
    builder: Vec<String>,
    root: Option<Rc<RefCell<TreeNode>>>,
}

impl MySerializer {
    fn new(root: Option<Rc<RefCell<TreeNode>>>) -> Self {
        MySerializer {
            builder: vec![],
            root,
        }
    }

    fn serialize_preorder(&mut self) -> String {
        self.preorder(self.root.clone());
        self.builder.join(",")
    }

    fn preorder(&mut self, node: Option<Rc<RefCell<TreeNode>>>) {
        match node {
            None => self.builder.push("#".to_string()),
            Some(node) => {
                self.builder.push(node.borrow().val.to_string());
                self.preorder(node.borrow().left.clone());
                self.preorder(node.borrow().right.clone());
            }
        }
    }
}

struct MyDeserializer {
    tokens: Vec<String>,
}

impl MyDeserializer {
    fn new(encoded: String) -> MyDeserializer {
        MyDeserializer {
            tokens: encoded.split(",").map(|s| s.to_string()).collect(),
        }
    }

    fn deserialize_preorder(&mut self) -> Option<Rc<RefCell<TreeNode>>> {
        self.preorder()
    }

    fn preorder(&mut self) -> Option<Rc<RefCell<TreeNode>>> {
        if self.tokens.is_empty() {
            None
        } else {
            let val = self.tokens.remove(0);
            if val == "#" {
                None
            } else {
                let node = Rc::new(RefCell::new(TreeNode::new(val.parse().unwrap())));
                node.borrow_mut().left = self.preorder();
                node.borrow_mut().right = self.preorder();
                Some(node)
            }
        }
    }
}
struct Codec {}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl Codec {
    fn new() -> Self {
        Codec {}
    }

    fn serialize(&self, root: Option<Rc<RefCell<TreeNode>>>) -> String {
        let mut serializer = MySerializer::new(root);
        serializer.serialize_preorder()
    }

    fn deserialize(&self, data: String) -> Option<Rc<RefCell<TreeNode>>> {
        let mut deserializer = MyDeserializer::new(data);
        deserializer.deserialize_preorder()
    }

}

//leetcode submit region end(Prohibit modification and deletion)
