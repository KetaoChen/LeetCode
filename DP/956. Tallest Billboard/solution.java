// OJ: https://leetcode.com/problems/tallest-billboard/
// Author: https://leetcode.com/charlesna/
// Time: O(n*sum)
// Space: O(sum)
class Solution {
    public int tallestBillboard(int[] rods) {
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }
        int[] dp = new int[sum + 1];
		// make sure to initiate all the default value.
        Arrays.fill(dp, -1);
        dp[0] = 0;
        // dp[i] represents the max height when the diff between left and right is i.
        // for a new rod can add to the left or the right or not to add.
        for (int rod : rods) {
            int[] temp = dp.clone();
            for (int i = 0; i <= sum; i++) {
                if (temp[i] == -1) {
                    continue;
                }
                // if we add the rod to the longer one
                if (i + rod <= sum) {
                    dp[i + rod] = Math.max(dp[i + rod], temp[i] + rod);
                }
                // if we add to the shorter one
                // the short one is still shorter
                if (i - rod >= 0) {
                    dp[i - rod] = Math.max(dp[i - rod], temp[i]);
                }
                // the shorter one becomes longer
                else {
                    dp[rod - i] = Math.max(dp[rod - i], temp[i] - i + rod);
                }
            }
        }
        return dp[0];
    }
}

// OJ: https://leetcode.com/problems/tallest-billboard/
// Author: https://leetcode.com/charlesna/
// Time: O(n*sum*sum = 20*2500*2500 = 1.25*10^8) TLE
// Space: O(sum*sum)
class Solution {
    public int tallestBillboard2(int[] rods) {
        // each rod can be assign to three parts: left, right, or not use
        // if we put current rod into left part or right
        // dp[i][j] represents for the first i rods, we have i height on left and j height on right
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }
        int l = sum / 2;
        boolean[][] dp = new boolean[l + 1][l + 1];
        dp[0][0] = true;
        for (int rod : rods) {
            for (int i = l; i >= 0; i--) {
                for (int j = l; j >= 0; j--) {
                    if (i >= rod) {
                        dp[i][j] |= dp[i - rod][j];
                    }
                    if (j >= rod) {
                        dp[i][j] |= dp[i][j - rod];
                    }
                }
            }
        }
        for (int i = l; i >= 0; i--) {
            if (dp[i][i]) {
                return i;
            }
        }
        return -1;
    }
}