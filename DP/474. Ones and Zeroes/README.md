# [474. Ones and Zeroes (Medium)](https://leetcode.com/problems/ones-and-zeroes/)

<p>In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.</p>

<p>For now, suppose you are a dominator of <b>m</b> <code>0s</code> and <b>n</b> <code>1s</code> respectively. On the other hand, there is an array with strings consisting of only <code>0s</code> and <code>1s</code>.</p>

<p>Now your task is to find the maximum number of strings that you can form with given <b>m</b> <code>0s</code> and <b>n</b> <code>1s</code>. Each <code>0</code> and <code>1</code> can be used at most <b>once</b>.</p>

<p><b>Note:</b></p>

<ol>
	<li>The given numbers of <code>0s</code> and <code>1s</code> will both not exceed <code>100</code></li>
	<li>The size of given string array won't exceed <code>600</code>.</li>
</ol>

<p>&nbsp;</p>

<p><b>Example 1:</b></p>

<pre><b>Input:</b> Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
<b>Output:</b> 4

<b>Explanation:</b> This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are ¡°10,¡±0001¡±,¡±1¡±,¡±0¡±
</pre>

<p>&nbsp;</p>

<p><b>Example 2:</b></p>

<pre><b>Input:</b> Array = {"10", "0", "1"}, m = 1, n = 1
<b>Output:</b> 2

<b>Explanation:</b> You could form "10", but then you'd have nothing left. Better form "0" and "1".
</pre>

<p>&nbsp;</p>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Non-negative Integers without Consecutive Ones (Hard)](https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/)

## Solution 

```java
// OJ: https://leetcode.com/problems/ones-and-zeroes/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n*k)
// Space: O(m*n)
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // for each string, we need to cost x 0s and y 1s, then we number of 0s left = m - x, 1s = n - y.
        // dp[i][j] to represent the maxForm with i 0s and j 1s left starting from the current string.
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            // count the number of 0s and 1s. 
            int c0 = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    c0++;
                }
            }
            //update the dp array. 
            for (int x = m; x >= c0; x--) {
                for (int y = n; y >= s.length() - c0; y--) {
                    dp[x][y] = Math.max(dp[x][y], dp[x - c0][y - (s.length() - c0)] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
```