from typing import *
from util import *


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.valToIdx = {}

    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        for i, v in enumerate(inorder):
            self.valToIdx[v] = i
        return self.build(inorder, 0, len(inorder) - 1, postorder, 0, len(postorder) - 1)

    def build(self, inorder, inStart, inEnd, postOrder, postStart, postEnd):
        if inStart > inEnd:
            return None

        rootVal = postOrder[postEnd]
        index = self.valToIdx.get(rootVal)
        leftSize = index - inStart

        node = TreeNode(rootVal)
        node.left = self.build(inorder, inStart, index - 1, postOrder, postStart, postStart + leftSize - 1)
        node.right = self.build(inorder, index + 1, inEnd, postOrder, postStart + leftSize, postEnd - 1)
        return node

# leetcode submit region end(Prohibit modification and deletion)
