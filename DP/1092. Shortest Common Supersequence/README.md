# [1092. Shortest Common Supersequence  (Hard)](https://leetcode.com/problems/shortest-common-supersequence/)

<p>Given two strings <code>str1</code> and <code>str2</code>,&nbsp;return the shortest string that has both <code>str1</code>&nbsp;and <code>str2</code>&nbsp;as subsequences.&nbsp;&nbsp;If multiple answers exist, you may return any of them.</p>

<p><em>(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen <u>anywhere</u> from T) results in the string S.)</em></p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>str1 = <span id="example-input-1-1">"abac"</span>, str2 = <span id="example-input-1-2">"cab"</span>
<strong>Output: </strong><span id="example-output-1">"cabac"</span>
<strong>Explanation: </strong>
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= str1.length, str2.length &lt;= 1000</code></li>
	<li><code>str1</code> and <code>str2</code> consist of lowercase English letters.</li>
</ol>


**Companies**:  
[Microsoft](https://leetcode.com/company/microsoft)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Longest Common Subsequence (Medium)](https://leetcode.com/problems/longest-common-subsequence/)

## Solution 

```java
// OJ: https://leetcode.com/problems/shortest-common-supersequence/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        // 1. abac, cab  ==> LCS = ab. ==> l1 + l2 - LCS
        int l1 = str1.length(), l2 = str2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }        
        // print out the reversed LCS
        StringBuilder lcs = new StringBuilder();
        int i = l1, j = l2;
        while (i > 0 && j > 0) {
            char c1 = str1.charAt(i - 1), c2 = str2.charAt(j - 1);
            if (c1 == c2) {
                lcs.append(c1);
                i--;
                j--;
            }
            else {
                if (dp[i - 1][j] > dp[i][j - 1]) i--;
                else j--;
            }
        }
        //System.out.println(lcs.reverse().toString());
        lcs.reverse();
        StringBuilder sb = new StringBuilder();
        i = 0;
        j = 0;
        int index = 0;
        while (index < lcs.length()) {
            while (str1.charAt(i) != lcs.charAt(index)) {
                sb.append(str1.charAt(i++));
            }
            while (str2.charAt(j) != lcs.charAt(index)) {
                sb.append(str2.charAt(j++));
            }
            sb.append(lcs.charAt(index++));
            i++;
            j++;
        }
        while (i < l1) {
            sb.append(str1.charAt(i++));
        }
        while (j < l2) {
            sb.append(str2.charAt(j++));
        }
        return sb.toString();
    }
}
```