# [305. Number of Islands II (Hard)](https://leetcode.com/problems/number-of-islands-ii/)

<p>A 2d grid map of <code>m</code> rows and <code>n</code> columns is initially filled with water. We may perform an <i>addLand</i> operation which turns the water at position (row, col) into a land. Given a list of positions to operate, <b>count the number of islands after each <i>addLand</i> operation</b>. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.</p>

<p><b>Example:</b></p>

<pre><b>Input:</b> m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
<b>Output:</b> [1,1,2,3]
</pre>

<p><b>Explanation:</b></p>

<p>Initially, the 2d grid <code>grid</code> is filled with water. (Assume 0 represents water and 1 represents land).</p>

<pre>0 0 0
0 0 0
0 0 0
</pre>

<p>Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.</p>

<pre>1 0 0
0 0 0   Number of islands = 1
0 0 0
</pre>

<p>Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.</p>

<pre>1 1 0
0 0 0   Number of islands = 1
0 0 0
</pre>

<p>Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.</p>

<pre>1 1 0
0 0 1   Number of islands = 2
0 0 0
</pre>

<p>Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.</p>

<pre>1 1 0
0 0 1   Number of islands = 3
0 1 0
</pre>

<p><b>Follow up:</b></p>

<p>Can you do it in time complexity O(k log mn), where k is the length of the <code>positions</code>?</p>


**Companies**:  
[Uber](https://leetcode.com/company/uber), [Facebook](https://leetcode.com/company/facebook), [Oracle](https://leetcode.com/company/oracle)

**Related Topics**:  
[Union Find](https://leetcode.com/tag/union-find/)

**Similar Questions**:
* [Number of Islands (Medium)](https://leetcode.com/problems/number-of-islands/)

## Solution 

```java
// OJ: https://leetcode.com/problems/number-of-islands-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int l = m * n, size = 0;
        int[] p = new int[l];
        for (int i = 0; i < l; i++) {
            p[i] = i;
        }
        List<Integer> res = new ArrayList<>();
        int num = 0;
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        Set<Integer> set = new HashSet<>();
        for (int[] add : positions) {
            int x = add[0], y = add[1], cur = x * n + y;
            if (set.contains(cur)) {
                res.add(num);
                continue;
            } 
            set.add(cur);
            int connected = 0;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d], next = nx * n + ny;
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && set.contains(next)) {
                    int pc = find(p, cur);
                    int pn = find(p, next);
                    if (pc != pn) {
                        connected++;
                        p[pn] = pc;
                    }
                }
            }
            num += 1 - connected;
            res.add(num);
        }
        return res;
    }
    private int find(int[] p, int x) {
        if (p[x] == x) return x;
        p[x] = find(p, p[x]);
        return p[x];
    }
}
```