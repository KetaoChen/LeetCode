// OJ: https://leetcode.com/problems/combination-sum-iv/
// Author: https://leetcode.com/charlesna/
// Time: O(target*n)
// Space: O(target)
class Solution {
    public int combinationSum4(int[] nums, int target) {
        //dp[i] is the number of ways to get i.        
        int[] dp = new int[target + 1];
        //initialize
        dp[0] = 1;
        //for each target;
        //we 
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i < num) {
                    break;
                }
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}