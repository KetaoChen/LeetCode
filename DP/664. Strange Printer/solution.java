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