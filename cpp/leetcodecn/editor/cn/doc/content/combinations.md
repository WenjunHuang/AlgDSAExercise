<p>给定两个整数 <code>n</code> 和 <code>k</code>，返回范围 <code>[1, n]</code> 中所有可能的 <code>k</code> 个数的组合。</p>

<p>你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 4, k = 2
<strong>输出：</strong>
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 1
<strong>输出：</strong>[[1]]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= n &lt;= 20</code></li> 
 <li><code>1 &lt;= k &lt;= n</code></li> 
</ul>

<div>👍 1608, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.gitee.io/article/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：算法可视化编辑器上线，[点击体验](https://labuladong.online/algo/intro/visualize/)！**



<p><strong><a href="https://labuladong.online/algo/slug.html?slug=combinations" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

> 本文有视频版：[回溯算法秒杀所有排列/组合/子集问题](https://www.bilibili.com/video/BV1Yt4y1t7dK)

PS：这道题在[《算法小抄》](https://item.jd.com/12759911.html) 的第 293 页。

这也是典型的回溯算法，`k` 限制了树的高度，`n` 限制了树的宽度，继续套我们以前讲过的 [回溯算法模板框架](https://labuladong.github.io/article/fname.html?fname=回溯算法详解修订版) 就行了：

![](https://labuladong.github.io/pictures/子集/2.jpg)

**详细题解：[回溯算法秒杀所有排列/组合/子集问题](https://labuladong.github.io/article/fname.html?fname=子集排列组合)**

**标签：[回溯算法](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2122002916411604996)，[数学](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2122023604245659649)**

## 解法代码

提示：🟢 标记的是我写的解法代码，🤖 标记的是 chatGPT 翻译的多语言解法代码。如有错误，可以 [点这里](https://github.com/labuladong/fucking-algorithm/issues/1113) 反馈和修正。

<div class="tab-panel"><div class="tab-nav">
<button data-tab-item="cpp" class="tab-nav-button btn active" data-tab-group="default" onclick="switchTab(this)">cpp🟢</button>

<button data-tab-item="python" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">python🤖</button>

<button data-tab-item="java" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">java🤖</button>

<button data-tab-item="go" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">go🤖</button>

<button data-tab-item="javascript" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">javascript🤖</button>
</div><div class="tab-content">
<div data-tab-item="cpp" class="tab-item active" data-tab-group="default"><div class="highlight">

```cpp
class Solution {
    public:

    vector<vector<int>>res;
    vector<vector<int>> combine(int n, int k) {
        if (k <= 0 || n <= 0) return res;
        vector<int> track;
        backtrack(n, k, 1, track);
        return res;
    }

    void backtrack(int n, int k, int start, vector<int>& track) {
        // 到达树的底部
        if (k == track.size()) {
            res.push_back(track);
            return;
        }
        // 注意 i 从 start 开始递增
        for (int i = start; i <= n; i++) {
            // 做选择
            track.push_back(i);
            backtrack(n, k, i + 1, track);
            // 撤销选择
            track.pop_back();
        }
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 cpp 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def __init__(self):
        self.res = []

    def combine(self, n: int, k: int) -> List[List[int]]:
        if k <= 0 or n <= 0:
            return self.res
        track = []
        self.backtrack(n, k, 1, track)
        return self.res

    def backtrack(self, n: int, k: int, start: int, track: List[int]) -> None:
        # 到达树的底部
        if k == len(track):
            self.res.append(track[:])
            return
        # 注意 i 从 start 开始递增
        for i in range(start, n + 1):
            # 做选择
            track.append(i)
            self.backtrack(n, k, i + 1, track)
            # 撤销选择
            track.pop()
```

</div></div>

<div data-tab-item="java" class="tab-item " data-tab-group="default"><div class="highlight">

```java
// 注意：java 代码由 chatGPT🤖 根据我的 cpp 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n <= 0) {
            return res;
        }
        List<Integer> track = new LinkedList<>();
        backtrack(n, k, 1, track);
        return res;
    }

    void backtrack(int n, int k, int start, List<Integer> track) {
        // 到达树的底部
        if (k == track.size()) {
            res.add(new LinkedList<>(track));
            return;
        }
        // 注意 i 从 start 开始递增
        for (int i = start; i <= n; i++) {
            // 做选择
            track.add(i);
            backtrack(n, k, i + 1, track);
            // 撤销选择
            track.remove(track.size() - 1);
        }
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 cpp 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func combine(n int, k int) [][]int {
    res := [][]int{}
    if k <= 0 || n <= 0 {
        return res
    }
    track := []int{}
    backtrack := func(n, k, start int, track []int) {}
    backtrack = func(n, k, start int, track []int) {
        // 到达树的底部
        if k == len(track) {
            ans := make([]int, k)
            copy(ans, track)
            res = append(res, ans)
            return
        }
        // 注意 i 从 start 开始递增
        for i := start; i <= n; i++ {
            // 做选择
            track = append(track, i)
            backtrack(n, k, i+1, track)
            // 撤销选择
            track = track[:len(track)-1]
        }
    }
    backtrack(n, k, 1, track)
    return res
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 cpp 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

var combine = function(n, k) {
    let res = [];
    if (k <= 0 || n <= 0) return res;
    let track = [];
    backtrack(n, k, 1, track, res);
    return res;
};

var backtrack = function(n, k, start, track, res) {
    // 到达树的底部
    if (k === track.length) {
        res.push([...track]);
        return;
    }
    // 注意 i 从 start 开始递增
    for (let i = start; i <= n; i++) {
        // 做选择
        track.push(i);
        backtrack(n, k, i + 1, track, res);
        // 撤销选择
        track.pop();
    }
};

// Example usage
console.log(combine(4, 2)); // [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>👾👾 算法可视化 👾👾</strong></summary><div id="data_combinations" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_combinations"></div></div>
</details><hr /><br />

**类似题目**：
  - [216. 组合总和 III 🟠](/problems/combination-sum-iii)
  - [39. 组合总和 🟠](/problems/combination-sum)
  - [40. 组合总和 II 🟠](/problems/combination-sum-ii)
  - [46. 全排列 🟠](/problems/permutations)
  - [47. 全排列 II 🟠](/problems/permutations-ii)
  - [78. 子集 🟠](/problems/subsets)
  - [90. 子集 II 🟠](/problems/subsets-ii)
  - [剑指 Offer II 079. 所有子集 🟠](/problems/TVdhkn)
  - [剑指 Offer II 080. 含有 k 个元素的组合 🟠](/problems/uUsW3B)
  - [剑指 Offer II 081. 允许重复选择元素的组合 🟠](/problems/Ygoe9J)
  - [剑指 Offer II 082. 含有重复元素集合的组合 🟠](/problems/4sjJUc)
  - [剑指 Offer II 083. 没有重复元素集合的全排列 🟠](/problems/VvJkup)
  - [剑指 Offer II 084. 含有重复元素集合的全排列 🟠](/problems/7p8L0Z)

</details>
</div>

