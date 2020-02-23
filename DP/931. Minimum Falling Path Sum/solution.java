// OJ: https://leetcode.com/problems/minimum-falling-path-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int minFallingPathSum(int[][] A) {
        //bellman-ford.
        //shortest path from top to bottom.
        int l = A.length;
        int[][] dis = new int[l][l];
        for (int[] d : dis) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int j = 0; j < l; j++) {
            dis[0][j] = A[0][j];
        }
        for (int i = 0; i < l - 1; i++) {
            for (int j = 0; j < l; j++) {
                for (int k = Math.max(0, j - 1); k < Math.min(l, j + 2); k++) {
                    dis[i + 1][k] = Math.min(dis[i + 1][k], dis[i][j] + A[i + 1][k]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < l; j++) {
            res = Math.min(res, dis[l - 1][j]);
        }
        return res;
    }
}

// OJ: https://leetcode.com/problems/minimum-falling-path-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int minFallingPathSum(int[][] arr) {
        int l = arr.length;
        int[][] dp = new int[l + 1][l];
        for (int i = 1; i <= l; i++) {
            for (int j = 0; j < l; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = Math.max(0, j - 1); k < Math.min(l, j + 2); k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + arr[i - 1][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < l; i++) {
            res = Math.min(res, dp[l][i]);
        }
        return res;
    }
}