// OJ: https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int maxValueAfterReverse(int[] nums) {
        //******a[b****c]d
        //delta = |a-c|+|b-d| - |a-b|-|c-d| we want to maximize this.
        //this can regarded as four distance on a line.
        //if (ab and cd have intersection, delta is small or equal to 0)
        //so we only care about the situation that ab and cd has no interseciton and the delta=abs(min(c,d) - max(a,b)).
        int res = 0, l = nums.length;;
        for (int i = 0; i < l - 1; i++) {
            res += Math.abs(nums[i] - nums[i + 1]);
        }
        int d = 0;
        // if the first element is swapped.
        for (int i = 0; i < l - 1; i++) {
            d = Math.max(d, Math.abs(nums[i + 1] - nums[0]) - Math.abs(nums[i + 1] - nums[i]));
        }
        // if the last element is swapped.
        for (int i = 1; i < l; i++) {
            d = Math.max(d, Math.abs(nums[i - 1] - nums[l - 1]) - Math.abs(nums[i - 1] - nums[i]));
        }
        // else 
        // *****a***b******c******d****
        // delta = 2*(min(c,d) - max(a,b));
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < l - 1; i++) {
            int curMax = Math.max(nums[i], nums[i + 1]);
            int curMin = Math.min(nums[i], nums[i + 1]);
            if (i == 0) {
                min = curMin;
                max = curMax;
                continue;
            }
            d = Math.max(d, 2 * Math.max(min - curMax, curMin - max));
            max = Math.min(max, curMax);
            min = Math.max(min, curMin);
        }
        return res + d;
    }
}