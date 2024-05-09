package leetcode.editor.cn

object SerializeAndDeserializeBinaryTree {
//leetcode submit region begin(Prohibit modification and deletion)
  /** Definition for a binary tree node. class TreeNode(var _value: Int) { var value: Int = _value var left: TreeNode = null var right: TreeNode = null }
    */
  import scala.collection.mutable.ListBuffer

  private class Serializer(val root: TreeNode) {
    private val builder = ListBuffer[String]()

    def serialize(): String =
      def impl(node: TreeNode): Unit =
        if node == null then builder.append("#")
        else
          builder.append(node.value.toString)
          impl(node.left)
          impl(node.right)

      impl(root)
      builder.mkString(",")
  }

  private class Deserializer(val encoded: String) {
    private val nodes = ListBuffer[String]()
    nodes.addAll(encoded.split(","))

    def deserialize(): TreeNode =
      if nodes.isEmpty then null
      else
        val nodeVal = nodes.head
        nodes.remove(0)
        if nodeVal == "#" then null
        else
          val node = TreeNode(nodeVal.toInt)
          node.left = deserialize()
          node.right = deserialize()
          node

  }

  class Codec {
    // Encodes a list of strings to a single string.
    def serialize(root: TreeNode): String =
      if root == null then "#"
      else
        Serializer(root).serialize()


    // Decodes a single string to a list of strings.
    def deserialize(data: String): TreeNode = Deserializer(data).deserialize()
  }

  /** Your Codec object will be instantiated and called as such: var ser = new Codec() var deser = new Codec() val s = ser.serialize(root) val ans = deser.deserialize(s)
    */
//leetcode submit region end(Prohibit modification and deletion)

}
