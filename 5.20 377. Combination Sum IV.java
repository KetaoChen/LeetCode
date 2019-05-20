class Solution {
    public int combinationSum4(int[] nums, int target) {
        //write your code here
        //for each number, check the number of ways to fill with only with this number for each target and update.
        //dp[i] represent the number of ways to fill this target.
        //for each nums, f[i] += f[i - nums[i - 1]];
        
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]]; 
                }
            }
        }
        return dp[target];
    }
}