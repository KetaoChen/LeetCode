// OJ: https://leetcode.com/problems/student-attendance-record-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int checkRecord(int n) {
        int mod = (int) (1e9 + 7);
        long[][][] dp = new long[2][4][2];
        int now = 1, old = 0;
        dp[now][0][0] = 1;
        // dp[i] is at ith pos, the total number of possibilities.
        // dp[i][0] cur state = present.
        // dp[i][1] cur state = 1 late
        // dp[i][2] cur state = 2 lates
        // dp[i][3] cur state = absent
        // dp[i][j][0] no absent.
        // dp[i][j][1] 1 absent.
        for (int i = 1; i <= n; i++) {
            now = old;
            old = 1 - old;
            dp[now][0][0] = (dp[old][0][0] + dp[old][1][0] + dp[old][2][0]) % mod;
            dp[now][1][0] = dp[old][0][0];
            dp[now][2][0] = dp[old][1][0];
            dp[now][3][1] = dp[now][0][0];
            dp[now][0][1] = (dp[old][0][1] + dp[old][1][1] + dp[old][2][1] + dp[old][3][1]) % mod;
            dp[now][1][1] = (dp[old][0][1] + dp[old][3][1]) % mod;
            dp[now][2][1] = dp[old][1][1];
        }
        return (int) ((dp[now][0][0] + dp[now][1][0] + dp[now][2][0] + dp[now][3][1] + dp[now][0][1] + dp[now][1][1] + dp[now][2][1]) % mod);
    }
}