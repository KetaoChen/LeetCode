# [1473. Paint House III (Hard)](https://leetcode.com/problems/paint-house-iii/)

<p>There is&nbsp;a row of&nbsp;<code>m</code>&nbsp;houses in a small city, each house must be painted with one of the&nbsp;<code>n</code>&nbsp;colors (labeled from 1 to <code>n</code>), some houses that has been painted last summer should not be painted again.</p>

<p>A neighborhood is a maximal group of continuous houses that are painted with the same color. (For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods&nbsp; [{1}, {2,2}, {3,3}, {2}, {1,1}]).</p>

<p>Given an array <code>houses</code>, an&nbsp;<code>m * n</code>&nbsp;matrix <code>cost</code> and&nbsp;an integer <code><font face="monospace">target</font></code>&nbsp;where:</p>

<ul>
	<li><code>houses[i]</code>:&nbsp;is the color of the house <code>i</code>, <strong>0</strong> if the house is not painted yet.</li>
	<li><code>cost[i][j]</code>: is the cost of paint the house <code>i</code> with the color <code>j+1</code>.</li>
</ul>

<p>Return the minimum cost of painting all the&nbsp;remaining houses in such a way that there are exactly <code>target</code> neighborhoods, if&nbsp;not possible return <strong>-1</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
<strong>Output:</strong> 9
<strong>Explanation:</strong> Paint houses of this way [1,2,2,1,1]
This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
<strong>Output:</strong> 11
<strong>Explanation:</strong> Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}]. 
Cost of paint the first and last house (10 + 1) = 11.
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
<strong>Output:</strong> 5
</pre>

<p><strong>Example 4:</strong></p>

<pre><strong>Input:</strong> houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
<strong>Output:</strong> -1
<strong>Explanation:</strong> Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == houses.length == cost.length</code></li>
	<li><code>n == cost[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= target&nbsp;&lt;= m</code></li>
	<li><code>0 &lt;= houses[i]&nbsp;&lt;= n</code></li>
	<li><code>1 &lt;= cost[i][j] &lt;= 10^4</code></li>
</ul>

**Companies**:  
[Paypal](https://leetcode.com/company/paypal)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 

```java
// OJ: https://leetcode.com/problems/paint-house-iii/
// Author: https://leetcode.com/charlesna/
// Time: O(m*t*n*n)
// Space: O(m*t*n)
class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // first ith houses, with t neighors, end with color j. the min cost.
        int[][][] dp = new int[m + 1][n + 1][target + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int t = 0; t <= target; t++) {
                    dp[i][j][t] = Integer.MAX_VALUE;
                }
            }
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j][0] = 0;
        }
        for (int t = 1; t <= target; t++) {
            for (int i = t; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // has painted
                    if (houses[i - 1] != 0 && houses[i - 1] != j) {
                        continue;
                    }
                    int c = houses[i - 1] != 0 ? 0 : cost[i - 1][j - 1];
                    // color of prev house
                    for (int k = 1; k <= n; k++) {
                        if (dp[i - 1][k][t] != Integer.MAX_VALUE && k == j) {
                            dp[i][j][t] = Math.min(dp[i][j][t], dp[i - 1][k][t] + c);
                        }
                        else if (dp[i - 1][k][t - 1] != Integer.MAX_VALUE && k != j) {
                            dp[i][j][t] = Math.min(dp[i][j][t], dp[i - 1][k][t - 1] + c);
                        }
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++) {
            res = Math.min(res, dp[m][j][target]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;    
    }
}
```