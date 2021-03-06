# [1319. Number of Operations to Make Network Connected (Medium)](https://leetcode.com/problems/number-of-operations-to-make-network-connected/)

<p>There are&nbsp;<code>n</code>&nbsp;computers numbered from&nbsp;<code>0</code>&nbsp;to&nbsp;<code>n-1</code>&nbsp;connected by&nbsp;ethernet cables&nbsp;<code>connections</code>&nbsp;forming a network where&nbsp;<code>connections[i] = [a, b]</code>&nbsp;represents a connection between computers&nbsp;<code>a</code>&nbsp;and&nbsp;<code>b</code>. Any computer&nbsp;can reach any other computer directly or indirectly through the network.</p>

<p>Given an initial computer network <code>connections</code>. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the <em>minimum number of times</em> you need to do this in order to make all the computers connected. If it's not possible, return -1.&nbsp;</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/01/02/sample_1_1677.png" style="width: 570px; height: 167px;"></strong></p>

<pre><strong>Input:</strong> n = 4, connections = [[0,1],[0,2],[1,2]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Remove cable between computer 1 and 2 and place between computers 1 and 3.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/01/02/sample_2_1677.png" style="width: 660px; height: 167px;"></strong></p>

<pre><strong>Input:</strong> n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are not enough cables.
</pre>

<p><strong>Example 4:</strong></p>

<pre><strong>Input:</strong> n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>1 &lt;= connections.length &lt;= min(n*(n-1)/2, 10^5)</code></li>
	<li><code>connections[i].length == 2</code></li>
	<li><code>0 &lt;= connections[i][0], connections[i][1]&nbsp;&lt; n</code></li>
	<li><code>connections[i][0] != connections[i][1]</code></li>
	<li>There are no repeated connections.</li>
	<li>No two computers are connected by more than one cable.</li>
</ul>

**Companies**:  
[Akuna Capital](https://leetcode.com/company/akuna-capital)

**Related Topics**:  
[Depth-first Search](https://leetcode.com/tag/depth-first-search/), [Breadth-first Search](https://leetcode.com/tag/breadth-first-search/), [Union Find](https://leetcode.com/tag/union-find/)

## Solution 

```java
// OJ: https://leetcode.com/problems/number-of-operations-to-make-network-connected/
// Author: https://leetcode.com/charlesna/
// Time: O(connections)
// Space: O(n)
class Solution {
    public int makeConnected(int n, int[][] connections) {
        int l = connections.length;
        if (l < n - 1) {
            return -1;
        }
        int[] p = new int[n];
        int count = n;
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int[] connection : connections) {
            int change = union(p, connection[0], connection[1]);
            count -= change;
        }
        return count - 1;
    }
    private int union(int[] p, int x, int y) {
        int px = find(x, p);
        int py = find(y, p);
        if (px != py) {
            p[px] = py;
            return 1;
        }
        return 0;
    }
    private int find(int x, int[] p) {
        if (p[x] == x) {
            return x;
        }
        p[x] = find(p[x], p);
        return p[x];
    }
}
```