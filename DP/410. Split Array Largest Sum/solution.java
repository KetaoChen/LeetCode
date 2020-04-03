// OJ: https://leetcode.com/problems/split-array-largest-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(l*log(sum)
// Space: O(1)
class Solution {
    public int splitArray(int[] nums, int m) {
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (canSplit(nums, mid, m)) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
    private boolean canSplit(int[] nums, int target, int m) {
        // System.out.println(target);
        int res = 0, index = 0, l = nums.length;
        long sum = 0;
        while (index < l) {
            boolean add = false;
            while (index < l && sum + nums[index] <= target) {
                sum += nums[index++];
                add = true;
            }
            if (!add) {
                return false;
            }
            res++;
            sum = 0;
        }
        return res <= m;
    }
}

// OJ: https://leetcode.com/problems/split-array-largest-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n*n*m)
// Space: O(n*m)
class Solution {
    public int splitArray(int[] nums, int m) {
        //dp[i][j] is the min sum for the first jth numbers, when breaking into i parts
        //dp[i][j] = max(dp[i - 1][k], sum[k+1][j])
        int l = nums.length, sum = 0;
        int[][] dp = new int[m][l];
        //init when there is only one part
        for (int i = 0; i < l; i++) {
            sum += nums[i];
            dp[0][i] = sum;
        }
        for (int i = 1; i < m; i++) {
            for (int j = i; j < l; j++) {
                sum = 0;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = j; k >= i; k--) {
                    sum += nums[k];
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k - 1], sum));
                }
            }
        }
        return dp[m - 1][l - 1];
    }
}