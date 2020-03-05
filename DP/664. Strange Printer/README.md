# [664. Strange Printer (Hard)](https://leetcode.com/problems/strange-printer/)

<p>
There is a strange printer with the following two special requirements:

</p><ol>
<li>The printer can only print a sequence of the same character each time.</li>
<li>At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.</li>
</ol>

<p></p>

<p>
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.
</p>

<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> "aaabbb"
<b>Output:</b> 2
<b>Explanation:</b> Print "aaa" first and then print "bbb".
</pre>
<p></p>

<p><b>Example 2:</b><br>
</p><pre><b>Input:</b> "aba"
<b>Output:</b> 2
<b>Explanation:</b> Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
</pre>
<p></p>

<p><b>Hint</b>: Length of the given string will not exceed 100.</p>

**Companies**:  
[NetEase](https://leetcode.com/company/netease)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/), [Depth-first Search](https://leetcode.com/tag/depth-first-search/)

**Similar Questions**:
* [Remove Boxes (Hard)](https://leetcode.com/problems/remove-boxes/)

## Solution : 

```java
// OJ: https://leetcode.com/problems/strange-printer/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3)
// Space: O(n^2)
class Solution {
    public int strangePrinter(String s) {
        // dp[i][j] is the min times of print for substring s[i-j];
        int l = s.length();
        if (l == 0) {
            return 0;
        }
        int[][] dp = new int[l][l];
        for (int len = 1; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                if (len <= 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 1 : 2;
                    continue;
                }
	// how to print the new added one? print alone or print with the same letter shown before
                dp[i][j] = dp[i][j - 1] + 1;
                for (int k = i; k < j; k++) {
                    if (s.charAt(k) == s.charAt(j)) {
                        if (i == k) {
                            dp[i][j] = dp[i][j - 1];
                        }
                        else {
                            dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k][j]);
                        }
                    }
                }
            }
        }
        return dp[0][l - 1];
    }
}
```