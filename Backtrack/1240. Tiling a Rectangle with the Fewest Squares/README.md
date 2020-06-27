# [1240. Tiling a Rectangle with the Fewest Squares (Hard)](https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/)

<p>Given a rectangle of size&nbsp;<code>n</code>&nbsp;x <code><font face="monospace">m</font></code>, find the minimum number of integer-sided squares that tile the rectangle.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/10/17/sample_11_1592.png" style="width: 154px; height: 106px;"></p>

<pre><strong>Input:</strong> n = 2, m = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> <code>3</code> squares are necessary to cover the rectangle.
<code>2</code> (squares of <code>1x1</code>)
<code>1</code> (square of <code>2x2</code>)</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/10/17/sample_22_1592.png" style="width: 224px; height: 126px;"></p>

<pre><strong>Input:</strong> n = 5, m = 8
<strong>Output:</strong> 5
</pre>

<p><strong>Example 3:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/10/17/sample_33_1592.png" style="width: 224px; height: 189px;"></p>

<pre><strong>Input:</strong> n = 11, m = 13
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 13</code></li>
	<li><code>1 &lt;= m&nbsp;&lt;=&nbsp;13</code></li>
</ul>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/), [Backtracking](https://leetcode.com/tag/backtracking/)

## Solution 

```java
// OJ: https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/
// Author: https://leetcode.com/charlesna/
// Time: O(n!)
// Space: O(n)
class Solution {
    int res;
    public int tilingRectangle(int n, int m) {
        res = n * m;
        int[] base = new int[n];
        helper(base, n, m, 0);
        return res;
    }
    private void helper(int[] base, int n, int m, int k) {
        // prune. 
        if (k >= res) return;
        // find the left-bottom cell to put square.
        int min = m, index = -1;
        for (int i = 0; i < n; i++) {
            if (base[i] < min) {
                min = base[i];
                index = i;
            }
        }
        // when it is full.
        if (min == m) {
            res = Math.min(res, k);
            return;
        }
        // choosing from the large to small.
        for (int i = Math.min(n - index, m - base[index]); i >= 1; i--) {
            int[] next = base.clone();
            boolean valid = true;
            for (int j = 0; j < i; j++) {
                next[index + j] += i;
                if (next[index + j] > m) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                helper(next, n, m, k + 1);    
            }
        }
    }
}
```