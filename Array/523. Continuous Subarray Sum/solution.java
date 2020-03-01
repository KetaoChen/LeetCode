// OJ: https://leetcode.com/problems/continuous-subarray-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(k)
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // 1.just need to check
        // 2. at least two
        // if we have a prefixSum[i] % k == mod && curPrefixSum % k == mod && cur != i+1 nums[i-cur]
        // 3. 0 is the also a multiple of k.
		// 4. k can be negative numbers
		
        // [23,2,6,4,7], k=6;
        //  sum = 35
        //  set: 5,1,1,5
        if (k == 0) {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1] && nums[i] == 0) {
                    return true;
                }
            }
            return false;
        }
        k = Math.abs(k);
        int sum = 0;
        // keep the prev mod. 
        int prev = 0;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int num : nums) {
            sum += num;
            int mod = sum % k;
            if (set.contains(mod) && prev != mod) {
                return true;
            }
            prev = set.contains(mod) ? -1 : mod;
            set.add(mod);
        }
        return false;
    }
}