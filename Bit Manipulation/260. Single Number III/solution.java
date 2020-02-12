// OJ: https://leetcode.com/problems/single-number-iii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int[] singleNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        int k = 0;
        while (((sum >> k) & 1) == 0) {
            k++;
        }
        int s = 0;
        for (int num : nums) {
            if (((num >> k) & 1) == 1) {
                s ^= num;
            }
        }
        return new int[]{s, sum ^ s};
    }
}