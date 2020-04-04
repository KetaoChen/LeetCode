# [903. Valid Permutations for DI Sequence (Hard)](https://leetcode.com/problems/valid-permutations-for-di-sequence/)

<p>We are given <code>S</code>, a length <code>n</code> string of characters from the set <code>{'D', 'I'}</code>. (These letters stand for "decreasing" and "increasing".)</p>

<p>A&nbsp;<em>valid permutation</em>&nbsp;is a permutation <code>P[0], P[1], ..., P[n]</code> of integers&nbsp;<code>{0, 1, ..., n}</code>, such that for all <code>i</code>:</p>

<ul>
	<li>If <code>S[i] == 'D'</code>, then <code>P[i] &gt; P[i+1]</code>, and;</li>
	<li>If <code>S[i] == 'I'</code>, then <code>P[i] &lt; P[i+1]</code>.</li>
</ul>

<p>How many valid permutations are there?&nbsp; Since the answer may be large, <strong>return your answer modulo <code>10^9 + 7</code></strong>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">"DID"</span>
<strong>Output: </strong><span id="example-output-1">5</span>
<strong>Explanation: </strong>
The 5 valid permutations of (0, 1, 2, 3) are:
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 200</code></li>
	<li><code>S</code> consists only of characters from the set <code>{'D', 'I'}</code>.</li>
</ol>

<div>
<p>&nbsp;</p>
</div>


**Related Topics**:  
[Divide and Conquer](https://leetcode.com/tag/divide-and-conquer/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 1: DP Change the partial value of permutation (PrefixSum)

```java
// OJ: https://leetcode.com/problems/valid-permutations-for-di-sequence/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int numPermsDISequence(String S) {
        int l = S.length(), mod = (int) (1e9 + 7);
        if (l == 0) return 0;
        // dp[i][j] is the number of permutation for first ith number, ending with j.
        long[][] dp = new long[l + 1][l + 1];
        // initialize, the first number 
        dp[0][0] = 1;
        // dp[i][j] = c == 'D' ? sum(dp[i-1][j:i-1]) : sum(dp[i-1][0:j-1]);
        for (int i = 1; i <= l; i++) {
            char c = S.charAt(i - 1);
            long sum = 0;
            if (c == 'D') {
                for (int j = i - 1; j >= 0; j--) {
                    sum = (sum + dp[i - 1][j]) % mod;
                    dp[i][j] = sum;
                }
            }
            else {
                for (int j = 1; j <= i; j++) {
                    sum = (sum + dp[i - 1][j - 1]) % mod;
                    dp[i][j] = sum;
                }
            }
        }
        long res = 0;
        for (int j = 0; j <= l; j++) {
            res = (res + dp[l][j]) % mod;
        }
        return (int) res;
    }
}
```

## Solution 2: Enumerate the place to put the max value

```java
// OJ: https://leetcode.com/problems/valid-permutations-for-di-sequence/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3)
// Space: O(n^2)
class Solution {
    int mod = (int) (1e9 + 7);
    long[][] f;
    Map<String, Integer> map = new HashMap<>();
    private void getCombo() {
        f = new long[201][201];
        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    f[i][j] = 1;
                    continue;
                }
                f[i][j] = (f[i - 1][j] + f[i - 1][j - 1]) % mod;
            }
        } 
    }
    public int numPermsDISequence(String S) {
        int l = S.length();
        if (l <= 1) return 1;
        if (map.containsKey(S)) return map.get(S);
        if (f == null) getCombo();
        long res = S.charAt(0) == 'D' ? numPermsDISequence(S.substring(1)) : 0;
        for (int i = 0; i < l - 1; i++) {
            if (S.charAt(i) == 'I' && S.charAt(i + 1) == 'D') {
                res = (res + (f[l][i + 1] * (numPermsDISequence(S.substring(0, i))) % mod * numPermsDISequence(S.substring(i + 2)))) % mod;
            }
        }
        if (S.charAt(l - 1) == 'I') res = (res + numPermsDISequence(S.substring(0, l - 1))) % mod;
        res = (res + mod) % mod;
        map.put(S, (int) res);
        return (int)res;
    }
}
```