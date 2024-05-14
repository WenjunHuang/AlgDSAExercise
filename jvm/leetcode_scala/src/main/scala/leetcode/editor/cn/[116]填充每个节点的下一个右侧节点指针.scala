package leetcode.editor.cn

object PopulatingNextRightPointersInEachNode {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a Node. class Node(var _value: Int) { var value: Int = _value var left: Node = null var right: Node = null var next: Node = null }
    */

  object Solution {
    def connect(root: Node): Node =
      if root != null then traverse(root.left, root.right)

      root

    def traverse(node1: Node, node2: Node): Unit =
      if node1 != null && node2 != null then
        node1.next = node2
        traverse(node1.left, node1.right)
        traverse(node2.left, node2.right)
        traverse(node1.right, node2.left)
  }
//leetcode submit region end(Prohibit modification and deletion)

}
