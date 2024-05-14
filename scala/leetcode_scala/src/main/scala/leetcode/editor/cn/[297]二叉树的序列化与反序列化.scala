package leetcode.editor.cn

object SerializeAndDeserializeBinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(var _value: Int) { var value: Int = _value var left: TreeNode = null var right: TreeNode = null }
    */

  import scala.collection.mutable.{ ArrayDeque, ListBuffer, Queue }

  private class Serializer(val root: TreeNode) {
    private val builder = ListBuffer[String]()

    def serializeWithPreOrder(): String =
      def impl(node: TreeNode): Unit =
        if node == null then builder.append("#")
        else
          impl(node.left)
          impl(node.right)
          builder.append(node.value.toString)

      impl(root)
      builder.mkString(",")

    def serializeWithPostOrder(): String =
      def impl(node: TreeNode): Unit =
        if node == null then builder.append("#")
        else
          builder.append(node.value.toString)
          impl(node.left)
          impl(node.right)

      impl(root)
      builder.mkString(",")

    def serializeWithLevelOrder(): String =
      if root == null then "#"
      else
        val q = Queue[TreeNode]()
        q.enqueue(root)

        while q.nonEmpty do
          val cur = q.dequeue()
          if cur == null then builder.append("#")
          else
            builder.append(cur.value.toString)
            q.enqueue(cur.left)
            q.enqueue(cur.right)

        builder.mkString(",")

  }

  private class Deserializer(val encoded: String) {
    private val nodes = ArrayDeque[String](encoded.split(",")*)

    def deserializeWithPreOrder(): TreeNode =
      if nodes.isEmpty then null
      else
        val nodeVal = nodes.head
        nodes.removeHead()
        if nodeVal == "#" then null
        else
          val node = TreeNode(nodeVal.toInt)
          node.left = deserializeWithPreOrder()
          node.right = deserializeWithPreOrder()
          node

    def deserializeWithPostOrder(): TreeNode =
      if nodes.isEmpty then null
      else
        val s = nodes.last
        nodes.removeLast()
        if s == "#" then null
        else
          val v = s.toInt
          val root = TreeNode(v)
          root.right = deserializeWithPostOrder()
          root.left = deserializeWithPostOrder()
          root

    def deserializeWithLevelOrder(): TreeNode =
      if nodes.isEmpty then null
      else
        val first = nodes.removeHead()
        if first == "#" then null
        else
          val root = TreeNode(first.toInt)
          val q = Queue[TreeNode]()
          q.enqueue(root)
          while q.nonEmpty do
            val parent = q.dequeue()
            if parent != null then
              val left = nodes.removeHead()
              if left != "#" then
                val leftNode = TreeNode(left.toInt)
                parent.left = leftNode
                q.enqueue(leftNode)

              val right = nodes.removeHead()
              if right != "#" then
                val rightNode = TreeNode(right.toInt)
                parent.right = rightNode
                q.enqueue(rightNode)

          root
  }

  class Codec {
    // Encodes a list of strings to a single string.
    def serialize(root: TreeNode): String =
      if root == null then "#"
      else Serializer(root).serializeWithLevelOrder()

    // Decodes a single string to a list of strings.
    def deserialize(data: String): TreeNode = Deserializer(data).deserializeWithLevelOrder()
  }

  /** Your Codec object will be instantiated and called as such: var ser = new Codec() var deser = new Codec() val s = ser.serialize(root) val ans = deser.deserialize(s)
    */
//leetcode submit region end(Prohibit modification and deletion)

  @main
  def runSerialize(): Unit = {
    val s = Serializer(TreeNode(1, TreeNode(2), TreeNode(3))).serializeWithPostOrder()
    println(Deserializer(s).deserializeWithPostOrder())
  }
}
