<p>给你一个整数 <code>n</code> ，求恰由 <code>n</code> 个节点组成且节点值从 <code>1</code> 到 <code>n</code> 互不相同的 <strong>二叉搜索树</strong> 有多少种？返回满足题意的二叉搜索树的种数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/uniquebstn3.jpg" style="width: 600px; height: 148px;" /> 
<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= n &lt;= 19</code></li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>树 | 二叉搜索树 | 数学 | 动态规划 | 二叉树</details><br>

<div>👍 2554, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/issues' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：网站新增大量习题，新增排序算法专题及可视化，具体请查看 [网站更新日志](https://labuladong.online/algo/changelog/website/)~**



<p><strong><a href="https://labuladong.online/algo/data-structure/bst-part3/" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

<div id="labuladong_solution_zh">

## 基本思路

假设给算法输入 `n = 5`，也就是说用 `{1,2,3,4,5}` 这些数字去构造 BST。

如果固定 `3` 作为根节点，左子树节点就是 `{1,2}` 的组合，右子树就是 `{4,5}` 的组合：

![](https://labuladong.online/algo/images/BST3/1.jpeg)

那么 `{1,2}` 和 `{4,5}` 的组合有多少种呢？只要合理定义递归函数，这些可以交给递归函数去做。

另外，这题存在重叠子问题，可以通过备忘录的方式消除冗余计算。

- **详细题解**：
  - [二叉搜索树心法（构造篇）](https://labuladong.online/algo/data-structure/bst-part3/)

</div>



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
// 注意：cpp 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照我的 java 代码查看。

#include <vector>

class Solution {
public:
    // 备忘录
    std::vector<std::vector<int>> memo;

    int numTrees(int n) {
        // 备忘录的值初始化为 0
        memo = std::vector<std::vector<int>>(n + 1, std::vector<int>(n + 1, 0));
        return count(1, n);
    }

private:
    int count(int lo, int hi) {
        if (lo > hi) return 1;
        // 查备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = count(lo, mid - 1);
            int right = count(mid + 1, hi);
            res += left * right;
        }
        // 将结果存入备忘录
        memo[lo][hi] = res;

        return res;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译。
# 本代码的正确性已通过力扣验证，如有疑问，可以对照我的 java 代码查看。

class Solution:
    # 备忘录
    def __init__(self):
        self.memo = []

    def numTrees(self, n: int) -> int:
        # 备忘录的值初始化为 0
        self.memo = [[0] * (n + 1) for _ in range(n + 1)]
        return self.count(1, n)

    def count(self, lo: int, hi: int) -> int:
        if lo > hi:
            return 1
        # 查备忘录
        if self.memo[lo][hi] != 0:
            return self.memo[lo][hi]

        res = 0
        for mid in range(lo, hi + 1):
            left = self.count(lo, mid - 1)
            right = self.count(mid + 1, hi)
            res += left * right
        # 将结果存入备忘录
        self.memo[lo][hi] = res

        return res
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    // 备忘录
    int[][] memo;

    int numTrees(int n) {
        // 备忘录的值初始化为 0
        memo = new int[n + 1][n + 1];
        return count(1, n);
    }

    int count(int lo, int hi) {
        if (lo > hi) return 1;
        // 查备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = count(lo, mid - 1);
            int right = count(mid + 1, hi);
            res += left * right;
        }
        // 将结果存入备忘录
        memo[lo][hi] = res;

        return res;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照我的 java 代码查看。

func numTrees(n int) int {
    // 备忘录的值初始化为 0
    memo := make([][]int, n+1)
    for i := 0; i <= n; i++ {
        memo[i] = make([]int, n+1)
    }
    return count(1, n, memo)
}

// 二叉搜索树计数函数
// 备忘录
func count(lo int, hi int, memo [][]int) int {
    if lo > hi {
        return 1
    }
    // 查备忘录
    if memo[lo][hi] != 0 {
        return memo[lo][hi]
    }

    res := 0
    for mid := lo; mid <= hi; mid++ {
        left := count(lo, mid-1, memo)
        right := count(mid+1, hi, memo)
        res += left * right
    }
    // 将结果存入备忘录
    memo[lo][hi] = res
    return res
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照我的 java 代码查看。

var numTrees = function(n) {
    // 备忘录
    let memo = new Array(n + 1).fill(0).map(() => new Array(n + 1).fill(0));

    // 备忘录的值初始化为 0

    function count(lo, hi) {
        if (lo > hi) return 1;
        // 查备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        let res = 0;
        for (let mid = lo; mid <= hi; mid++) {
            let left = count(lo, mid - 1);
            let right = count(mid + 1, hi);
            res += left * right;
        }
        // 将结果存入备忘录
        memo[lo][hi] = res;

        return res;
    }
    
    return count(1, n);
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🌟🌟 算法可视化 🌟🌟</strong></summary><div id="data_unique-binary-search-trees" data="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" ></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_unique-binary-search-trees"></div></div>
</details><hr /><br />

</div>

</details>
</div>

