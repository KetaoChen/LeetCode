// OJ: https://leetcode.com/problems/largest-divisible-subset/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n)
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int l = nums.length;
        if (l == 0) return new ArrayList<>();
        Arrays.sort(nums);
        // dp[i] is the largest size of set, which including nums[i];
        // since it is an ascending array. if a number can be divided by this number, it can be divided by others
        int[] dp = new int[l], pi = new int[l];
        int max = 1, index = 0;
        dp[0] = 1;
        pi[0] = -1;
        for (int i = 1; i < l; i++) {
            dp[i] = 1;
            pi[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    pi[i] = j;
                    if (dp[i] > max) {
                        max = dp[i];
                        index = i;
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index >= 0) {
            res.add(nums[index]);
            index = pi[index];
        }
        return res;
    }
}