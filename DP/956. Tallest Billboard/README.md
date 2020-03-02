# [956. Tallest Billboard (Hard)](https://leetcode.com/problems/tallest-billboard/)

<p>You are installing a billboard and want it to have the largest height.&nbsp; The billboard will have two steel supports, one on each side.&nbsp; Each steel support must be an equal height.</p>

<p>You have a collection of <code>rods</code> which can be welded together.&nbsp; For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.</p>

<p>Return the largest possible height of your billboard installation.&nbsp; If you cannot support the billboard, return 0.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">[1,2,3,6]</span>
<strong>Output: </strong><span id="example-output-1">6</span>
<strong>Explanation:</strong> We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong><span id="example-input-2-1">[1,2,3,4,5,6]</span>
<strong>Output: </strong><span id="example-output-2">10</span>
<strong>Explanation:</strong> We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
</pre>
</div>

<div>
<p><strong>Example 3:</strong></p>

<pre><strong>Input: </strong><span id="example-input-3-1">[1,2]</span>
<strong>Output: </strong><span id="example-output-3">0</span>
<strong>Explanation: </strong>The billboard cannot be supported, so we return 0.
</pre>
</div>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= rods.length &lt;= 20</code></li>
	<li><code>1 &lt;= rods[i] &lt;= 1000</code></li>
	<li><code>The sum of rods is at most 5000.</code></li>
</ol>


**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 1: 1D DP Using Property to Elimination

```java
// OJ: https://leetcode.com/problems/tallest-billboard/
// Author: https://leetcode.com/charlesna/
// Time: O(n*sum)
// Space: O(sum)
class Solution {
    public int tallestBillboard(int[] rods) {
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }
        int[] dp = new int[sum + 1];
		// make sure to initiate all the default value.
        Arrays.fill(dp, -1);
        dp[0] = 0;
        // dp[i] represents the max height when the diff between left and right is i.
        // for a new rod can add to the left or the right or not to add.
        for (int rod : rods) {
            int[] temp = dp.clone();
            for (int i = 0; i <= sum; i++) {
                if (temp[i] == -1) {
                    continue;
                }
                // if we add the rod to the longer one
                if (i + rod <= sum) {
                    dp[i + rod] = Math.max(dp[i + rod], temp[i] + rod);
                }
                // if we add to the shorter one
                // the short one is still shorter
                if (i - rod >= 0) {
                    dp[i - rod] = Math.max(dp[i - rod], temp[i]);
                }
                // the shorter one becomes longer
                else {
                    dp[rod - i] = Math.max(dp[rod - i], temp[i] - i + rod);
                }
            }
        }
        return dp[0];
    }
}
```

## Solution 2: 2D DP

```java
// OJ: https://leetcode.com/problems/tallest-billboard/
// Author: https://leetcode.com/charlesna/
// Time: O(n*sum*sum = 20*2500*2500 = 1.25*10^8) TLE
// Space: O(sum*sum)
class Solution {
    public int tallestBillboard2(int[] rods) {
        // each rod can be assign to three parts: left, right, or not use
        // if we put current rod into left part or right
        // dp[i][j] represents for the first i rods, we have i height on left and j height on right
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }
        int l = sum / 2;
        boolean[][] dp = new boolean[l + 1][l + 1];
        dp[0][0] = true;
        for (int rod : rods) {
            for (int i = l; i >= 0; i--) {
                for (int j = l; j >= 0; j--) {
                    if (i >= rod) {
                        dp[i][j] |= dp[i - rod][j];
                    }
                    if (j >= rod) {
                        dp[i][j] |= dp[i][j - rod];
                    }
                }
            }
        }
        for (int i = l; i >= 0; i--) {
            if (dp[i][i]) {
                return i;
            }
        }
        return -1;
    }
}
```