# [743. Network Delay Time (Medium)](https://leetcode.com/problems/network-delay-time/)

<p>There are <code>N</code> network nodes, labelled <code>1</code> to <code>N</code>.</p>

<p>Given <code>times</code>, a list of travel times as <b>directed</b> edges <code>times[i] = (u, v, w)</code>, where <code>u</code> is the source node, <code>v</code> is the target node, and <code>w</code> is the time it takes for a signal to travel from source to target.</p>

<p>Now, we send a signal from a certain node <code>K</code>. How long will it take for all nodes to receive the signal? If it is impossible, return <code>-1</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/05/23/931_example_1.png" style="width: 200px; height: 220px;"></p>

<pre><strong>Input: </strong>times = <span id="example-input-1-1">[[2,1,1],[2,3,1],[3,4,1]]</span>, N = <span id="example-input-1-2">4</span>, K = <span id="example-input-1-3">2</span>
<strong>Output: </strong><span id="example-output-1">2</span>
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li><code>N</code> will be in the range <code>[1, 100]</code>.</li>
	<li><code>K</code> will be in the range <code>[1, N]</code>.</li>
	<li>The length of <code>times</code> will be in the range <code>[1, 6000]</code>.</li>
	<li>All edges <code>times[i] = (u, v, w)</code> will have <code>1 &lt;= u, v &lt;= N</code> and <code>0 &lt;= w &lt;= 100</code>.</li>
</ol>


**Companies**:  
[Google](https://leetcode.com/company/google), [Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Heap](https://leetcode.com/tag/heap/), [Depth-first Search](https://leetcode.com/tag/depth-first-search/), [Breadth-first Search](https://leetcode.com/tag/breadth-first-search/), [Graph](https://leetcode.com/tag/graph/)

## Solution 1: 

```java
// OJ: https://leetcode.com/problems/network-delay-time/
// Author: https://leetcode.com/charlesna/
// Time: O(V * E)
// Space: O(N*N)
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[K][K] = 0;
        for (int k = 0; k < N; k++) {
            for (int[] t : times) {
                if (dp[K][t[0]] != Integer.MAX_VALUE) {
                    dp[K][t[1]] = Math.min(dp[K][t[1]], dp[K][t[0]] + t[2]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, dp[K][i]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
```