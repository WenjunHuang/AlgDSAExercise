<p>给出二叉<strong> 搜索 </strong>树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 <code>node</code>&nbsp;的新值等于原树中大于或等于&nbsp;<code>node.val</code>&nbsp;的值之和。</p>

<p>提醒一下，二叉搜索树满足下列约束条件：</p>

<ul> 
 <li>节点的左子树仅包含键<strong> 小于 </strong>节点键的节点。</li> 
 <li>节点的右子树仅包含键<strong> 大于</strong> 节点键的节点。</li> 
 <li>左右子树也必须是二叉搜索树。</li> 
</ul>

<p><strong>注意：</strong>本题和 1038:&nbsp;<a href="https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/">https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/</a> 相同</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/05/03/tree.png" style="height: 364px; width: 534px;" /></strong></p>

<pre><strong>输入：</strong>[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
<strong>输出：</strong>[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>root = [0,null,1]
<strong>输出：</strong>[1,null,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>root = [1,0,2]
<strong>输出：</strong>[3,3,2]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>root = [3,2,4,1]
<strong>输出：</strong>[7,9,4,10]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中的节点数介于 <code>0</code>&nbsp;和 <code>10<sup>4</sup></code><sup>&nbsp;</sup>之间。</li> 
 <li>每个节点的值介于 <code>-10<sup>4</sup></code>&nbsp;和&nbsp;<code>10<sup>4</sup></code>&nbsp;之间。</li> 
 <li>树中的所有值 <strong>互不相同</strong> 。</li> 
 <li>给定的树为二叉搜索树。</li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>树 | 深度优先搜索 | 二叉搜索树 | 二叉树</details><br>

<div>👍 1016, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：已完成网站教程、网站习题、配套插件中所有多语言代码的校准，解决了之前 chatGPT 翻译可能出错的问题~**



<p><strong><a href="https://labuladong.online/algo/data-structure/bst-part1/" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

<div id="labuladong_solution_zh">

## 基本思路

前文 [手把手刷二叉树总结篇](https://labuladong.online/algo/essential-technique/binary-tree-summary/) 说过二叉树的递归分为「遍历」和「分解问题」两种思维模式，这道题需要用到「遍历」的思维。

维护一个外部累加变量 `sum`，在遍历 BST 的过程中增加 `sum`，同时把 `sum` 赋值给 BST 中的每一个节点，就将 BST 转化成累加树了。

但是注意顺序，正常的中序遍历顺序是先左子树后右子树，这里需要反过来，先右子树后左子树。

**详细题解：[东哥带你刷二叉搜索树（特性篇）](https://labuladong.online/algo/data-structure/bst-part1/)**

</div>

**标签：[二叉搜索树](https://labuladong.online/algo/)，[数据结构](https://labuladong.online/algo/)**

<div id="solution">

## 解法代码



<div class="tab-panel"><div class="tab-nav">
<button data-tab-item="cpp" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">cpp🤖</button>

<button data-tab-item="python" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">python🤖</button>

<button data-tab-item="java" class="tab-nav-button btn active" data-tab-group="default" onclick="switchTab(this)">java🟢</button>

<button data-tab-item="go" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">go🤖</button>

<button data-tab-item="javascript" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">javascript🤖</button>
</div><div class="tab-content">
<div data-tab-item="cpp" class="tab-item " data-tab-group="default"><div class="highlight">

```cpp
// 注意：cpp 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

class Solution {
public:
    TreeNode* convertBST(TreeNode* root) {
        traverse(root);
        return root;
    }

    // 记录累加和
    int sum = 0;
    void traverse(TreeNode* root) {
        if (root == nullptr) {
            return;
        }
        traverse(root->right);
        // 维护累加和
        sum += root->val;
        // 将 BST 转化成累加树
        root->val = sum;
        traverse(root->left);
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def __init__(self):
        # 记录累加和
        self.sum = 0

    def convertBST(self, root: TreeNode) -> TreeNode:
        self.traverse(root)
        return root

    def traverse(self, root: TreeNode):
        if root is None:
            return
        self.traverse(root.right)
        # 维护累加和
        self.sum += root.val
        # 将 BST 转化成累加树
        root.val = self.sum
        self.traverse(root.left)
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    // 记录累加和
    int sum = 0;
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.right);
        // 维护累加和
        sum += root.val;
        // 将 BST 转化成累加树
        root.val = sum;
        traverse(root.left);
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func convertBST(root *TreeNode) *TreeNode {
    sum := 0
    traverse(root, &sum)
    return root
}

// 记录累加和
func traverse(root *TreeNode, sum *int) {
    if root == nil {
        return
    }
    traverse(root.Right, sum)
    // 维护累加和
    *sum += root.Val
    // 将 BST 转化成累加树
    root.Val = *sum
    traverse(root.Left, sum)
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var convertBST = function(root) {
    // 记录累加和
    let sum = 0;
    // 中序遍历节点
    const traverse = function(node) {
        if (!node) {
            return;
        }
        traverse(node.right);  // 先遍历右子树
        sum += node.val;  // 维护累加和
        node.val = sum;  // 将 BST 节点的值更新为累加和
        traverse(node.left);  // 遍历左子树
    }
    traverse(root);
    return root;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🌟🌟 算法可视化 🌟🌟</strong></summary><div id="data_convert-bst-to-greater-tree" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_convert-bst-to-greater-tree"></div></div>
</details><hr /><br />

**类似题目**：
  - [1038. 从二叉搜索树到更大和树 🟠](/problems/binary-search-tree-to-greater-sum-tree)
  - [230. 二叉搜索树中第K小的元素 🟠](/problems/kth-smallest-element-in-a-bst)
  - [剑指 Offer II 054. 所有大于等于节点的值之和 🟠](/problems/w6cpku)

</div>

</details>
</div>

