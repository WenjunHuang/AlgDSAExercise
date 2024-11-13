<p>给定一棵二叉树的根节点 <code>root</code>，返回给定节点 <code>p</code> 和 <code>q</code> 的最近公共祖先（LCA）节点。如果 <code>p</code> 或 <code>q</code> 之一<strong> 不存在</strong> 于该二叉树中，返回 <code>null</code>。树中的每个节点值都是互不相同的。</p>

<p>根据<a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">维基百科中对最近公共祖先节点的定义</a>：“两个节点 <code>p</code> 和 <code>q</code> 在二叉树 <code>T</code> 中的最近公共祖先节点是<strong> 后代节点 </strong>中既包括 <code>p</code>&nbsp;又包括&nbsp;<code>q</code>&nbsp;的最深节点（我们允许<strong> 一个节点为自身的一个后代节点 </strong>）”。一个节点 <code>x</code>&nbsp;的<strong> 后代节点 </strong>是节点&nbsp;<code>x</code> 到某一叶节点间的路径中的节点 <code>y</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" /> 
<pre>
<b>输入：</b> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
<b>输出：</b> 3
<b>解释：</b> 节点 5 和 1 的共同祖先节点是 3。</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" /></p>

<pre>
<b>输入：</b> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
<b>输出：</b> 5
<b>解释：</b> 节点 5 和 4 的共同祖先节点是 5。根据共同祖先节点的定义，一个节点可以是自身的后代节点。</pre>

<p><strong>示例 3:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" /></p>

<pre>
<strong>输入：</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
<b>输出：</b> null
<b>解释：</b> 节点 10 不存在于树中，所以返回 null。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li>树中节点个数的范围是&nbsp;<code>[1, 10<sup>4</sup>]</code></li> 
 <li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li> 
 <li>所有节点的值&nbsp;<code>Node.val</code> <strong>互不相同</strong></li> 
 <li><code>p != q</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong> 在不检查节点是否存在的情况下，你可以遍历树找出最近公共祖先节点吗？</p>

<details><summary><strong>Related Topics</strong></summary>树 | 深度优先搜索 | 二叉树</details><br>

<div>👍 47, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/issues' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：网站新增大量习题，新增排序算法专题及可视化，具体请查看 [网站更新日志](https://labuladong.online/algo/changelog/website/)~**



<p><strong><a href="https://labuladong.online/algo/practice-in-action/lowest-common-ancestor-summary/" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

<!-- vip -->
<!-- i_63956417e4b02685a425cc0d -->

本题思路为 labuladong 网站会员专属，请 [点击这里](https://labuladong.online/algo/intro/site-vip/) 购买会员并「按照各个插件的解锁方法手动刷新数据」。

若之前已经购买会员并成功解锁插件，现在却突然出现这个问题，是因为添加了新的题解数据。请尝试重新手动刷新插件数据。进入 [会员购买页](https://labuladong.online/algo/intro/site-vip/) 向下翻即可查看各个插件刷新数据的方法。

若依然无法解决问题，可以在按照 [bug 反馈页面](https://labuladong.online/algo/intro/bug-report/) 的提示像我反馈问题，如是 bug 我会立即修复。</details>
</div>

