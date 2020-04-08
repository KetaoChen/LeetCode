# [1043. Partition Array for Maximum Sum (Medium)](https://leetcode.com/problems/partition-array-for-maximum-sum/)

<p>Given an integer array <code>A</code>, you partition the array into (contiguous) subarrays of length at most <code>K</code>.&nbsp; After partitioning, each subarray has their values changed to become the maximum value of that subarray.</p>

<p>Return the largest sum of the given array after partitioning.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>A = <span id="example-input-1-1">[1,15,7,9,2,5,10]</span>, K = <span id="example-input-1-2">3</span>
<strong>Output: </strong><span id="example-output-1">84
</span><strong>Explanation</strong>: A becomes [15,15,15,9,10,10,10]</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= K &lt;= A.length&nbsp;&lt;= 500</code></li>
	<li><code>0 &lt;= A[i] &lt;= 10^6</code></li>
</ol>


**Companies**:  
[Facebook](https://leetcode.com/company/facebook)

**Related Topics**:  
[Graph](https://leetcode.com/tag/graph/)

## Solution 

```java
// OJ: https://leetcode.com/problems/partition-array-for-maximum-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n*k)
// Space: O(n)
class Solution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        // dp[i] is the largest sum for first i items
        // XXXXXXX{j....i} dp[i] = max(dp[j - 1] + max[j][i] * len) where j = [i-k+1, i];
        int l = A.length;
        int[] dp = new int[l + 1];
        for (int i = 1; i <= l; i++) {
            dp[i] = dp[i - 1] + A[i - 1];
            int max = 0;
            for (int j = i; j >= Math.max(1, i - K + 1); j--) {
                max = Math.max(max, A[j - 1]);
                dp[i] = Math.max(dp[i], dp[j - 1] + max * (i - j + 1));
            }
        }
        return dp[l];
    }
}

// OJ: https://leetcode.com/problems/partition-array-for-maximum-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n*K)
// Space: O(n*n)
class Solution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        // dp[i] is the largest sum for first i items
        // XXXXXXX{j....i} dp[i] = max(dp[j - 1] + max[j][i] * len) where j = [i-k+1, i];
        int l = A.length;
        if (l == 0) return 0;
        int[][] max = new int[l][l];
        for (int len = 1; len <= K; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    max[i][j] = A[i];
                    continue;
                }
                max[i][j] = Math.max(max[i][j - 1], max[i + 1][j]);
            }
        }
        int[] dp = new int[l + 1];
        for (int i = 1; i <= l; i++) {
            dp[i] = dp[i - 1] + A[i - 1];
            for (int j = i; j >= Math.max(1, i - K + 1); j--) {
                dp[i] = Math.max(dp[i], dp[j - 1] + max[j - 1][i - 1] * (i - j + 1));
            }
        }
        return dp[l];
    }
}
```