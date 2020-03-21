# [10. Regular Expression Matching (Hard)](https://leetcode.com/problems/regular-expression-matching/)

<p>Given an input string (<code>s</code>) and a pattern (<code>p</code>), implement regular expression matching with support for <code>'.'</code> and <code>'*'</code>.</p>

<pre>'.' Matches any single character.
'*' Matches zero or more of the preceding element.
</pre>

<p>The matching should cover the <strong>entire</strong> input string (not partial).</p>

<p><strong>Note:</strong></p>

<ul>
	<li><code>s</code>&nbsp;could be empty and contains only lowercase letters <code>a-z</code>.</li>
	<li><code>p</code> could be empty and contains only lowercase letters <code>a-z</code>, and characters like&nbsp;<code>.</code>&nbsp;or&nbsp;<code>*</code>.</li>
</ul>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong>
s = "aa"
p = "a"
<strong>Output:</strong> false
<strong>Explanation:</strong> "a" does not match the entire string "aa".
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong>
s = "aa"
p = "a*"
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;'*' means zero or more of the preceding&nbsp;element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong>
s = "ab"
p = ".*"
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;".*" means "zero or more (*) of any character (.)".
</pre>

<p><strong>Example 4:</strong></p>

<pre><strong>Input:</strong>
s = "aab"
p = "c*a*b"
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
</pre>

<p><strong>Example 5:</strong></p>

<pre><strong>Input:</strong>
s = "mississippi"
p = "mis*is*p*."
<strong>Output:</strong> false
</pre>


**Companies**:  
[Facebook](https://leetcode.com/company/facebook), [Microsoft](https://leetcode.com/company/microsoft), [Amazon](https://leetcode.com/company/amazon), [Google](https://leetcode.com/company/google), [Lyft](https://leetcode.com/company/lyft), [Adobe](https://leetcode.com/company/adobe), [Coursera](https://leetcode.com/company/coursera), [Twitter](https://leetcode.com/company/twitter), [Oracle](https://leetcode.com/company/oracle)

**Related Topics**:  
[String](https://leetcode.com/tag/string/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/), [Backtracking](https://leetcode.com/tag/backtracking/)

**Similar Questions**:
* [Wildcard Matching (Hard)](https://leetcode.com/problems/wildcard-matching/)

## Solution 

```java
// OJ: https://leetcode.com/problems/regular-expression-matching/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public boolean isMatch(String s, String p) {
        int ls = s.length(), lp = p.length();
        boolean[][] dp = new boolean[ls + 1][lp + 1];
        // dp[i][j] is whether s[0-i] matches p[0-j].
        // init. when p = 0, i is empty or x*x*x* can match
        dp[0][0] = true;
        for (int j = 2; j <= lp; j += 2) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        }
        // dp[i][j] = c == '*'
        // s = 'xxxxxxxxa'
        // p = 'xxxxxxxxa*'
        // dp[i][j] == dp[i-1][j-2] || dp[i-1][j-1]
        for (int i = 1; i <= ls; i++) {
            for (int j = 1; j <= lp; j++) {
                char ci = s.charAt(i - 1);
                char cj = p.charAt(j - 1);
                if (ci == cj || cj == '.') {
                    dp[i][j] |= dp[i - 1][j - 1];
                }
                else if (cj == '*') {
                    if (j >= 2 && (p.charAt(j - 2) == ci || p.charAt(j - 2) == '.')) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                    dp[i][j] |= dp[i][j - 2];
                }
            }
        }
        return dp[ls][lp];
    }
}
```