# [583. Delete Operation for Two Strings (Medium)](https://leetcode.com/problems/delete-operation-for-two-strings/)

<p>
Given two words <i>word1</i> and <i>word2</i>, find the minimum number of steps required to make <i>word1</i> and <i>word2</i> the same, where in each step you can delete one character in either string.
</p>

<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> "sea", "eat"
<b>Output:</b> 2
<b>Explanation:</b> You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
</pre>
<p></p>

<p><b>Note:</b><br>
</p><ol>
<li>The length of given words won't exceed 500.</li>
<li>Characters in given words can only be lower-case letters.</li>
</ol>
<p></p>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Google](https://leetcode.com/company/google)

**Related Topics**:  
[String](https://leetcode.com/tag/string/)

**Similar Questions**:
* [Edit Distance (Hard)](https://leetcode.com/problems/edit-distance/)
* [Minimum ASCII Delete Sum for Two Strings (Medium)](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)
* [Longest Common Subsequence (Medium)](https://leetcode.com/problems/longest-common-subsequence/)

## Solution 

```java
// OJ: https://leetcode.com/problems/delete-operation-for-two-strings/
// Author: https://leetcode.com/charlesna/
// Time: O(n*m)
// Space: O(n*m)
class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j] means the minimum number of delete to make w1, w2 the same.
        // if w1[i] == w2[j], dp[i][j] = dp[i - 1][j - 1]
        // else dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + 1, delete w1[i] or w2[j]
        int l1 = word1.length(), l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (i == 0) {
                    dp[0][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][0] = i;
                    continue;
                }
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                dp[i][j] = Math.min(dp[i][j], Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
            }
        }
        return dp[l1][l2];
    }
}

// OJ: https://leetcode.com/problems/delete-operation-for-two-strings/
// Author: https://leetcode.com/charlesna/
// Time: O(n*m)
// Space: O(2*m)
class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j] means the minimum number of delete to make w1, w2 the same.
        // if w1[i] == w2[j], dp[i][j] = dp[i - 1][j - 1]
        // else dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + 1, delete w1[i] or w2[j]
        int l1 = word1.length(), l2 = word2.length();
        int[][] dp = new int[2][l2 + 1];
        int now = 1, old = 0;
        for (int j = 0; j <= l2; j++) {
            dp[now][j] = j;
        }
        for (int i = 1; i <= l1; i++) {
            old = now;
            now = 1 - now;
            dp[now][0] = i;
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[now][j] = dp[old][j - 1];
                }
                else {
                    dp[now][j] = Math.min(dp[old][j], dp[now][j - 1]) + 1;
                }
            }
        }
        return dp[now][l2];
    }
}
```