// OJ: https://leetcode.com/problems/new-21-game/
// Author: https://leetcode.com/charlesna/
// Time: O(N)
// Space: O(N)
class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        double[] dp = new double[N + 1];
        dp[0] = 1.0;
        double res = 0.0, pSum = 1.0;
        // dp[i] is the sum of possibility of previous W points sum(dp[i - W], dp[i - 1]) / W
        for (int i = 1; i <= N; i++) {
            dp[i] = pSum / W;
            if (i >= K) {
                res += dp[i];
            }
            else {
                pSum += dp[i];
            }
            if (i - W >= 0) {
                pSum -= dp[i - W];
            }
        }
        return res;
    }
}

// OJ: https://leetcode.com/problems/new-21-game/
// Author: https://leetcode.com/charlesna/
// Time: O(N*W) TLE
// Space: O(N)
class Solution {
    public double new21Game(int N, int K, int W) {
		// dp[i] is the possibility to get i points.
        double[] dp = new double[N + 1];
        double res = 0.0;
        dp[0] = 1.0;
		
		
        for (int i = 0; i <= N; i++) {
			// if i >= K, we stop drawing, and add the possibility to the res.
            if (i >= K) {
                res += dp[i];
                continue;
            }
			// if the point is smaller than k, we will draw another card.
			// so the possibility of [i + 1, i + W] will increase dp[i] / W because the new drawing cards
            for (int j = 1; j <= W && i + j <= N; j++) {
                dp[i + j] += dp[i] / W;
            }
        }
        return res;
    }
}