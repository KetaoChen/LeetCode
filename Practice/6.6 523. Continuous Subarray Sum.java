523. Continuous Subarray Sum
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        //to have a sum which is a multiple of target.
        //my intuition if to do module of all the number, and when record the prefixSum, after module
        if (nums.length <= 1) {
            return false;
        }
        if (k == 0) {
            return checkTwoZero(nums);
        }

        //since the length is at least two
        int sum = nums[0] % k;
        int prefix = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i < nums.length; i++) {
            set.add(prefix);
            sum = (sum + nums[i]) % k;
            if (sum == 0 || set.contains(sum)) {
                return true;
            }
            prefix = (prefix + nums[i - 1]) % k;
        }
        return false;
    }
    
    private boolean checkTwoZero(int[] nums) {
        for (int i = 0; i <= nums.length - 2; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) {
                return true;
            }
        }
        return false;
    }
}