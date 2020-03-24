# [1391. Check if There is a Valid Path in a Grid (Medium)](https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/)

Given a <em>m</em> x <em>n</em> <code>grid</code>. Each cell of the <code>grid</code> represents a street. The street of&nbsp;<code>grid[i][j]</code> can be:
<ul>
	<li><strong>1</strong> which means a street connecting the left cell and the right cell.</li>
	<li><strong>2</strong> which means a street connecting the upper cell and the lower cell.</li>
	<li><b>3</b>&nbsp;which means a street connecting the left cell and the lower cell.</li>
	<li><b>4</b> which means a street connecting the right cell and the lower cell.</li>
	<li><b>5</b> which means a street connecting the left cell and the upper cell.</li>
	<li><b>6</b> which means a street connecting the right cell and the upper cell.</li>
</ul>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/03/05/main.png" style="width: 450px; height: 708px;"></p>

<p>You will initially start at the street of the&nbsp;upper-left cell <code>(0,0)</code>. A valid path in the grid is a path which starts from the upper left&nbsp;cell <code>(0,0)</code> and ends at the bottom-right&nbsp;cell <code>(m - 1, n - 1)</code>. <strong>The path should only follow the streets</strong>.</p>

<p><strong>Notice</strong> that you are <strong>not allowed</strong> to change any street.</p>

<p>Return <i>true</i>&nbsp;if there is a valid path in the grid or <em>false</em> otherwise.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/03/05/e1.png" style="width: 455px; height: 311px;">
<pre><strong>Input:</strong> grid = [[2,4,3],[6,5,2]]
<strong>Output:</strong> true
<strong>Explanation:</strong> As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/03/05/e2.png" style="width: 455px; height: 293px;">
<pre><strong>Input:</strong> grid = [[1,2,1],[1,2,1]]
<strong>Output:</strong> false
<strong>Explanation:</strong> As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> grid = [[1,1,2]]
<strong>Output:</strong> false
<strong>Explanation:</strong> You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
</pre>

<p><strong>Example 4:</strong></p>

<pre><strong>Input:</strong> grid = [[1,1,1,1,1,1,3]]
<strong>Output:</strong> true
</pre>

<p><strong>Example 5:</strong></p>

<pre><strong>Input:</strong> grid = [[2],[2],[2],[2],[2],[2],[6]]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 6</code></li>
</ul>


**Companies**:  
[Robinhood](https://leetcode.com/company/robinhood)

**Related Topics**:  
[Depth-first Search](https://leetcode.com/tag/depth-first-search/), [Breadth-first Search](https://leetcode.com/tag/breadth-first-search/)

## Solution 

```java
// OJ: https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(m*n)
class Solution {
    public boolean hasValidPath(int[][] grid) {
        //          up, left, right, down 
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        // e.g. for the first cell, it has component left and right
        int[][] d = {{}, {1, 2}, {0, 3}, {1, 3}, {2, 3}, {0, 1}, {0, 2}};
        int n = grid.length, m = grid[0].length;
        // for each cell, check its components and check connectivity;
        int[] p = new int[n * m];
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = grid[i][j];
                for (int curd : d[val]) {
                    int nx = i + dx[curd];
                    int ny = j + dy[curd];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        int next = grid[nx][ny];
                        for (int nd : d[next]) {
                            // only left ^ right and up ^ down can get 1.
                            //        10 ^ 01        00 ^ 11
                            if ((nd ^ curd) == 3) {
                                union(p, i * m + j, nx * m + ny);
                            }
                        }
                    }
                }
            }
        }
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         System.out.print(p[i * m + j] + " ");
        //     }
        //     System.out.println();
        // }
        return find(p, 0) == find(p, m * n - 1);
    }
    private int find(int[] p, int x) {
        if (p[x] == x) {
            return x;
        }
        p[x] = find(p, p[x]);
        return p[x];
    }
    private void union(int[] p, int x, int y) {
        int px = find(p, x);
        int py = find(p, y);
        if (px != py) {
            p[py] = px;
        }
    }
}
```