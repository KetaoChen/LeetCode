// OJ: https://leetcode.com/problems/network-delay-time/
// Author: https://leetcode.com/charlesna/
// Time: O(V * E)
// Space: O(N*N)
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[K][K] = 0;
        for (int k = 0; k < N; k++) {
            for (int[] t : times) {
                if (dp[K][t[0]] != Integer.MAX_VALUE) {
                    dp[K][t[1]] = Math.min(dp[K][t[1]], dp[K][t[0]] + t[2]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, dp[K][i]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}