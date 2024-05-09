from typing import *
from util import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Serializer:
    def __init__(self, root: TreeNode):
        self.root = root
        self.builder = []

    def serialize(self) -> str:
        self.impl(self.root)
        return ','.join(self.builder)

    def impl(self, node: TreeNode | None):
        if node is None:
            self.builder.append("#")
        else:
            self.builder.append(str(node.val))
            self.impl(node.left)
            self.impl(node.right)


class Deserializer:
    def __init__(self, encoded: str):
        self.encoded = encoded
        self.nodes = encoded.split(",")

    def deserialize(self) -> TreeNode | None:
        if len(self.nodes) == 0:
            return None
        else:
            first = self.nodes[0]
            self.nodes = self.nodes[1:]
            if first == "#":
                return None
            else:
                val = int(first)
                node = TreeNode(val)
                node.left = self.deserialize()
                node.right = self.deserialize()
                return node


class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """
        return Serializer(root).serialize()

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        return Deserializer(data).deserialize()

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))
# leetcode submit region end(Prohibit modification and deletion)
