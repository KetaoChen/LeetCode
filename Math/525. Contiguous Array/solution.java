// OJ: https://leetcode.com/problems/contiguous-array/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
            else {
                res = Math.max(res, i - map.get(sum));
            }
        }
        return res;
    }
}