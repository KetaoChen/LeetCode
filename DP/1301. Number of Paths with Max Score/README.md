# [1301. Number of Paths with Max Score (Hard)](https://leetcode.com/problems/number-of-paths-with-max-score/)

<p>You are given a square <code>board</code>&nbsp;of characters. You can move on the board starting at the bottom right square marked with the character&nbsp;<code>'S'</code>.</p>

<p>You need&nbsp;to reach the top left square marked with the character <code>'E'</code>. The rest of the squares are labeled either with a numeric character&nbsp;<code>1, 2, ..., 9</code> or with an obstacle <code>'X'</code>. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.</p>

<p>Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, <strong>taken modulo <code>10^9 + 7</code></strong>.</p>

<p>In case there is no path, return&nbsp;<code>[0, 0]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> board = ["E23","2X2","12S"]
<strong>Output:</strong> [7,1]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> board = ["E12","1X1","21S"]
<strong>Output:</strong> [4,2]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> board = ["E11","XXX","11S"]
<strong>Output:</strong> [0,0]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= board.length == board[i].length &lt;= 100</code></li>
</ul>

**Companies**:  
[Samsung](https://leetcode.com/company/samsung)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 

```java
// OJ: https://leetcode.com/problems/number-of-paths-with-max-score/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int l = board.size();
        long[][][] dp = new long[l][l][2];
        int mod = 1000000007;
        //dp[i][j][k] represent the number of ways to get i, j.
        //dp[i][j][k] = sum(dp[i - 1][j][] + dp[i][j - 1][k -cur]) + dp[i - 1][j - 1][k - cur]
        dp[l - 1][l - 1][1] = 1;
        //for the last row
        for (int j = l - 2; j >= 0; j--) {
            if (board.get(l - 1).charAt(j) == 'X') {
                break;
            }
            dp[l - 1][j][0] = board.get(l - 1).charAt(j) - '0' + dp[l - 1][j + 1][0];
            dp[l - 1][j][1] = 1;
        }
        //last column
        for (int i = l - 2; i >= 0; i--) {
            if (board.get(i).charAt(l - 1) == 'X') {
                break;
            }
            dp[i][l - 1][0] = board.get(i).charAt(l - 1) - '0' + dp[i + 1][l - 1][0];
            dp[i][l - 1][1] = 1;
        }
        int[] dx = {0, -1, -1};
        int[] dy = {-1, 0, -1};
        for (int i = l - 2; i >= 0; i--) {
            String s = board.get(i);
            for (int j = l - 2; j >= 0; j--) {
                if (s.charAt(j) == 'X') {
                    dp[i][j][1] = 0;
                    continue;
                }
                for (int d = 0; d < 3; d++) {
                    if (dp[i - dx[d]][j - dy[d]][1] == 0) {
                        continue;
                    }
                    long sum = s.charAt(j) == 'E' ? dp[i - dx[d]][j - dy[d]][0] : dp[i - dx[d]][j - dy[d]][0] + s.charAt(j) - '0';
                    if (dp[i][j][0] == sum) {
                        dp[i][j][1] = (dp[i][j][1] + dp[i - dx[d]][j - dy[d]][1]) % mod;
                    }
                    else if (dp[i][j][0] < sum) {
                        dp[i][j][0] = sum;
                        dp[i][j][1] = dp[i - dx[d]][j - dy[d]][1];
                    }
                }
            }
        }
        return new int[]{(int)dp[0][0][0], (int)dp[0][0][1]};
    }
}

```