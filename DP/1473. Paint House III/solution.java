// OJ: https://leetcode.com/problems/paint-house-iii/
// Author: https://leetcode.com/charlesna/
// Time: O(m*t*n*n)
// Space: O(m*t*n)
class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // first ith houses, with t neighors, end with color j. the min cost.
        int[][][] dp = new int[m + 1][n + 1][target + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int t = 0; t <= target; t++) {
                    dp[i][j][t] = Integer.MAX_VALUE;
                }
            }
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j][0] = 0;
        }
        for (int t = 1; t <= target; t++) {
            for (int i = t; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // has painted
                    if (houses[i - 1] != 0 && houses[i - 1] != j) {
                        continue;
                    }
                    int c = houses[i - 1] != 0 ? 0 : cost[i - 1][j - 1];
                    // color of prev house
                    for (int k = 1; k <= n; k++) {
                        if (dp[i - 1][k][t] != Integer.MAX_VALUE && k == j) {
                            dp[i][j][t] = Math.min(dp[i][j][t], dp[i - 1][k][t] + c);
                        }
                        else if (dp[i - 1][k][t - 1] != Integer.MAX_VALUE && k != j) {
                            dp[i][j][t] = Math.min(dp[i][j][t], dp[i - 1][k][t - 1] + c);
                        }
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++) {
            res = Math.min(res, dp[m][j][target]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;    
    }
}