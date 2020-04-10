# [312. Burst Balloons (Hard)](https://leetcode.com/problems/burst-balloons/)

<p>Given <code>n</code> balloons, indexed from <code>0</code> to <code>n-1</code>. Each balloon is painted with a number on it represented by array <code>nums</code>. You are asked to burst all the balloons. If the you burst balloon <code>i</code> you will get <code>nums[left] * nums[i] * nums[right]</code> coins. Here <code>left</code> and <code>right</code> are adjacent indices of <code>i</code>. After the burst, the <code>left</code> and <code>right</code> then becomes adjacent.</p>

<p>Find the maximum coins you can collect by bursting the balloons wisely.</p>

<p><b>Note:</b></p>

<ul>
	<li>You may imagine <code>nums[-1] = nums[n] = 1</code>. They are not real therefore you can not burst them.</li>
	<li>0 ¡Ü <code>n</code> ¡Ü 500, 0 ¡Ü <code>nums[i]</code> ¡Ü 100</li>
</ul>

<p><b>Example:</b></p>

<pre><b>Input:</b> <code>[3,1,5,8]</code>
<b>Output:</b> <code>167 
<strong>Explanation: </strong></code>nums = [3,1,5,8] --&gt; [3,5,8] --&gt;   [3,8]   --&gt;  [8]  --&gt; []
&nbsp;            coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
</pre>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Facebook](https://leetcode.com/company/facebook)

**Related Topics**:  
[Divide and Conquer](https://leetcode.com/tag/divide-and-conquer/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Minimum Cost to Merge Stones (Hard)](https://leetcode.com/problems/minimum-cost-to-merge-stones/)

## Solution 

```java
// OJ: https://leetcode.com/problems/burst-balloons/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int maxCoins(int[] nums) {
        // [*******]i[*****]
        int l = nums.length;
        // dp[i][j] is the max coins we can get when burst all the balloon in range[i:j]
        // dp[i][j] = dp[i][k-1] + dp[k + 1][j] + left*k*right; where k=[i:j]
        if (l == 0) return 0;
        int[][] dp = new int[l][l];
        for (int len = 1; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                int left = i == 0 ? 1 : nums[i - 1];
                int right = j == l - 1 ? 1 : nums[j + 1];
                if (len == 1) {
                    dp[i][j] = left * nums[i] * right;
                    continue;
                }
                for (int k = i; k <= j; k++) {
                    if (k == i) dp[i][j] = Math.max(dp[i][j], left * nums[i] * right + dp[k + 1][j]);
                    else if (k == j) dp[i][j] = Math.max(dp[i][j], left * nums[j] * right + dp[i][k - 1]);
                    else dp[i][j] = Math.max(dp[i][j], left * nums[k] * right + dp[i][k - 1] + dp[k + 1][j]);
                }
            }
        }
        return dp[0][l - 1];
    }
}
```