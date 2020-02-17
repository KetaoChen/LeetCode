# [947. Most Stones Removed with Same Row or Column (Medium)](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/)

<p>On a 2D plane, we place stones at some integer coordinate points.&nbsp; Each coordinate point may have at most one stone.</p>

<p>Now, a <em>move</em> consists of removing a stone&nbsp;that shares a column or row with another stone on the grid.</p>

<p>What is the largest possible number of moves we can make?</p>

<p>&nbsp;</p>

<div>
<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>stones = <span id="example-input-1-2">[[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]</span>
<strong>Output: </strong>5
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong>stones = <span id="example-input-2-2">[[0,0],[0,2],[1,1],[2,0],[2,2]]</span>
<strong>Output: </strong>3
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre><strong>Input: </strong>stones = <span id="example-input-3-2">[[0,0]]</span>
<strong>Output: </strong>0
</pre>

<p>&nbsp;</p>

<p><strong><span>Note:</span></strong></p>

<ol>
	<li><code>1 &lt;= stones.length &lt;= 1000</code></li>
	<li><code>0 &lt;= stones[i][j] &lt; 10000</code></li>
</ol>
</div>
</div>
</div>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Depth-first Search](https://leetcode.com/tag/depth-first-search/), [Union Find](https://leetcode.com/tag/union-find/)

## Solution 

```java
// OJ: https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    class UnionFind{
        private int[] parent;
        private int count;
        public UnionFind(int l) {
            parent = new int[l];
            for (int i = 0; i < l; i++) {
                parent[i] = i;
            }
            count = l;
        }
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                parent[parentX] = parentY;
                count--;
            }
        }
        public int getNum() {
            return count;
        }
    }
    public int removeStones(int[][] stones) {
        int l = stones.length;
        UnionFind uf = new UnionFind(l);
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        for (int i = 0; i < l; i++) {
            int[] stone = stones[i];
            int r = stone[0];
            int c = stone[1];
            if (row.containsKey(r)) {
                uf.union(i, row.get(r));
            }
            else {
                row.put(r, i);
            }
            if (col.containsKey(c)) {
                uf.union(i, col.get(c));
            }
            else {
                col.put(c, i);
            }
        }
        return l - uf.getNum();
    }
}
```