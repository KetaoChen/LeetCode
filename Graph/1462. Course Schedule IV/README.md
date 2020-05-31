# [1462. Course Schedule IV (Medium)](https://leetcode.com/problems/course-schedule-iv/)

<p>There are a total of <code>n</code> courses you have to take, labeled from <code>0</code> to <code>n-1</code>.</p>

<p>Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: <code>[1,0]</code></p>

<p>Given the total number of courses <code>n</code>,&nbsp;a list of direct&nbsp;<code>prerequisite</code> <strong>pairs</strong> and a list of <code>queries</code> <strong>pairs</strong>.</p>

<p>You should answer for each <code>queries[i]</code> whether the course <code>queries[i][0]</code> is a&nbsp;prerequisite of the course&nbsp;<code>queries[i][1]</code> or not.</p>

<p>Return <em>a list of boolean</em>, the answers to the given <code>queries</code>.</p>

<p>Please note that if course <strong>a</strong> is a prerequisite of course <strong>b</strong> and course <strong>b</strong> is a prerequisite&nbsp;of course <strong>c</strong>, then, course <strong>a</strong> is a&nbsp;prerequisite of course <strong>c</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/04/17/graph.png" style="width: 300px; height: 300px;">
<pre><strong>Input:</strong> n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
<strong>Output:</strong> [false,true]
<strong>Explanation:</strong> course 0 is not a prerequisite of course 1 but the opposite is true.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> n = 2, prerequisites = [], queries = [[1,0],[0,1]]
<strong>Output:</strong> [false,false]
<strong>Explanation:</strong> There are no prerequisites and each course is independent.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/04/17/graph-1.png" style="width: 300px; height: 300px;">
<pre><strong>Input:</strong> n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
<strong>Output:</strong> [true,true]
</pre>

<p><strong>Example 4:</strong></p>

<pre><strong>Input:</strong> n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
<strong>Output:</strong> [false,true]
</pre>

<p><strong>Example 5:</strong></p>

<pre><strong>Input:</strong> n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
<strong>Output:</strong> [true,false,true,false]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= prerequisite.length &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= prerequisite[i][0], prerequisite[i][1] &lt; n</code></li>
	<li><code>prerequisite[i][0] != prerequisite[i][1]</code></li>
	<li>The prerequisites graph has no cycles.</li>
	<li>The prerequisites graph has no repeated edges.</li>
	<li><code>1 &lt;= queries.length &lt;= 10^4</code></li>
	<li><code>queries[i][0] != queries[i][1]</code></li>
</ul>


**Companies**:  
[Uber](https://leetcode.com/company/uber)

**Related Topics**:  
[Graph](https://leetcode.com/tag/graph/)

## Solution 1: Floyd

```java
// OJ: https://leetcode.com/problems/course-schedule-iv/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3)
// Space: O(n^2)
class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] pre, int[][] queries) {
        boolean[][] f = new boolean[n][n];
        for (int[] p : pre) {
            f[p[0]][p[1]] = true;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (f[i][k] && f[k][j]) {
                        f[i][j] = true;
                    }
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(f[q[0]][q[1]]);
        }
        return res;
    }
}
```

## Solution 2: Graph & DFS

```java
// OJ: https://leetcode.com/problems/course-schedule-iv/
// Author: https://leetcode.com/charlesna/
// Time: O(n*Q)
// Space: O(E)
class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] pre, int[][] que) {
        List<Boolean> res = new ArrayList<>();
        List<Integer>[] list = new List[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] p : pre) {
            list[p[0]].add(p[1]);
        }
        for (int[] qu : que) {
            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[n];
            q.offer(qu[0]);
            visited[qu[0]] = true;
            boolean is = false;
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == qu[1]) {
                    is = true;
                    break;
                }
                for (int next : list[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            res.add(is);
        }
        return res;
    }
}
```