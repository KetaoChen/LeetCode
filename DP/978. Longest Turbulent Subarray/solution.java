// OJ: https://leetcode.com/problems/longest-turbulent-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int maxTurbulenceSize(int[] A) {
        int l = A.length;
        int[][] dp = new int[l][2];
        // dp[i][0] is inc at i, dp[i][1] is dec at i
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < l; i++) {
            if (A[i] > A[i - 1]) {
                dp[i][0] = dp[i - 1][1] + 1;
                dp[i][1] = 1;
            }
            else if (A[i] < A[i - 1]) {
                dp[i][1] = dp[i - 1][0] + 1;
                dp[i][0] = 1;
            }
            else {
                dp[i][0] = 1;
                dp[i][1] = 1;
            }
        }
        int res = 0;
        for (int i = 0; i < l; i++) {
            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }
        return res;
    }
}