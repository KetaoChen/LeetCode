// OJ: https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    // Most Important! when we add another number, check whether this number will increase the cost.
    public int numOfArrays(int N, int M, int K) {
        int mod = (int) 1e9 + 7;
        // devide into k parts, each part end with some number.
        if (K > N || K > M) return 0;
        // dp[n][k][m] the number of ways when current is the nth number, max until now is m, cost is k
        // dp[n][k][m] = dp[n - 1][k][m] + sum(dp[n - 1][k - 1][m]).
        long[][][] dp = new long[N + 1][K + 1][M + 1];        
        dp[0][0][0] = 1;
        for (int k = 1; k <= K; k++) {
            for (int n = k; n <= N; n++) {
                long prefix = dp[n - 1][k - 1][k - 1];
                for (int m = k; m <= M; m++) {
                    dp[n][k][m] = (dp[n - 1][k][m] * m + prefix) % mod;
                    prefix = (prefix + dp[n - 1][k - 1][m]) % mod;
                }
            }
        }
        long res = 0;
        for (int m = K; m <= M; m++) {
            // System.out.print(dp[N][K][m] + " ");
            res = (res + dp[N][K][m]) % mod;
        }
        return (int) res;
    }
}