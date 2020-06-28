// OJ: https://leetcode.com/problems/parallel-courses-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(3^n)
// Space: O(2^n)
class Solution {
    public int minNumberOfSemesters(int n, int[][] dependencies, int K) {
        // use bitmask to represent the preClass for each class
        int[] preClass = new int[n];
        for (int[] d : dependencies) {
            preClass[d[1] - 1] |= (1 << (d[0] - 1));
        }
        // compute the preClassSet for each state.
        int[] preClassSet = new int[1 << n];
        for (int state = 1; state < 1 << n; state++) {
            for (int k = 0; k < n; k++) {
                if ((state >> k & 1) == 1) {
                    preClassSet[state] |= preClass[k];
                }
            }
        }
        // dp
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // loop all the state
        for (int state = 1; state < 1 << n; state++) {
            if (Integer.bitCount(state) <= K && preClassSet[state] == 0) {
                dp[state] = 1;
            }
            // loop its subset
            // when the subset meet the preClassSet and take <= K classes
            for (int subset = state; subset > 0; subset = (subset - 1) & state) {
                if (dp[subset] != Integer.MAX_VALUE && (preClassSet[state] & subset) == preClassSet[state] 
                   && Integer.bitCount(state) - Integer.bitCount(subset) <= K) {
                    dp[state] = Math.min(dp[state], dp[subset] + 1);
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}