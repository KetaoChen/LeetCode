// OJ: https://leetcode.com/problems/ones-and-zeroes/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n*k)
// Space: O(m*n)
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // for each string, we need to cost x 0s and y 1s, then we number of 0s left = m - x, 1s = n - y.
        // dp[i][j] to represent the maxForm with i 0s and j 1s left starting from the current string.
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            // count the number of 0s and 1s. 
            int c0 = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    c0++;
                }
            }
            //update the dp array. 
            for (int x = m; x >= c0; x--) {
                for (int y = n; y >= s.length() - c0; y--) {
                    dp[x][y] = Math.max(dp[x][y], dp[x - c0][y - (s.length() - c0)] + 1);
                }
            }
        }
        return dp[m][n];
    }
}