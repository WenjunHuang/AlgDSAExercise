use crate::leetcode::editor::cn::util::{ListNode, TreeNode};
use std::rc::Rc;
use std::cell::RefCell;

struct Solution;

//leetcode submit region begin(Prohibit modification and deletion)
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
impl Solution {
    pub fn delete_duplicates(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut phead = head;
        let mut dummy = Box::new(ListNode { val: 0, next: None });
        let mut p = &mut dummy;
        let mut q = &mut phead;

        while q.is_some() {
            if q.as_ref()?.next.is_some() && q.as_ref()?.val == q.as_ref()?.next.as_ref()?.val {
                while q.as_ref()?.next.is_some() && q.as_ref()?.val == q.as_ref()?.next.as_ref()?.val {
                    q = &mut q.as_mut().unwrap().next;
                }

                q = &mut q.as_mut().unwrap().next;

                if q.is_none() {
                    p.next = None;
                }
            } else {
                p.next = q.take();
                p = p.next.as_mut().unwrap();
                q = &mut p.next;
            }
        }

        return dummy.next;
    }

    pub fn delete_duplicates_clone(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Box::new(ListNode { val: 0, next: None });
        let mut p = &mut dummy;
        let mut q = &head;

        while q.is_some() {
            let mut count = 0;
            let mut r = q;
            while r.as_ref().is_some_and(|e| e.val == q.as_ref().unwrap().val) {
                count += 1;
                r = &r.as_ref().unwrap().next;
                if r.is_none() {
                    p.next = None;
                }
            }
            if count == 1 {
                p.next = q.clone();
                p = p.next.as_mut().unwrap();
            }
            q = r;
        }


        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

