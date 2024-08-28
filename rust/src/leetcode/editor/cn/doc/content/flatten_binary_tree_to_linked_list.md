<p>给你二叉树的根结点 <code>root</code> ，请你将它展开为一个单链表：</p>

<ul> 
 <li>展开后的单链表应该同样使用 <code>TreeNode</code> ，其中 <code>right</code> 子指针指向链表中下一个结点，而左子指针始终为 <code>null</code> 。</li> 
 <li>展开后的单链表应该与二叉树 <a href="https://baike.baidu.com/item/%E5%85%88%E5%BA%8F%E9%81%8D%E5%8E%86/6442839?fr=aladdin" target="_blank"><strong>先序遍历</strong></a> 顺序相同。</li> 
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/14/flaten.jpg" style="width: 500px; height: 226px;" /> 
<pre>
<strong>输入：</strong>root = [1,2,5,3,4,null,6]
<strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [0]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中结点数在范围 <code>[0, 2000]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以使用原地算法（<code>O(1)</code> 额外空间）展开这棵树吗？</p>

<details><summary><strong>Related Topics</strong></summary>栈 | 树 | 深度优先搜索 | 链表 | 二叉树</details><br>

<div>👍 1719, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：已完成网站教程、网站习题、配套插件中所有多语言代码的校准，解决了之前 chatGPT 翻译可能出错的问题~**



<p><strong><a href="https://labuladong.online/algo/slug.html?slug=flatten-binary-tree-to-linked-list" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

<div id="labuladong_solution_zh">

## 基本思路

> 本文有视频版：[二叉树/递归的框架思维（纲领篇）](https://www.bilibili.com/video/BV1nG411x77H)

前文 [手把手刷二叉树总结篇](https://labuladong.online/algo/essential-technique/binary-tree-summary/) 说过二叉树的递归分为「遍历」和「分解问题」两种思维模式，这道题需要用到「分解问题」的思维。

前者较简单，只要运用二叉树的递归遍历框架即可；后者的关键在于明确递归函数的定义，然后利用这个定义，这题就属于后者，`flatten` 函数的定义如下：

**给 `flatten` 函数输入一个节点 `root`，那么以 `root` 为根的二叉树就会被拉平为一条链表**。

如何利用这个定义来完成算法？你想想怎么把以 `root` 为根的二叉树拉平为一条链表？

很简单，以下流程：

1、将 `root` 的左子树和右子树拉平。

2、将 `root` 的右子树接到左子树下方，然后将整个左子树作为右子树。

![](https://labuladong.online/algo/images/二叉树系列/2.jpeg)

至于如何把 `root` 的左右子树拉平，不用你操心，`flatten` 函数的定义就是这样，交给他做就行了。

把上面的逻辑翻译成代码，即可解决本题。

**详细题解：[东哥带你刷二叉树（思路篇）](https://labuladong.online/algo/data-structure/binary-tree-part1/)**

</div>

**标签：[二叉树](https://labuladong.online/algo/)，[数据结构](https://labuladong.online/algo/)**

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

class Solution {
public:
    // 定义：将以 root 为根的树拉平为链表
    void flatten(TreeNode* root) {
        // base case
        if (root == nullptr) return;
        // 先递归拉平左右子树
        flatten(root->left);
        flatten(root->right);

        // ***后序遍历位置***
        // 1、左右子树已经被拉平成一条链表
        TreeNode* left = root->left;
        TreeNode* right = root->right;

        // 2、将左子树作为右子树
        root->left = nullptr;
        root->right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode* p = root;
        while (p->right != nullptr) {
            p = p->right;
        }
        p->right = right;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    # 定义：将以 root 为根的树拉平为链表
    def flatten(self, root):
        # base case
        if root is None:
            return
        
        # 先递归拉平左右子树
        self.flatten(root.left)
        self.flatten(root.right)

        # ***后序遍历位置***
        # 1、左右子树已经被拉平成一条链表
        left = root.left
        right = root.right

        # 2、将左子树作为右子树
        root.left = None
        root.right = left

        # 3、将原先的右子树接到当前右子树的末端
        p = root
        while p.right is not None:
            p = p.right
        p.right = right
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    // 定义：将以 root 为根的树拉平为链表
    public void flatten(TreeNode root) {
        // base case
        if (root == null) return;
        // 先递归拉平左右子树
        flatten(root.left);
        flatten(root.right);

        // ***后序遍历位置***
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;/**<extend up -50>![](https://labuladong.online/algo/images/二叉树系列/2.jpeg) */
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

// 定义：将以 root 为根的树拉平为链表
func flatten(root *TreeNode) {
    // base case
    if root == nil {
        return
    }
    // 先递归拉平左右子树
    flatten(root.Left)
    flatten(root.Right)

    // ***后序遍历位置***
    // 1、左右子树已经被拉平成一条链表
    left := root.Left
    right := root.Right

    // 2、将左子树作为右子树
    root.Left = nil
    root.Right = left

    // 3、将原先的右子树接到当前右子树的末端
    p := root
    for p.Right != nil {
        p = p.Right
    }
    p.Right = right
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

var flatten = function(root) {
    // 定义：将以 root 为根的树拉平为链表
    var flattenTree = function(root) {
        // base case
        if (root == null) return;
        // 先递归拉平左右子树
        flattenTree(root.left);
        flattenTree(root.right);

        // ***后序遍历位置***
        // 1、左右子树已经被拉平成一条链表
        let left = root.left;
        let right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        let p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    };

    flattenTree(root);
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🥳🥳 算法可视化 🥳🥳</strong></summary><div id="data_flatten-binary-tree-to-linked-list" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_flatten-binary-tree-to-linked-list"></div></div>
</details><hr /><br />

**类似题目**：
  - [116. 填充每个节点的下一个右侧节点指针 🟠](/problems/populating-next-right-pointers-in-each-node)
  - [226. 翻转二叉树 🟢](/problems/invert-binary-tree)
  - [897. 递增顺序搜索树 🟢](/problems/increasing-order-search-tree)
  - [剑指 Offer 27. 二叉树的镜像 🟢](/problems/er-cha-shu-de-jing-xiang-lcof)
  - [剑指 Offer II 052. 展平二叉搜索树 🟢](/problems/NYBBNL)

</div>

</details>
</div>

