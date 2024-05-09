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
        self.subTrees = {}
        self.res = []

    def findDuplicateSubtrees(self, root: Optional[TreeNode]) -> List[Optional[TreeNode]]:
        self.serialize(root)
        return self.res

    def serialize(self, root: Optional[TreeNode]):
        if root is None:
            return "#"

        # 先计算左右子树的序列化结果
        left = self.serialize(root.left)
        right = self.serialize(root.right)

        mySelf = f"{left},{right},{root.val}"
        freq = self.subTrees.setdefault(mySelf, 0)
        # 多次重复也只会被加入结果一次
        if freq == 1:
            self.res.append(root)

        self.subTrees[mySelf] = freq + 1
        return mySelf

# leetcode submit region end(Prohibit modification and deletion)
