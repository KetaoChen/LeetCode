// OJ: https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int minSwap(int[] A, int[] B) {
        // dp[i][0] is the min swap when we do not change the ith position
        // dp[i][1] is the min swap when we swap the elements at ith position
        int l = A.length;
        int[][] dp = new int[l][2];
        for (int[] d : dp) {
            Arrays.fill(d, l);
        }
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < l; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1);
            }
        }
        return Math.min(dp[l - 1][0], dp[l - 1][1]);
    }
}