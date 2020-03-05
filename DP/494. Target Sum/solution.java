// OJ: https://leetcode.com/problems/target-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n*sum)
// Space: O(n*sum)
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (S > 1000) {
            return 0;
        }
        // dp[i][j] is for first ith numbers, the number of ways to have sum j
        int l = nums.length;
        int[][] dp = new int[l + 1][2001];
        // init: there is 1 way to have 0 for 0 numebers.
        dp[0][1000] = 1;
        // dp[i][j] = we can add this number or minus this number
        // dp[i][j] = dp[i - 1][j + num] + dp[i - 1][j - num];
        for (int i = 1; i <= l; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= 2000; j++) {
                if (j + num <= 2000) {
                    dp[i][j] += dp[i - 1][j + num];
                }
                if (j - num >= 0) {
                    dp[i][j ] += dp[i - 1][j - num];
                }
            }
        }
        return dp[l][1000 + S];
    }
}

// OJ: https://leetcode.com/problems/target-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n*sum)
// Space: O(n*sum)
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        Map<Integer, Integer>[] memo = new HashMap[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = new HashMap<>();
        }
        return helper(nums, 0, S, memo);
    }
    private int helper(int[] nums, int index, int S, Map<Integer, Integer>[] memo) {
        int res = 0;
        if (index == nums.length) {
            return S == 0 ? 1 : 0;
        }
        if (memo[index].containsKey(S)) {
            return memo[index].get(S);
        }
        res += helper(nums, index + 1, S - nums[index], memo) + 
            helper(nums, index + 1, S + nums[index], memo);
        memo[index].put(S, res);
        return res;
    }
}