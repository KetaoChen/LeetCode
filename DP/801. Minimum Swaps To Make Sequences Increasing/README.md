# [801. Minimum Swaps To Make Sequences Increasing (Medium)](https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/)

<p>We have two integer sequences <code>A</code> and <code>B</code> of the same non-zero length.</p>

<p>We are allowed to swap elements <code>A[i]</code> and <code>B[i]</code>.&nbsp; Note that both elements are in the same index position in their respective sequences.</p>

<p>At the end of some number of swaps, <code>A</code> and <code>B</code> are both strictly increasing.&nbsp; (A sequence is <em>strictly increasing</em> if and only if <code>A[0] &lt; A[1] &lt; A[2] &lt; ... &lt; A[A.length - 1]</code>.)</p>

<p>Given A and B, return the minimum number of swaps to make both sequences strictly increasing.&nbsp; It is guaranteed that the given input always makes it possible.</p>

<pre><strong>Example:</strong>
<strong>Input:</strong> A = [1,3,5,4], B = [1,2,3,7]
<strong>Output:</strong> 1
<strong>Explanation: </strong>
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>A, B</code> are arrays with the same length, and that length will be in the range <code>[1, 1000]</code>.</li>
	<li><code>A[i], B[i]</code> are integer values in the range <code>[0, 2000]</code>.</li>
</ul>


**Companies**:  
[Google](https://leetcode.com/company/google), [Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 

```java
// OJ: https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int minSwap(int[] A, int[] B) {
        // dp[i][0] is the min swap when we do not change the ith position
        // dp[i][1] is the min swap when we swap the elements at ith position
        int l = A.length;
        int[][] dp = new int[l][2];
        for (int[] d : dp) {
            Arrays.fill(d, l);
        }
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < l; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1);
            }
        }
        return Math.min(dp[l - 1][0], dp[l - 1][1]);
    }
}
```