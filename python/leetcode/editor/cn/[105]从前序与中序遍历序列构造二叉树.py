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

    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        for i, val in enumerate(inorder):
            self.valToIdx[val] = i

        return self.build(preorder, 0, len(preorder) - 1, inorder, 0, len(inorder) - 1)

    def build(self, preorder: List[int], preStart: int, preEnd: int, inorder: List[int], inStart: int, inEnd: int):
        if preStart > preEnd:
            return None

        rootVal = preorder[preStart]
        index = self.valToIdx.get(rootVal)
        leftSize = index - inStart

        root = TreeNode(rootVal)
        root.left = self.build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1)
        root.right = self.build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd)
        return root
# leetcode submit region end(Prohibit modification and deletion)
