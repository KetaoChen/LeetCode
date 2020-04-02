# [813. Largest Sum of Averages (Medium)](https://leetcode.com/problems/largest-sum-of-averages/)

<p>We partition a row of numbers <code>A</code>&nbsp;into at most <code>K</code> adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?</p>

<p>Note that our partition must use every number in A, and that scores are not necessarily integers.</p>

<pre><strong>Example:</strong>
<strong>Input:</strong> 
A = [9,1,2,3,9]
K = 3
<strong>Output:</strong> 20
<strong>Explanation:</strong> 
The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned A into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
</pre>

<p>&nbsp;</p>

<p><strong>Note: </strong></p>

<ul>
	<li><code>1 &lt;= A.length &lt;= 100</code>.</li>
	<li><code>1 &lt;= A[i] &lt;= 10000</code>.</li>
	<li><code>1 &lt;= K &lt;= A.length</code>.</li>
	<li>Answers within <code>10^-6</code> of the correct answer will be accepted as correct.</li>
</ul>


**Companies**:  
[Bloomberg](https://leetcode.com/company/bloomberg)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 

```java
// OJ: https://leetcode.com/problems/largest-sum-of-averages/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2*k)
// Space: O(n*k)
class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        int l = A.length;
        double[][] dp = new double[K][l];
        int sum = 0;
        for (int i = 0; i < l; i++) {
            sum += A[i];
            dp[0][i] = 1.0 * sum / (i + 1);
        }
        for (int k = 1; k < K; k++) {
            for (int i = k; i < l; i++) {
                sum = 0;
                for (int j = i; j >= k; j--) {
                    sum += A[j];
                    dp[k][i] = Math.max(dp[k][i], dp[k - 1][j - 1] + 1.0 * sum / (i - j + 1));
                }
            }
        }
        return dp[K - 1][l - 1];
    }
}
```