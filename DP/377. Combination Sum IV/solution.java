// OJ: https://leetcode.com/problems/combination-sum-iv/
// Author: https://leetcode.com/charlesna/
// Time: O(target*n)
// Space: O(target)
class Solution {
    public int combinationSum4(int[] nums, int target) {
        //dp[i] is the number of ways to get i.        
        int[] memo = new int[target + 1];
		//must do this
        Arrays.fill(memo, -1);
        //initialize
        memo[0] = 1;
        Arrays.sort(nums);
        return helper(nums, target, memo);
    }
    private int helper(int[] nums, int target, int[] memo) {
		if (target < 0 && target >= memo.length) {
			return 0;
		}
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int num : nums) {
			//must do this, or we need to check the index in the beginning of the method.
            if (num > target) {
                break;
            }
            res += helper(nums, target - num, memo);
        }
        memo[target] = res;
        return res;
    }
}

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