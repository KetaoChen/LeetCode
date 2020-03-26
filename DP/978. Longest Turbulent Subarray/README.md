# [978. Longest Turbulent Subarray (Medium)](https://leetcode.com/problems/longest-turbulent-subarray/)

<p>A subarray <code>A[i], A[i+1], ..., A[j]</code>&nbsp;of <code>A</code> is said to be <em>turbulent</em> if and only if:</p>

<ul>
	<li>For <code>i &lt;= k &lt; j</code>, <code>A[k] &gt; A[k+1]</code> when <code>k</code> is odd, and <code>A[k] &lt; A[k+1]</code> when <code>k</code> is even;</li>
	<li><strong>OR</strong>, for <code>i &lt;= k &lt; j</code>, <code>A[k] &gt; A[k+1]</code> when <code>k</code> is even, and <code>A[k] &lt; A[k+1]</code> when <code>k</code> is odd.</li>
</ul>

<p>That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.</p>

<p>Return the <strong>length</strong> of a&nbsp;maximum size turbulent subarray of A.</p>

<p>&nbsp;</p>

<div>
<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">[9,4,2,10,7,8,8,1,9]</span>
<strong>Output: </strong><span id="example-output-1">5</span>
<strong>Explanation: </strong>(A[1] &gt; A[2] &lt; A[3] &gt; A[4] &lt; A[5])
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong><span id="example-input-2-1">[4,8,12,16]</span>
<strong>Output: </strong><span id="example-output-2">2</span>
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre><strong>Input: </strong><span id="example-input-3-1">[100]</span>
<strong>Output: </strong><span id="example-output-3">1</span>
</pre>
</div>
</div>
</div>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 40000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 10^9</code></li>
</ol>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/), [Sliding Window](https://leetcode.com/tag/sliding-window/)

**Similar Questions**:
* [Maximum Subarray (Easy)](https://leetcode.com/problems/maximum-subarray/)

## Solution 

```java
// OJ: https://leetcode.com/problems/longest-turbulent-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int maxTurbulenceSize(int[] A) {
        int l = A.length;
        int[][] dp = new int[l][2];
        // dp[i][0] is inc at i, dp[i][1] is dec at i
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < l; i++) {
            if (A[i] > A[i - 1]) {
                dp[i][0] = dp[i - 1][1] + 1;
                dp[i][1] = 1;
            }
            else if (A[i] < A[i - 1]) {
                dp[i][1] = dp[i - 1][0] + 1;
                dp[i][0] = 1;
            }
            else {
                dp[i][0] = 1;
                dp[i][1] = 1;
            }
        }
        int res = 0;
        for (int i = 0; i < l; i++) {
            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }
        return res;
    }
}
```