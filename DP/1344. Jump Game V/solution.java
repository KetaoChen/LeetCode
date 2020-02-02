// OJ: https://leetcode.com/problems/jump-game-v/
// Author: https://leetcode.com/charlesna/
// Time: O(nd)
// Space: O(n)

class Solution {
    public int maxJumps(int[] arr, int d) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            helper(arr, d, dp, i);
        }
        int res = 1;
        for (int r : dp) {
            res = Math.max(res, r);
        }
        return res;
    }
    private int helper(int[] arr, int d, int[] dp, int index) {
        if (dp[index] != 0) {
            return dp[index];
        }
        int l = arr.length;
        int res = 1;
        for (int next = index + 1; next <= Math.min(l - 1, index + d); next++) {
            if (arr[next] >= arr[index]) {
                break;
            }
            res = Math.max(res, helper(arr, d, dp, next) + 1);
        }
        for (int next = index - 1; next >= Math.max(0, index - d); next--) {
            if (arr[next] >= arr[index]) {
                break;
            }
            res = Math.max(res, helper(arr, d, dp, next) + 1);
        }
        dp[index] = res;
        return res;
    }
}