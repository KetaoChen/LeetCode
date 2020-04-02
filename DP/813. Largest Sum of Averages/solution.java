// OJ: https://leetcode.com/problems/largest-sum-of-averages/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2*k)
// Space: O(n*k)
class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        int l = A.length;
        double[][] dp = new double[K][l];
        int sum = 0;
        for (int i = 0; i < l; i++) {
            sum += A[i];
            dp[0][i] = 1.0 * sum / (i + 1);
        }
        for (int k = 1; k < K; k++) {
            for (int i = k; i < l; i++) {
                sum = 0;
                for (int j = i; j >= k; j--) {
                    sum += A[j];
                    dp[k][i] = Math.max(dp[k][i], dp[k - 1][j - 1] + 1.0 * sum / (i - j + 1));
                }
            }
        }
        return dp[K - 1][l - 1];
    }
}