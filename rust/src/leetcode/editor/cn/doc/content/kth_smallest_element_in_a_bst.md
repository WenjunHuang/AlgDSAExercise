<p>给定一个二叉搜索树的根节点 <code>root</code> ，和一个整数 <code>k</code> ，请你设计一个算法查找其中第&nbsp;<code>k</code><strong>&nbsp;</strong>小的元素（从 1 开始计数）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/28/kthtree1.jpg" style="width: 212px; height: 301px;" /> 
<pre>
<strong>输入：</strong>root = [3,1,4,null,2], k = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/28/kthtree2.jpg" style="width: 382px; height: 302px;" /> 
<pre>
<strong>输入：</strong>root = [5,3,6,2,4,null,null,1], k = 3
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中的节点数为 <code>n</code> 。</li> 
 <li><code>1 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li> 
 <li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 <code>k</code> 小的值，你将如何优化算法？</p>

<details><summary><strong>Related Topics</strong></summary>树 | 深度优先搜索 | 二叉搜索树 | 二叉树</details><br>

<div>👍 910, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：已完成网站教程、网站习题、配套插件中所有多语言代码的校准，解决了之前 chatGPT 翻译可能出错的问题~**



<p><strong><a href="https://labuladong.online/algo/data-structure/bst-part1/" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

<div id="labuladong_solution_zh">

## 基本思路

BST 的中序遍历结果是有序的（升序），所以用一个外部变量记录中序遍历结果第 `k` 个元素即是第 `k` 小的元素。

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

class Solution {
public:
    int kthSmallest(TreeNode* root, int k) {
        // 利用 BST 的中序遍历特性
        traverse(root, k);
        return res;
    }

private:
    // 记录结果
    int res = 0;
    // 记录当前元素的排名
    int rank = 0;
    void traverse(TreeNode* root, int k) {
        if (root == nullptr) {
            return;
        }
        traverse(root->left, k);
        // 中序代码位置
        rank++;
        if (k == rank) {
            // 找到第 k 小的元素
            res = root->val;
            return;
        }

        traverse(root->right, k);
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
    def kthSmallest(self, root: TreeNode, k: int) -> int:
        # 利用 BST 的中序遍历特性
        self.traverse(root, k)
        return self.res

    # 记录结果
    res = 0
    # 记录当前元素的排名
    rank = 0

    def traverse(self, root: TreeNode, k: int):
        if root is None:
            return
        self.traverse(root.left, k)
        # 中序代码位置
        self.rank += 1
        if k == self.rank:
            # 找到第 k 小的元素
            self.res = root.val
            return

        self.traverse(root.right, k)
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // 利用 BST 的中序遍历特性
        traverse(root, k);
        return res;
    }

    // 记录结果
    int res = 0;
    // 记录当前元素的排名
    int rank = 0;
    void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        // 中序代码位置
        rank++;
        if (k == rank) {
            // 找到第 k 小的元素
            res = root.val;
            return;
        }

        traverse(root.right, k);
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func kthSmallest(root *TreeNode, k int) int {
    // 利用 BST 的中序遍历特性
    var res int
    var rank int
    traverse(root, k, &res, &rank)
    return res
}

// 记录结果
// 记录当前元素的排名
func traverse(root *TreeNode, k int, res *int, rank *int) {
    if root == nil {
        return
    }
    traverse(root.Left, k, res, rank)
    // 中序代码位置
    *rank++
    if k == *rank {
        // 找到第 k 小的元素
        *res = root.Val
        return
    }
    traverse(root.Right, k, res, rank)
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

var kthSmallest = function(root, k) {
    // 记录结果
    let res = 0;
    // 记录当前元素的排名
    let rank = 0;

    var traverse = function(root, k) {
        if (root === null) {
            return;
        }
        traverse(root.left, k);
        // 中序代码位置
        rank++;
        if (k === rank) {
            // 找到第 k 小的元素
            res = root.val;
            return;
        }
        traverse(root.right, k);
    };

    // 利用 BST 的中序遍历特性
    traverse(root, k);
    return res;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🌈🌈 算法可视化 🌈🌈</strong></summary><div id="data_kth-smallest-element-in-a-bst" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_kth-smallest-element-in-a-bst"></div></div>
</details><hr /><br />

**类似题目**：
  - [1038. 从二叉搜索树到更大和树 🟠](/problems/binary-search-tree-to-greater-sum-tree)
  - [538. 把二叉搜索树转换为累加树 🟠](/problems/convert-bst-to-greater-tree)
  - [剑指 Offer 54. 二叉搜索树的第k大节点 🟢](/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof)
  - [剑指 Offer II 054. 所有大于等于节点的值之和 🟠](/problems/w6cpku)

</div>

</details>
</div>



