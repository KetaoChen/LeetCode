// OJ: https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        return helper(nums.length - 1, nums, 0, k, used, sum / k);
    }
    private boolean helper(int index, int[] nums, int sum, int part, boolean[] used, int target) {
        if (part == 0) {
            return true;
        }
        if (sum == target) {
            return helper(nums.length - 1, nums, 0, part - 1, used, target);
        }
        if (sum > target || index < 0) {
            return false;
        }
        boolean res = false;
        for (int i = index; i >= 0; i--) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            res = res || helper(i - 1, nums, sum + nums[i], part, used, target);
            used[i] = false;
        }
        return res;
    }
}