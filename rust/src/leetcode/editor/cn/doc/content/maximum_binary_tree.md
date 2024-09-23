<p>给定一个不重复的整数数组&nbsp;<code>nums</code> 。&nbsp;<strong>最大二叉树</strong>&nbsp;可以用下面的算法从&nbsp;<code>nums</code> 递归地构建:</p>

<ol> 
 <li>创建一个根节点，其值为&nbsp;<code>nums</code> 中的最大值。</li> 
 <li>递归地在最大值&nbsp;<strong>左边</strong>&nbsp;的&nbsp;<strong>子数组前缀上</strong>&nbsp;构建左子树。</li> 
 <li>递归地在最大值 <strong>右边</strong> 的&nbsp;<strong>子数组后缀上</strong>&nbsp;构建右子树。</li> 
</ol>

<p>返回&nbsp;<em><code>nums</code> 构建的 </em><strong><em>最大二叉树</em> </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/24/tree1.jpg" /> 
<pre>
<strong>输入：</strong>nums = [3,2,1,6,0,5]
<strong>输出：</strong>[6,3,5,null,2,0,null,null,1]
<strong>解释：</strong>递归调用如下所示：
- [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
    - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
        - 空数组，无子节点。
        - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
            - 空数组，无子节点。
            - 只有一个元素，所以子节点是一个值为 1 的节点。
    - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
        - 只有一个元素，所以子节点是一个值为 0 的节点。
        - 空数组，无子节点。
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/24/tree2.jpg" /> 
<pre>
<strong>输入：</strong>nums = [3,2,1]
<strong>输出：</strong>[3,null,2,null,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
 <li><code>0 &lt;= nums[i] &lt;= 1000</code></li> 
 <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>栈 | 树 | 数组 | 分治 | 二叉树 | 单调栈</details><br>

<div>👍 801, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：已完成网站教程、网站习题、配套插件中所有多语言代码的校准，解决了之前 chatGPT 翻译可能出错的问题~**



<p><strong><a href="https://labuladong.online/algo/slug.html?slug=maximum-binary-tree" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

<div id="labuladong_solution_zh">

## 基本思路

前文 [手把手刷二叉树总结篇](https://labuladong.online/algo/essential-technique/binary-tree-summary/) 说过二叉树的递归算法可以分两类，一类是遍历二叉树的类型，一类是分解子问题的类型。

前者较简单，只要运用二叉树的递归遍历框架即可；后者的关键在于明确递归函数的定义，然后利用这个定义。

这题是后者，函数 `build` 的定义是根据输入的数组构造最大二叉树，那么只要我先要找到根节点，然后让 `build` 函数递归生成左右子树即可。

**详细题解：[东哥带你刷二叉树（构造篇）](https://labuladong.online/algo/data-structure/binary-tree-part2/)**

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

#include <vector>
#include <climits>

class Solution {
public:
    // 主函数
    TreeNode* constructMaximumBinaryTree(std::vector<int>& nums) {
        return build(nums, 0, nums.size() - 1);
    }

    // 定义：将 nums[lo..hi] 构造成符合条件的树，返回根节点
    TreeNode* build(std::vector<int>& nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return nullptr;
        }

        // 找到数组中的最大值和对应的索引
        int index = -1, maxVal = INT_MIN;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        TreeNode* root = new TreeNode(maxVal);
        // 递归调用构造左右子树
        root->left = build(nums, lo, index - 1);
        root->right = build(nums, index + 1, hi);

        return root;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    # 主函数
    def constructMaximumBinaryTree(self, nums: List[int]) -> TreeNode:
        return self.build(nums, 0, len(nums) - 1)

    # 定义：将 nums[lo..hi] 构造成符合条件的树，返回根节点
    def build(self, nums: List[int], lo: int, hi: int) -> TreeNode:
        # base case
        if lo > hi:
            return None

        # 找到数组中的最大值和对应的索引
        index = -1
        max_val = float('-inf')
        for i in range(lo, hi + 1):
            if max_val < nums[i]:
                index = i
                max_val = nums[i]

        root = TreeNode(max_val)
        # 递归调用构造左右子树
        root.left = self.build(nums, lo, index - 1)
        root.right = self.build(nums, index + 1, hi)

        return root
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    // 主函数
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    // 定义：将 nums[lo..hi] 构造成符合条件的树，返回根节点
    TreeNode build(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }

        // 找到数组中的最大值和对应的索引
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxVal);
        // 递归调用构造左右子树
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

// 主函数
func constructMaximumBinaryTree(nums []int) *TreeNode {
    return build(nums, 0, len(nums)-1)
}

// 定义：将 nums[lo..hi] 构造成符合条件的树，返回根节点
func build(nums []int, lo, hi int) *TreeNode {
    // base case
    if lo > hi {
        return nil
    }

    // 找到数组中的最大值和对应的索引
    index, maxVal := -1, int(^uint(0)>>1) * -1
    for i := lo; i <= hi; i++ {
        if maxVal < nums[i] {
            index = i
            maxVal = nums[i]
        }
    }

    root := &TreeNode{Val: maxVal}
    // 递归调用构造左右子树
    root.Left = build(nums, lo, index-1)
    root.Right = build(nums, index+1, hi)

    return root
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

// 主函数
var constructMaximumBinaryTree = function(nums) {
    return build(nums, 0, nums.length - 1);
};

// 定义：将 nums[lo..hi] 构造成符合条件的树，返回根节点
var build = function(nums, lo, hi) {
    // base case
    if (lo > hi) {
        return null;
    }

    // 找到数组中的最大值和对应的索引
    let index = -1, maxVal = -Infinity;
    for (let i = lo; i <= hi; i++) {
        if (maxVal < nums[i]) {
            index = i;
            maxVal = nums[i];
        }
    }

    let root = new TreeNode(maxVal);
    // 递归调用构造左右子树
    root.left = build(nums, lo, index - 1);
    root.right = build(nums, index + 1, hi);

    return root;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>👾👾 算法可视化 👾👾</strong></summary><div id="data_maximum-binary-tree" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_maximum-binary-tree"></div></div>
</details><hr /><br />

**类似题目**：
  - [105. 从前序与中序遍历序列构造二叉树 🟠](/problems/construct-binary-tree-from-preorder-and-inorder-traversal)
  - [106. 从中序与后序遍历序列构造二叉树 🟠](/problems/construct-binary-tree-from-inorder-and-postorder-traversal)
  - [889. 根据前序和后序遍历构造二叉树 🟠](/problems/construct-binary-tree-from-preorder-and-postorder-traversal)
  - [998. 最大二叉树 II 🟠](/problems/maximum-binary-tree-ii)
  - [剑指 Offer 07. 重建二叉树 🟠](/problems/zhong-jian-er-cha-shu-lcof)

</div>

</details>
</div>











