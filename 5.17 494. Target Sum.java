class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        //for the way when it is pos + the way when it is neg
        //start from the ith int, with a target S
        //the subproblem is from the i+1th int, with target(S - nums[i]) + target == (S + nums[i])
        int l = nums.length;
        if (l == 0) {
            if (S == 0) {
                return 1;
            }
            return 0;
        }
        if (S > 2000) {
            return 0;
        }
        HashMap<Integer, Integer>[] memo = new HashMap[l];
        for (int i = 0; i < l; i++) {
            memo[i] = new HashMap<Integer, Integer>();
        }
        return ways(nums, S, 0, memo);
    }
    
    public int ways(int[]nums, int S, int index, HashMap[] memo) {
        if (index == nums.length) {
            if (S == 0) {
                return 1;
            }
            return 0;
        }
        
        if (memo[index].containsKey(S)) {
            return (int) memo[index].get(S);
        }
        memo[index].put(S, ways(nums, S + nums[index], index + 1, memo) + ways(nums, S - nums[index], index + 1, memo));
        return (int) memo[index].get(S);
    }
}