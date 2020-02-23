# [931. Minimum Falling Path Sum (Medium)](https://leetcode.com/problems/minimum-falling-path-sum/)

<p>Given a <strong>square</strong> array of integers <code>A</code>, we want the <strong>minimum</strong> sum of a <em>falling path</em> through <code>A</code>.</p>

<p>A falling path starts at any element in the first row, and chooses one element from each row.&nbsp; The next row's choice must be in a column that is different from the previous row's column by at most one.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">[[1,2,3],[4,5,6],[7,8,9]]</span>
<strong>Output: </strong><span id="example-output-1">12</span>
<strong>Explanation: </strong>
The possible falling paths are:
</pre>

<ul>
	<li><code>[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]</code></li>
	<li><code>[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]</code></li>
	<li><code>[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]</code></li>
</ul>

<p>The falling path with the smallest sum is <code>[1,4,7]</code>, so the answer is <code>12</code>.</p>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length == A[0].length &lt;= 100</code></li>
	<li><code>-100 &lt;= A[i][j] &lt;= 100</code></li>
</ol>

**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Minimum Falling Path Sum II (Hard)](https://leetcode.com/problems/minimum-falling-path-sum-ii/)

## Solution 1: Graph & Bellman-Ford 1 relax

```java
// OJ: https://leetcode.com/problems/minimum-falling-path-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int minFallingPathSum(int[][] A) {
        //bellman-ford.
        //shortest path from top to bottom.
        int l = A.length;
        int[][] dis = new int[l][l];
        for (int[] d : dis) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int j = 0; j < l; j++) {
            dis[0][j] = A[0][j];
        }
        for (int i = 0; i < l - 1; i++) {
            for (int j = 0; j < l; j++) {
                for (int k = Math.max(0, j - 1); k < Math.min(l, j + 2); k++) {
                    dis[i + 1][k] = Math.min(dis[i + 1][k], dis[i][j] + A[i + 1][k]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < l; j++) {
            res = Math.min(res, dis[l - 1][j]);
        }
        return res;
    }
}
```

## Solution 2: DP

```java
// OJ: https://leetcode.com/problems/minimum-falling-path-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int minFallingPathSum(int[][] arr) {
        int l = arr.length;
        int[][] dp = new int[l + 1][l];
        for (int i = 1; i <= l; i++) {
            for (int j = 0; j < l; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = Math.max(0, j - 1); k < Math.min(l, j + 2); k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + arr[i - 1][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < l; i++) {
            res = Math.min(res, dp[l][i]);
        }
        return res;
    }
}
```