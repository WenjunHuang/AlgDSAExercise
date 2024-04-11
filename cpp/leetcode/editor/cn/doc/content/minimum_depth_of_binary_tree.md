<p>给定一个二叉树，找出其最小深度。</p>

<p>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。</p>

<p><strong>说明：</strong>叶子节点是指没有子节点的节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/12/ex_depth.jpg" style="width: 432px; height: 302px;" /> 
<pre>
<strong>输入：</strong>root = [3,9,20,null,null,15,7]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [2,null,3,null,4,null,5,null,6]
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点数的范围在 <code>[0, 10<sup>5</sup>]</code> 内</li> 
 <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
</ul>

<div>👍 1175, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.gitee.io/article/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：算法可视化编辑器上线，[点击体验](https://labuladong.online/algo/intro/visualize/)！**



<p><strong><a href="https://labuladong.online/algo/slug.html?slug=minimum-depth-of-binary-tree" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

> 本文有视频版：[BFS 算法核心框架套路](https://www.bilibili.com/video/BV1oT411u7Vn)

前文 [手把手刷二叉树总结篇](https://labuladong.github.io/article/fname.html?fname=二叉树总结) 说过二叉树的递归分为「遍历」和「分解问题」两种思维模式，这道题可以同时运用两种思维，而且还可以用 BFS 层序遍历的方式求解，我会给出三种解法。

希望大家借助这道题仔细理解二叉树和 [BFS 算法](https://labuladong.github.io/article/fname.html?fname=BFS框架)、[动态规划算法](https://labuladong.github.io/article/fname.html?fname=动态规划详解进阶)、[回溯算法](https://labuladong.github.io/article/fname.html?fname=回溯算法详解修订版) 的联系。

**详细题解：[BFS 算法解题套路框架](https://labuladong.github.io/article/fname.html?fname=BFS框架)**

**标签：[BFS 算法](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2122002916411604996)，[二叉树](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2121994699837177859)**

## 解法代码

提示：🟢 标记的是我写的解法代码，🤖 标记的是 chatGPT 翻译的多语言解法代码。如有错误，可以 [点这里](https://github.com/labuladong/fucking-algorithm/issues/1113) 反馈和修正。

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
    int minDepth(TreeNode* root) {
        if (root == NULL) return 0;
        queue<TreeNode*> q;
        q.push(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.empty()) {
            /* 层数 step */
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                TreeNode* cur = q.front();
                q.pop();
                /* 判断是否到达终点 */
                if (cur->left == NULL && cur->right == NULL)
                    return depth;
                /* 将 cur 的相邻节点加入队列 */
                if (cur->left != NULL)
                    q.push(cur->left);
                if (cur->right != NULL)
                    q.push(cur->right);
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def minDepth(self, root: TreeNode) -> int:
        if not root:
            return 0
        q = collections.deque([root])
        # root 本身就是一层，depth 初始化为 1
        depth = 1
        while q:
            # extend down -200>
            # ![](https://labuladong.github.io/pictures/dijkstra/1.jpeg)
            sz = len(q)
            ## 遍历当前层的节点
            for i in range(sz):
                cur = q.popleft()
                ## 判断是否到达叶子结点
                if not cur.left and not cur.right:
                    return depth
                ## 将下一层节点加入队列
                if cur.left:
                    q.append(cur.left)
                if cur.right:
                    q.append(cur.right)
            ## 这里增加步数
            depth += 1
        return depth
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
// 「迭代」的递归思路
class Solution {
    private int minDepth = Integer.MAX_VALUE;
    private int currentDepth = 0;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traverse(root);
        return minDepth;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // 做选择：在进入节点时增加当前深度
        currentDepth++;

        // 如果当前节点是叶子节点，更新最小深度
        if (root.left == null && root.right == null) {
            minDepth = Math.min(minDepth, currentDepth);
        }

        traverse(root.left);
        traverse(root.right);

        // 撤销选择：在离开节点时减少当前深度
        currentDepth--;
    }
}

// 「分解问题」的递归思路
class Solution2 {
    public int minDepth(TreeNode root) {
        // 基本情况：如果节点为空，返回深度为0
        if (root == null) {
            return 0;
        }

        // 递归计算左子树的最小深度
        int leftDepth = minDepth(root.left);
        // 递归计算右子树的最小深度
        int rightDepth = minDepth(root.right);

        // 特殊情况处理：如果左子树为空，返回右子树的深度加1
        if (leftDepth == 0) {
            return rightDepth + 1;
        }
        // 特殊情况处理：如果右子树为空，返回左子树的深度加1
        if (rightDepth == 0) {
            return leftDepth + 1;
        }

        // 计算并返回最小深度：左右子树深度的最小值加1
        return Math.min(leftDepth, rightDepth) + 1;
    }
}


// BFS 的思路
class Solution3 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {/**<extend down -200>![](https://labuladong.github.io/pictures/dijkstra/1.jpeg) */
            int sz = q.size();
            /* 遍历当前层的节点 */
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                /* 判断是否到达叶子结点 */
                if (cur.left == null && cur.right == null)
                    return depth;
                /* 将下一层节点加入队列 */
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * Definition for TreeNode.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minDepth(root *TreeNode) int {
    if root == nil {
        return 0
    }
    q := []*TreeNode{root}
    // root 本身就是一层，depth 初始化为 1
    depth := 1

    for len(q) != 0 {/**<extend down -200>![](https://labuladong.github.io/pictures/dijkstra/1.jpeg) */
        sz := len(q)
        /* 遍历当前层的节点 */
        for i := 0; i < sz; i++ {
            cur := q[0]
            q = q[1:]
            /* 判断是否到达叶子结点 */
            if cur.Left == nil && cur.Right == nil {
                return depth
            }
            /* 将下一层节点加入队列 */
            if cur.Left != nil {
                q = append(q, cur.Left)
            }
            if cur.Right != nil {
                q = append(q, cur.Right)
            }
        }
        /* 这里增加步数 */
        depth++
    }
    return depth
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * @param {TreeNode} root
 * @return {number}
 */
var minDepth = function(root) {
    if (root === null) return 0;
    var q = [];
    q.push(root);
    //root本身就是一层，depth初始化为 1
    var depth = 1;

    while (q.length !== 0) {/**<extend down -200>![](https://labuladong.github.io/pictures/dijkstra/1.jpeg) */
        var sz = q.length;
        /* 遍历当前层的节点 */
        for (var i = 0; i < sz; i++) {

            var cur = q.shift();
            /* 判断是否到达叶子结点 */
            if (!cur.left && !cur.right) {
                return depth;
            }
            /* 将下一层节点加入队列 
            */
           if (cur.left !== null) {
             q.push(cur.left);
            }
            if (cur.right !== null) {
              q.push(cur.right);
             }
            
        }
        /* 这里增加步数 */
        depth++;
    }
    return depth;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🍭🍭 算法可视化 🍭🍭</strong></summary><div id="data_minimum-depth-of-binary-tree" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_minimum-depth-of-binary-tree"></div></div>
</details><hr /><br />

**类似题目**：
  - [542. 01 矩阵 🟠](/problems/01-matrix)
  - [752. 打开转盘锁 🟠](/problems/open-the-lock)
  - [剑指 Offer II 109. 开密码锁 🟠](/problems/zlDJc7)

</details>
</div>

