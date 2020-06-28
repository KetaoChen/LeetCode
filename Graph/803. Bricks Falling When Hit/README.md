# [803. Bricks Falling When Hit (Hard)](https://leetcode.com/problems/bricks-falling-when-hit/)

<p>We have a grid of 1s and 0s; the 1s in a cell represent bricks.&nbsp; A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.</p>

<p>We will do some erasures&nbsp;sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may&nbsp;drop because of that&nbsp;erasure.</p>

<p>Return an array representing the number of bricks that will drop after each erasure in sequence.</p>

<pre><strong>Example 1:</strong>
<strong>Input:</strong> 
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
<strong>Output:</strong> [2]
<strong>Explanation: </strong>
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.</pre>

<pre><strong>Example 2:</strong>
<strong>Input:</strong> 
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
<strong>Output:</strong> [0,0]
<strong>Explanation: </strong>
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The number of rows and columns in the grid will be in the range&nbsp;[1, 200].</li>
	<li>The number of erasures will not exceed the area of the grid.</li>
	<li>It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.</li>
	<li>An erasure may refer to a location with no brick - if it does, no bricks drop.</li>
</ul>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Union Find](https://leetcode.com/tag/union-find/)

## Solution 

```java
// OJ: https://leetcode.com/problems/bricks-falling-when-hit/
// Author: https://leetcode.com/charlesna/
// Time: O(n*m)
// Space: O(n*m)
class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int l = hits.length;
        int[] res = new int[l];
        if (grid.length == 0 || grid[0].length == 0) return res;
        int row = grid.length, col = grid[0].length, tot = row * col;
        int[] uf = new int[tot + 1], g = new int[tot + 1];
        for (int i = 0; i <= tot; i++) {
            uf[i] = i;
            g[i] = 1;
        }
        // validate the hits
        boolean[] valid = new boolean[l];
        for (int i = 0; i <l; i++) {
            int x = hits[i][0], y = hits[i][1];
            if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) continue;
            grid[x][y] = 0;
            valid[i] = true;
        }
        // check the final state
        int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) continue;
                int index = i * col + j;
                if (i == 0) union(uf, g, index, tot);
                for (int d = 0; d < 4; d++) {
                    int x = i + dx[d], y = j + dy[d];
                    if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 1) {
                        int nIndex = x * col + y;
                        union(uf, g, index, nIndex);
                    }
                }
            }
        }
        // reverse the hit
        for (int i = l - 1; i >= 0; i--) {
            if (!valid[i]) continue;
            int x = hits[i][0], y = hits[i][1];
            int index = x * col + y;
            grid[x][y] = 1;
            int old = g[find(uf, tot)];
            if (x == 0) union(uf, g, index, tot);
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && grid[nx][ny] == 1) {
                    int nIndex = nx * col + ny;
                    union(uf, g, index, nIndex);
                }
            }
            res[i] = Math.max(0, g[find(uf, tot)] - old - 1);
        }
        return res;
    }
    private int find(int[] uf, int i) {
        if (uf[i] == i) return i;
        uf[i] = find(uf, uf[i]);
        return uf[i];
    }
    private void union(int[] uf, int[] g, int i, int j) {
        int pi = find(uf, i);
        int pj = find(uf, j);
        if (pi != pj) {
            uf[pi] = pj;
            g[pj] += g[pi];
        }
    }
}
```