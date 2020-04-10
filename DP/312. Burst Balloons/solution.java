// OJ: https://leetcode.com/problems/burst-balloons/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int maxCoins(int[] nums) {
        // [*******]i[*****]
        int l = nums.length;
        // dp[i][j] is the max coins we can get when burst all the balloon in range[i:j]
        // dp[i][j] = dp[i][k-1] + dp[k + 1][j] + left*k*right; where k=[i:j]
        if (l == 0) return 0;
        int[][] dp = new int[l][l];
        for (int len = 1; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                int left = i == 0 ? 1 : nums[i - 1];
                int right = j == l - 1 ? 1 : nums[j + 1];
                if (len == 1) {
                    dp[i][j] = left * nums[i] * right;
                    continue;
                }
                for (int k = i; k <= j; k++) {
                    if (k == i) dp[i][j] = Math.max(dp[i][j], left * nums[i] * right + dp[k + 1][j]);
                    else if (k == j) dp[i][j] = Math.max(dp[i][j], left * nums[j] * right + dp[i][k - 1]);
                    else dp[i][j] = Math.max(dp[i][j], left * nums[k] * right + dp[i][k - 1] + dp[k + 1][j]);
                }
            }
        }
        return dp[0][l - 1];
    }
}