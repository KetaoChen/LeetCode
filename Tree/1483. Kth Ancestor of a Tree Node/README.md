# [1483. Kth Ancestor of a Tree Node (Hard)](https://leetcode.com/problems/kth-ancestor-of-a-tree-node/)

<p>You are given a tree with&nbsp;<code>n</code>&nbsp;nodes numbered from&nbsp;<code>0</code>&nbsp;to&nbsp;<code>n-1</code>&nbsp;in the form of a parent array where <code>parent[i]</code>&nbsp;is the parent of node <code>i</code>. The root of the tree is node <code>0</code>.</p>

<p>Implement the function&nbsp;<code>getKthAncestor</code><code>(int node, int k)</code>&nbsp;to return the <code>k</code>-th ancestor of the given&nbsp;<code>node</code>. If there is no such ancestor, return&nbsp;<code>-1</code>.</p>

<p>The&nbsp;<em>k-th&nbsp;</em><em>ancestor</em>&nbsp;of a tree node is the <code>k</code>-th node&nbsp;in the path&nbsp;from that node to the root.</p>

<p>&nbsp;</p>

<p><strong>Example:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/08/28/1528_ex1.png" style="width: 396px; height: 262px;"></strong></p>

<pre><b>Input:</b>
["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]

<b>Output:</b>
[null,1,0,-1]

<b>Explanation:</b>
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);

treeAncestor.getKthAncestor(3, 1);  // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2);  // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3);  // returns -1 because there is no such ancestor
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;=&nbsp;n &lt;= 5*10^4</code></li>
	<li><code>parent[0] == -1</code>&nbsp;indicating that&nbsp;<code>0</code>&nbsp;is the root node.</li>
	<li><code>0 &lt;= parent[i] &lt; n</code>&nbsp;for all&nbsp;<code>0 &lt;&nbsp;i &lt; n</code></li>
	<li><code>0 &lt;= node &lt; n</code></li>
	<li>There will be at most <code>5*10^4</code> queries.</li>
</ul>

**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution Multiplication (±¶Ôö)

```java
// OJ: https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
// Author: https://leetcode.com/charlesna/
// Time: O(nlogn)
// Space: O(n)
class TreeAncestor {
    int[][] fa;
    public TreeAncestor(int n, int[] parent) {
        fa = new int[n][16];
        for (int i = 0; i < n; i++) {
            fa[i][0] = parent[i];
        }
        for (int k = 1; k < 16; k++) {
            for (int i = 0; i < n; i++) {
                fa[i][k] = fa[i][k - 1] == -1 ? -1 : fa[fa[i][k - 1]][k - 1];
            }
        }
    }
    public int getKthAncestor(int node, int k) {
        for (int i = 0; i < 16; i++) {
            if ((k >> i & 1) == 1) {
                node = fa[node][i];
                if (node == -1) {
                    return node;
                }
            }
        }
        return node;
    }
}
/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
```