# [1278. Palindrome Partitioning III (Hard)](https://leetcode.com/problems/palindrome-partitioning-iii/)

<p>You are given a string&nbsp;<code>s</code> containing lowercase letters and an integer <code>k</code>. You need to :</p>

<ul>
	<li>First, change some characters of <code>s</code>&nbsp;to other lowercase English letters.</li>
	<li>Then divide <code>s</code>&nbsp;into <code>k</code> non-empty disjoint substrings such that each substring is palindrome.</li>
</ul>

<p>Return the minimal number of characters that you need to change&nbsp;to divide the string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> s = "abc", k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong>&nbsp;You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> s = "aabbc", k = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;You can split the string into "aa", "bb" and "c", all of them are palindrome.</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> s = "leetcode", k = 8
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 100</code>.</li>
	<li><code>s</code>&nbsp;only contains lowercase English letters.</li>
</ul>

**Companies**:  
[Uber](https://leetcode.com/company/uber), [Apple](https://leetcode.com/company/apple)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 

```java
// OJ: https://leetcode.com/problems/palindrome-partitioning-iii/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2*k)
// Space: O(n^2)
class Solution {
    public int palindromePartition(String s, int k) {
        // dp[i][k] means the min change to make s[0-i] split into k palin
        // base case i == k dp[i][k] = 1;
        // dp[i][k] = loop the last palin and find the min: min(dp[m][k-1] + change[m+1][i])
        int l = s.length();
        int[][] dp = new int[l][k + 1];
        int[][] change = new int[l][l];
        for (int len = 1; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                if (len <= 2) {
                    change[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                    continue;
                }
                change[i][j] = s.charAt(i) == s.charAt(j) ? change[i + 1][j - 1] : change[i + 1][j - 1] + 1;
            }
        }
        for (int m = 1; m <= k; m++) {
            for (int i = m - 1; i < l; i++) {
                dp[i][m] = i;
                if (m == 1) {
                    dp[i][m] = change[0][i];
                    continue;
                }
                for (int j = m - 2; j < i ; j++) {
                    dp[i][m] = Math.min(dp[i][m], dp[j][m - 1] + change[j + 1][i]);
                }
            }
        }
        return dp[l - 1][k];
    }
}

// OJ: https://leetcode.com/problems/palindrome-partitioning-iii/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3*k)
// Space: O(n^2*k)
class Solution {
    public int palindromePartition(String s, int k) {
        // dp[i][j][k] means the min change to make s[i-j] split into k palin
        // base case j-i+1 == k dp[i][j][k] = 1;
        // dp[i][j][k] = loop the last palin find the min: min(dp[i][m][k-1] + dp[m+1][j][1])
        int l = s.length();
        int[][][] dp = new int[l][l][k + 1];
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                for (int m = 1; m <= k; m++) {
                    dp[i][j][m] = l;
                }
            }
        }
        for (int len = 1; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                for (int m = 1; m <= Math.min(len, k); m++) {
                    if (len == m) {
                        dp[i][j][m] = 0;
                        continue;
                    }
                    if (m == 1) {
                        dp[i][j][m] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1][m] : dp[i + 1][j - 1][m] + 1;
                        continue;
                    }
                    for (int n = i + m - 2; n < j; n++) {
                        dp[i][j][m] = Math.min(dp[i][j][m], dp[i][n][m - 1] + dp[n + 1][j][1]);
                    }
                }
            }
        }

        return dp[0][l-1][k];
    }
}
```