object 分隔链表 {
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     */
    object Solution {
        def partition(head: ListNode, x: Int): ListNode = {
            val dump1 = ListNode(-1)
            val dump2 = ListNode(-1)
            var p1 = dump1
            var p2 = dump2
            var p = head

            while (p != null) {
                if (p.x < x) {
                    p1.next = p
                    p1 = p1.next
                } else {
                    p2.next = p
                    p2 = p2.next
                }

                val temp = p.next
                p.next = null
                p = temp
            }

            p1.next = dump2.next
            dump1.next
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
