// OJ: https://leetcode.com/problems/valid-triangle-number/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(1)
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    res += right - left;
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        return res;
    }
}