<p>给定两个整数数组&nbsp;<code>preorder</code> 和 <code>inorder</code>&nbsp;，其中&nbsp;<code>preorder</code> 是二叉树的<strong>先序遍历</strong>， <code>inorder</code>&nbsp;是同一棵树的<strong>中序遍历</strong>，请构造二叉树并返回其根节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" style="height: 302px; width: 277px;" /> 
<pre>
<strong>输入</strong><strong>:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
<strong>输出:</strong> [3,9,20,null,null,15,7]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> preorder = [-1], inorder = [-1]
<strong>输出:</strong> [-1]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>1 &lt;= preorder.length &lt;= 3000</code></li> 
 <li><code>inorder.length == preorder.length</code></li> 
 <li><code>-3000 &lt;= preorder[i], inorder[i] &lt;= 3000</code></li> 
 <li><code>preorder</code>&nbsp;和&nbsp;<code>inorder</code>&nbsp;均 <strong>无重复</strong> 元素</li> 
 <li><code>inorder</code>&nbsp;均出现在&nbsp;<code>preorder</code></li> 
 <li><code>preorder</code>&nbsp;<strong>保证</strong> 为二叉树的前序遍历序列</li> 
 <li><code>inorder</code>&nbsp;<strong>保证</strong> 为二叉树的中序遍历序列</li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>树 | 数组 | 哈希表 | 分治 | 二叉树</details><br>

<div>👍 2377, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：已完成网站教程、网站习题、配套插件中所有多语言代码的校准，解决了之前 chatGPT 翻译可能出错的问题~**



<p><strong><a href="https://labuladong.online/algo/slug.html?slug=construct-binary-tree-from-preorder-and-inorder-traversal" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

<div id="labuladong_solution_zh">

## 基本思路

**构造二叉树，第一件事一定是找根节点，然后想办法构造左右子树**。

二叉树的前序和中序遍历结果的特点如下：

![](https://labuladong.online/algo/images/二叉树系列2/1.jpeg)

前序遍历结果第一个就是根节点的值，然后再根据中序遍历结果确定左右子树的节点。

![](https://labuladong.online/algo/images/二叉树系列2/4.jpeg)

结合这个图看代码辅助理解。

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
#include <unordered_map>
using namespace std;

class Solution {
    // 存储 inorder 中值到索引的映射
    unordered_map<int, int> valToIndex;

public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        for (int i = 0; i < inorder.size(); i++) {
            valToIndex[inorder[i]] = i;
        }
        return build(preorder, 0, preorder.size() - 1, inorder, 0, inorder.size() - 1);
    }

private:
    // 定义：前序遍历数组为 preorder[preStart..preEnd]，
    // 中序遍历数组为 inorder[inStart..inEnd]，
    // 构造这个二叉树并返回该二叉树的根节点
    TreeNode* build(vector<int>& preorder, int preStart, int preEnd, vector<int>& inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return nullptr;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = valToIndex[rootVal];
        int leftSize = index - inStart;

        // 先构造出当前根节点
        TreeNode* root = new TreeNode(rootVal);
        // 递归构造左右子树
        root->left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1);
        root->right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);
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
    # 存储 inorder 中值到索引的映射
    def __init__(self):
        self.valToIndex = {}

    def buildTree(self, preorder, inorder):
        for i in range(len(inorder)):
            self.valToIndex[inorder[i]] = i
        return self.build(preorder, 0, len(preorder) - 1,
                          inorder, 0, len(inorder) - 1)

    '''
       定义：前序遍历数组为 preorder[preStart..preEnd]，
       中序遍历数组为 inorder[inStart..inEnd]，
       构造这个二叉树并返回该二叉树的根节点
    '''
    def build(self, preorder, preStart, preEnd, inorder, inStart, inEnd):
        if preStart > preEnd:
            return None

        # root 节点对应的值就是前序遍历数组的第一个元素
        rootVal = preorder[preStart]
        # rootVal 在中序遍历数组中的索引
        index = self.valToIndex[rootVal]

        leftSize = index - inStart

        # 先构造出当前根节点
        root = TreeNode(rootVal)

        # 递归构造左右子树
        root.left = self.build(preorder, preStart + 1, preStart + leftSize,
                               inorder, inStart, index - 1)

        root.right = self.build(preorder, preStart + leftSize + 1, preEnd,
                                inorder, index + 1, inEnd)
        return root
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    // 存储 inorder 中值到索引的映射
    HashMap<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                    inorder, 0, inorder.length - 1);
    }

    /*
       定义：前序遍历数组为 preorder[preStart..preEnd]，
       中序遍历数组为 inorder[inStart..inEnd]，
       构造这个二叉树并返回该二叉树的根节点
    */
    TreeNode build(int[] preorder, int preStart, int preEnd,
                   int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = valToIndex.get(rootVal);

        int leftSize = index - inStart;

        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);/**<extend up -200>![](https://labuladong.online/algo/images/二叉树系列2/4.jpeg) */
        // 递归构造左右子树
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);

        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);
        return root;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func buildTree(preorder []int, inorder []int) *TreeNode {
    // 存储 inorder 中值到索引的映射
    valToIndex := make(map[int]int)
    for i, val := range inorder {
        valToIndex[val] = i
    }
    return build(preorder, 0, len(preorder)-1, inorder, 0, len(inorder)-1, valToIndex)
}

/*
   定义：前序遍历数组为 preorder[preStart..preEnd]，
   中序遍历数组为 inorder[inStart..inEnd]，
   构造这个二叉树并返回该二叉树的根节点
*/
func build(preorder []int, preStart, preEnd int, inorder []int, inStart, inEnd int, valToIndex map[int]int) *TreeNode {
    if preStart > preEnd {
        return nil
    }

    // root 节点对应的值就是前序遍历数组的第一个元素
    rootVal := preorder[preStart]
    // rootVal 在中序遍历数组中的索引
    index := valToIndex[rootVal]

    leftSize := index - inStart

    // 先构造出当前根节点
    root := &TreeNode{Val: rootVal}

    // 递归构造左右子树
    root.Left = build(preorder, preStart+1, preStart+leftSize, inorder, inStart, index-1, valToIndex)
    root.Right = build(preorder, preStart+leftSize+1, preEnd, inorder, index+1, inEnd, valToIndex)
    return root
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

var buildTree = function(preorder, inorder) {
    // 存储 inorder 中值到索引的映射
    let valToIndex = new Map();
    for (let i = 0; i < inorder.length; i++) {
        valToIndex.set(inorder[i], i);
    }

    var build = function(preorder, preStart, preEnd, inorder, inStart, inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        let rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        let index = valToIndex.get(rootVal);

        let leftSize = index - inStart;

        // 先构造出当前根节点
        let root = new TreeNode(rootVal);

        // 递归构造左右子树
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                          inorder, inStart, index - 1);

        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                           inorder, index + 1, inEnd);
        return root;
    };

    return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
};
```

</div></div>
</div></div>

**类似题目**：
  - [106. 从中序与后序遍历序列构造二叉树 🟠](/problems/construct-binary-tree-from-inorder-and-postorder-traversal)
  - [654. 最大二叉树 🟠](/problems/maximum-binary-tree)
  - [889. 根据前序和后序遍历构造二叉树 🟠](/problems/construct-binary-tree-from-preorder-and-postorder-traversal)
  - [剑指 Offer 07. 重建二叉树 🟠](/problems/zhong-jian-er-cha-shu-lcof)

</div>

</details>
</div>

