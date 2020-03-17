// OJ: https://leetcode.com/problems/delete-and-earn/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10001];
        for (int num : nums) {
            count[num] += num;
        }
        // if we pick i, then i-1 and i+1 can not be picked.
        int[] dp = new int[10001];
        // dp[i] is the maximum we can get for first i element.
        // if we get i, then we can not get i+1.
        int pick = 0, notPick = 0;
        for (int i = 1; i <= 10000; i++) {
            int temp = notPick;
            notPick = Math.max(notPick, pick);
            pick = temp + count[i];
        }
        return Math.max(pick, notPick);
    }
}