<p>一个密码锁由 4&nbsp;个环形拨轮组成，每个拨轮都有 10 个数字： <code>'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'</code> 。每个拨轮可以自由旋转：例如把 <code>'9'</code> 变为&nbsp;<code>'0'</code>，<code>'0'</code> 变为 <code>'9'</code> 。每次旋转都只能旋转一个拨轮的一位数字。</p>

<p>锁的初始数字为 <code>'0000'</code> ，一个代表四个拨轮的数字的字符串。</p>

<p>列表 <code>deadends</code> 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。</p>

<p>字符串 <code>target</code> 代表可以解锁的数字，请给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>deadends = ["0201","0101","0102","1212","2002"], target = "0202"
<strong>输出：</strong>6
<strong>解释：</strong>
可能的移动序列为 "0000" -&gt; "1000" -&gt; "1100" -&gt; "1200" -&gt; "1201" -&gt; "1202" -&gt; "0202"。
注意 "0000" -&gt; "0001" -&gt; "0002" -&gt; "0102" -&gt; "0202" 这样的序列是不能解锁的，因为当拨动到 "0102" 时这个锁就会被锁定。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> deadends = ["8888"], target = "0009"
<strong>输出：</strong>1
<strong>解释：</strong>
把最后一位反向旋转一次即可 "0000" -&gt; "0009"。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
<strong>输出：</strong>-1
<strong>解释：
</strong>无法旋转到目标数字且不被锁定。
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入:</strong> deadends = ["0000"], target = "8888"
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;=&nbsp;deadends.length &lt;= 500</code></li> 
 <li><code><font face="monospace">deadends[i].length == 4</font></code></li> 
 <li><code><font face="monospace">target.length == 4</font></code></li> 
 <li><code>target</code> <strong>不在</strong> <code>deadends</code> 之中</li> 
 <li><code>target</code> 和 <code>deadends[i]</code> 仅由若干位数字组成</li> 
</ul>

<p>&nbsp;</p>

<p>
 <meta charset="UTF-8" />注意：本题与主站 752&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/open-the-lock/">https://leetcode-cn.com/problems/open-the-lock/</a></p>

<div>👍 47, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.gitee.io/article/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：算法可视化编辑器上线，[点击体验](https://labuladong.online/algo/intro/visualize/)！**



<p><strong><a href="https://labuladong.online/algo/slug.html?slug=zlDJc7" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

这道题和 [752. 打开转盘锁](/problems/open-the-lock) 相同。

> 本文有视频版：[BFS 算法核心框架套路](https://www.bilibili.com/video/BV1oT411u7Vn)

PS：这道题在[《算法小抄》](https://item.jd.com/12759911.html) 的第 53 页。

本质上就是穷举，在避开 `deadends` 密码的前提下，对四位密码的每一位进行 0~9 的穷举。

根据 BFS 算法的性质，第一次拨出 `target` 时的旋转次数就是最少的，直接套 [BFS 算法框架](https://labuladong.github.io/article/fname.html?fname=BFS框架) 即可。

另外，针对这道题的场景，还可以使用「双向 BFS」技巧进行优化，见详细题解。

**详细题解：[BFS 算法解题套路框架](https://labuladong.github.io/article/fname.html?fname=BFS框架)**

**标签：[BFS 算法](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2122002916411604996)**

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
    int openLock(vector<string>& deadends, string target) {
        // 记录需要跳过的死亡密码
        unordered_set<string> deads(deadends.begin(), deadends.end());
        // 记录已经穷举过的密码，防止走回头路
        unordered_set<string> visited;
        queue<string> q;
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.push("0000");
        visited.emplace("0000");

        while (!q.empty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                string cur = q.front();
                q.pop();

                /* 判断是否到达终点 */
                if (deads.find(cur) != deads.end())
                    continue;
                if (cur == target)
                    return step;

                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    string up = plusOne(cur, j);
                    if (visited.find(up) == visited.end()) {
                        q.push(up);
                        visited.emplace(up);
                    }
                    string down = minusOne(cur, j);
                    if (visited.find(down) == visited.end()) {
                        q.push(down);
                        visited.emplace(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }

    // 将 s[j] 向上拨动一次
    string plusOne(string s, int j) {
        if (s[j] == '9')
            s[j] = '0';
        else
            s[j] += 1;
        return s;
    }

    // 将 s[i] 向下拨动一次
    string minusOne(string s, int j) {
        if (s[j] == '0')
            s[j] = '9';
        else
            s[j] -= 1;
        return s;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def openLock(self, deadends: List[str], target: str) -> int:
        #记录需要跳过的死亡密码
        deads = set(deadends)
        #记录已经穷举过的密码，防止走回头路
        visited = set()
        q = deque()
        #从起点开始启动广度优先搜索
        step = 0
        q.append("0000")
        visited.add("0000")

        while q:
            sz = len(q)
            #将当前队列中的所有节点向周围扩散
            for i in range(sz):
                cur = q.popleft()

                #判断是否到达终点
                if cur in deads:
                    continue
                if cur == target:
                    return step

                # 将一个节点的未遍历相邻节点加入队列
                for j in range(4):
                    up = self.plusOne(cur, j)
                    if up not in visited:
                        q.append(up)
                        visited.add(up)
                    down = self.minusOne(cur, j)
                    if down not in visited:
                        q.append(down)
                        visited.add(down)

            #在这里增加步数
            step += 1
        #如果穷举完都没找到目标密码，那就是找不到了
        return -1

    
    #将 s[j] 向上拨动一次
    def plusOne(self, s: str, j: int) -> str:
        ch = list(s)
        if ch[j] == '9':
            ch[j] = '0'
        else:
            ch[j] = chr(ord(ch[j]) + 1)
        return "".join(ch)

    #将 s[i] 向下拨动一次
    def minusOne(self, s: str, j: int) -> str:
        ch = list(s)
        if ch[j] == '0':
            ch[j] = '9'
        else:
            ch[j] = chr(ord(ch[j]) - 1)
        return "".join(ch)
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();

                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (cur.equals(target))
                    return step;

                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }

    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func openLock(deadends []string, target string) int {
    // 记录需要跳过的死亡密码
    var deads = map[string]bool{}
    for _, s := range deadends {
        deads[s] = true
    }
    // 记录已经穷举过的密码，防止走回头路
    var visited = map[string]bool{}
    q := []string{"0000"}
    // 从起点开始启动广度优先搜索
    step := 0
    visited["0000"] = true

    for len(q) > 0 {
        sz := len(q)
        /* 将当前队列中的所有节点向周围扩散 */
        for i := 0; i < sz; i++ {
            cur := q[0]
            q = q[1:]

            /* 判断是否到达终点 */
            if deads[cur] {
                continue
            }
            if cur == target {
                return step
            }

            /* 将一个节点的未遍历相邻节点加入队列 */
            for j := 0; j < 4; j++ {
                up := plusOne(cur, j)
                if !visited[up] {
                    q = append(q, up)
                    visited[up] = true
                }
                down := minusOne(cur, j)
                if !visited[down] {
                    q = append(q, down)
                    visited[down] = true
                }
            }
        }
        /* 在这里增加步数 */
        step++
    }
    // 如果穷举完都没找到目标密码，那就是找不到了
    return -1
}

// 将 s[j] 向上拨动一次
func plusOne(s string, j int) string {
    ch := []byte(s)
    if ch[j] == '9' {
        ch[j] = '0'
    } else {
        ch[j] += 1
    }
    return string(ch)
}

// 将 s[i] 向下拨动一次
func minusOne(s string, j int) string {
    ch := []byte(s)
    if ch[j] == '0' {
        ch[j] = '9'
    } else {
        ch[j] -= 1
    }
    return string(ch)
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码不保证正确性，仅供参考。如有疑惑，可以参照我写的 java 代码对比查看。

var openLock = function (deadends, target) {
  // 记录需要跳过的死亡密码
  const deads = new Set(deadends);
  // 记录已经穷举过的密码，防止走回头路
  const visited = new Set();
  const q = [];
  let step = 0;
  // 从起点开始启动广度优先搜索
  q.push("0000");
  visited.add("0000");

  while (q.length > 0) {
    let sz = q.length;
    /* 将当前队列中的所有节点向周围扩散 */
    for (let i = 0; i < sz; i++) {
      let cur = q.shift();
      /* 判断是否到达终点 */
      if (deads.has(cur)) {
        continue;
      }
      if (cur === target) {
        return step;
      }
      /* 将一个节点的未遍历相邻节点加入队列 */
      for (let j = 0; j < 4; j++) {
        let up = plusOne(cur, j);
        if (!visited.has(up)) {
          q.push(up);
          visited.add(up);
        }
        let down = minusOne(cur, j);
        if (!visited.has(down)) {
          q.push(down);
          visited.add(down);
        }
      }
    }
    /* 在这里增加步数 */
    step++;
  }
  // 如果穷举完都没找到目标密码，那就是找不到了
  return -1;

  // 将 s[j] 向上拨动一次
  function plusOne(s, j) {
    let ch = s.split('');
    if (ch[j] === '9') {
      ch[j] = '0';
    } else {
      ch[j] = (+ch[j] + 1).toString();
    }
    return ch.join('');
  }
  // 将 s[i] 向下拨动一次
  function minusOne(s, j) {
    let ch = s.split('');
    if (ch[j] === '0') {
      ch[j] = '9';
    } else {
      ch[j] = (+ch[j] - 1).toString();
    }
    return ch.join('');
  }
};
```

</div></div>
</div></div>

**类似题目**：
  - [111. 二叉树的最小深度 🟢](/problems/minimum-depth-of-binary-tree)
  - [剑指 Offer II 109. 开密码锁 🟠](/problems/zlDJc7)

</details>
</div>
