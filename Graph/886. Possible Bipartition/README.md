# [886. Possible Bipartition (Medium)](https://leetcode.com/problems/possible-bipartition/)

<p>Given a set of <code>N</code>&nbsp;people (numbered <code>1, 2, ..., N</code>), we would like to split everyone into two groups of <strong>any</strong> size.</p>

<p>Each person may dislike some other people, and they should not go into the same group.&nbsp;</p>

<p>Formally, if <code>dislikes[i] = [a, b]</code>, it means it is not allowed to put the people numbered <code>a</code> and <code>b</code> into the same group.</p>

<p>Return <code>true</code>&nbsp;if and only if it is possible to split everyone into two groups in this way.</p>

<p>&nbsp;</p>

<div>
<div>
<ol>
</ol>
</div>
</div>

<div>
<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>N = <span id="example-input-1-1">4</span>, dislikes = <span id="example-input-1-2">[[1,2],[1,3],[2,4]]</span>
<strong>Output: </strong><span id="example-output-1">true</span>
<strong>Explanation</strong>: group1 [1,4], group2 [2,3]
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong>N = <span id="example-input-2-1">3</span>, dislikes = <span id="example-input-2-2">[[1,2],[1,3],[2,3]]</span>
<strong>Output: </strong><span id="example-output-2">false</span>
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre><strong>Input: </strong>N = <span id="example-input-3-1">5</span>, dislikes = <span id="example-input-3-2">[[1,2],[2,3],[3,4],[4,5],[1,5]]</span>
<strong>Output: </strong><span id="example-output-3">false</span>
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 2000</code></li>
	<li><code>0 &lt;= dislikes.length &lt;= 10000</code></li>
	<li><code>1 &lt;= dislikes[i][j] &lt;= N</code></li>
	<li><code>dislikes[i][0] &lt; dislikes[i][1]</code></li>
	<li>There does not exist <code>i != j</code> for which <code>dislikes[i] == dislikes[j]</code>.</li>
</ol>
</div>
</div>
</div>


**Companies**:  
[Facebook](https://leetcode.com/company/facebook)

**Related Topics**:  
[Depth-first Search](https://leetcode.com/tag/depth-first-search/)

## Solution 

```java
// OJ: https://leetcode.com/problems/possible-bipartition/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] st = new int[N + 1];
        List<Integer>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dis : dislikes) {
            graph[dis[0]].add(dis[1]);
            graph[dis[1]].add(dis[0]);
        }
        for (int i = 1; i <= N; i++) {
            if (st[i] != 0) continue;
            st[i] = 1;
            if (!valid(graph, st, i, 1)) return false;
        }
        return true;
    }
    private boolean valid(List<Integer>[] graph, int[] st, int index, int cur) {
        for (int next : graph[index]) {
            if (st[next] == cur) return false;
            if (st[next] == 3 - cur) continue;
            st[next] = 3 - cur;
            valid(graph, st, next, 3 - cur);
        }
        return true;
    }
}
```