// OJ: https://leetcode.com/problems/house-robber-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int rob(int[] nums) {
        // rob the first or not.
        // if rob the first, can not rob the last one.
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(helper(nums, 0, nums.length - 2), helper(nums, 1, nums.length - 1));
    }
    private int helper(int[] nums, int first, int last) {
        int rob = 0;
        int notRob = 0;
        for (int i = first; i <= last; i++) {
            int temp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(notRob, temp);
        }
        return Math.max(rob, notRob);
    }
}