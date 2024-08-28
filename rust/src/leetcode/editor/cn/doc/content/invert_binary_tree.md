<p>给你一棵二叉树的根节点 <code>root</code> ，翻转这棵二叉树，并返回其根节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg" style="height: 165px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>root = [4,2,7,1,3,6,9]
<strong>输出：</strong>[4,7,2,9,6,3,1]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg" style="width: 500px; height: 120px;" /></p>

<pre>
<strong>输入：</strong>root = [2,1,3]
<strong>输出：</strong>[2,3,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点数目范围在 <code>[0, 100]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>树 | 深度优先搜索 | 广度优先搜索 | 二叉树</details><br>

<div>👍 1852, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：已完成网站教程、网站习题、配套插件中所有多语言代码的校准，解决了之前 chatGPT 翻译可能出错的问题~**



<p><strong><a href="https://labuladong.online/algo/slug.html?slug=invert-binary-tree" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

<div id="labuladong_solution_zh">

## 基本思路

> 本文有视频版：[二叉树/递归的框架思维（纲领篇）](https://www.bilibili.com/video/BV1nG411x77H)

前文 [手把手刷二叉树总结篇](https://labuladong.online/algo/essential-technique/binary-tree-summary/) 说过二叉树的递归分为「遍历」和「分解问题」两种思维模式，这道题可以同时使用两种思维模式。

如何翻转二叉树？其实就是把二叉树上的每个节点的左右子节点都交换一下，我同时给出两种思维模式下的解法供你对比。

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

// 「遍历」的思路
class Solution {
public:
    // 主函数
    TreeNode* invertTree(TreeNode* root) {
        // 遍历二叉树，交换每个节点的子节点
        traverse(root);
        return root;
    }

    // 二叉树遍历函数
    void traverse(TreeNode* root) {
        if (root == nullptr) {
            return;
        }

        // *** 前序位置 ***
        // 每一个节点需要做的事就是交换它的左右子节点
        TreeNode* tmp = root->left;
        root->left = root->right;
        root->right = tmp;

        // 遍历框架，去遍历左右子树的节点
        traverse(root->left);
        traverse(root->right);
    }
};

// 「分解问题」的思路
class Solution2 {
public:
    // 定义：将以 root 为根的这棵二叉树翻转，返回翻转后的二叉树的根节点
    TreeNode* invertTree(TreeNode* root) {
        if (root == nullptr) {
            return nullptr;
        }
        // 利用函数定义，先翻转左右子树
        TreeNode* left = invertTree(root->left);
        TreeNode* right = invertTree(root->right);

        // 然后交换左右子节点
        root->left = right;
        root->right = left;

        // 和定义逻辑自恰：以 root 为根的这棵二叉树已经被翻转，返回 root
        return root;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

# 「遍历」的思路
class Solution:
    # 主函数
    def invertTree(self, root):
        # 遍历二叉树，交换每个节点的子节点
        self.traverse(root)
        return root

    # 二叉树遍历函数
    def traverse(self, root):
        if root is None:
            return

        # *** 前序位置 ***
        # 每一个节点需要做的事就是交换它的左右子节点
        tmp = root.left
        root.left = root.right
        root.right = tmp

        # 遍历框架，去遍历左右子树的节点
        self.traverse(root.left)
        self.traverse(root.right)

# 「分解问题」的思路
class Solution2:
    # 定义：将以 root 为根的这棵二叉树翻转，返回翻转后的二叉树的根节点
    def invertTree(self, root):
        if root is None:
            return None
        # 利用函数定义，先翻转左右子树
        left = self.invertTree(root.left)
        right = self.invertTree(root.right)

        # 然后交换左右子节点
        root.left = right
        root.right = left

        # 和定义逻辑自恰：以 root 为根的这棵二叉树已经被翻转，返回 root
        return root
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
// 「遍历」的思路
class Solution {
    // 主函数
    public TreeNode invertTree(TreeNode root) {
        // 遍历二叉树，交换每个节点的子节点
        traverse(root);
        return root;
    }

    // 二叉树遍历函数
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // *** 前序位置 ***
        // 每一个节点需要做的事就是交换它的左右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 遍历框架，去遍历左右子树的节点
        traverse(root.left);
        traverse(root.right);
    }
}

// 「分解问题」的思路
class Solution2 {
    // 定义：将以 root 为根的这棵二叉树翻转，返回翻转后的二叉树的根节点
    TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 利用函数定义，先翻转左右子树
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // 然后交换左右子节点
        root.left = right;
        root.right = left;

        // 和定义逻辑自恰：以 root 为根的这棵二叉树已经被翻转，返回 root
        return root;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

// 「遍历」的思路
func invertTree(root *TreeNode) *TreeNode {
    // 主函数
    // 遍历二叉树，交换每个节点的子节点
    traverse(root)
    return root
}

// 二叉树遍历函数
func traverse(root *TreeNode) {
    if root == nil {
        return
    }

    // *** 前序位置 ***
    // 每一个节点需要做的事就是交换它的左右子节点
    tmp := root.Left
    root.Left = root.Right
    root.Right = tmp

    // 遍历框架，去遍历左右子树的节点
    traverse(root.Left)
    traverse(root.Right)
}

// 「分解问题」的思路
func invertTree2(root *TreeNode) *TreeNode {
    // 定义：将以 root 为根的这棵二叉树翻转，返回翻转后的二叉树的根节点
    if root == nil {
        return nil
    }
    // 利用函数定义，先翻转左右子树
    left := invertTree2(root.Left)
    right := invertTree2(root.Right)

    // 然后交换左右子节点
    root.Left = right
    root.Right = left

    // 和定义逻辑自恰：以 root 为根的这棵二叉树已经被翻转，返回 root
    return root
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

// 「遍历」的思路
var invertTree = function(root) {
    // 主函数
    // 遍历二叉树，交换每个节点的子节点
    traverse(root);
    return root;
};

// 二叉树遍历函数
function traverse(root) {
    if (root === null) {
        return;
    }

    // *** 前序位置 ***
    // 每一个节点需要做的事就是交换它的左右子节点
    let tmp = root.left;
    root.left = root.right;
    root.right = tmp;

    // 遍历框架，去遍历左右子树的节点
    traverse(root.left);
    traverse(root.right);
}

// 「分解问题」的思路
var invertTree2 = function(root) {
    // 定义：将以 root 为根的这棵二叉树翻转，返回翻转后的二叉树的根节点
    if (root === null) {
        return null;
    }
    // 利用函数定义，先翻转左右子树
    let left = invertTree2(root.left);
    let right = invertTree2(root.right);

    // 然后交换左右子节点
    root.left = right;
    root.right = left;

    // 和定义逻辑自恰：以 root 为根的这棵二叉树已经被翻转，返回 root
    return root;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🍭🍭 算法可视化 🍭🍭</strong></summary><div id="data_invert-binary-tree" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_invert-binary-tree"></div></div>
</details><hr /><br />

**类似题目**：
  - [114. 二叉树展开为链表 🟠](/problems/flatten-binary-tree-to-linked-list)
  - [116. 填充每个节点的下一个右侧节点指针 🟠](/problems/populating-next-right-pointers-in-each-node)
  - [剑指 Offer 27. 二叉树的镜像 🟢](/problems/er-cha-shu-de-jing-xiang-lcof)

</div>

</details>
</div>

