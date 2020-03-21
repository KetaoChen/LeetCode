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