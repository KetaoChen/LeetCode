// OJ: https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int l = jobDifficulty.length;
        if (l < d) {
            return -1;
        }
        int[][] dp = new int[2][l];
        int now = 0, old = 1;
        dp[now][0] = jobDifficulty[0];
        for (int i = 1; i < l; i++) {
            dp[now][i] = Math.max(dp[now][i - 1], jobDifficulty[i]);
        }
        // System.out.println("good");
		// every day, we update our dp[i], means the min cost to finish first ith job.
        for (int day = 1; day < d; day++) {
            Stack<Integer> stack = new Stack<>();
            old = now;
            now = 1 - now;
			// for each job, if this job is less difficult
				// if it merge to the previous one, it will not increase the dp[i] = dp[i - 1].
				// if it does not merge, dp[i] = dp[old][i - 1] + diff[i]. we need to choose the min.
			// if this job is more difficult. 
				// we want to use this job to merge previous jobs which is less difficult than it.
			// that's the reason we want to use a descending monotonic stack to keep the order.
            for (int i = day; i < l; i++) {
                dp[now][i] = dp[old][i - 1] + jobDifficulty[i];
                while (!stack.isEmpty() && jobDifficulty[stack.peek()] <= jobDifficulty[i]) {
                    int index = stack.pop();
                    dp[now][i] = Math.min(dp[now][i], dp[now][index] + jobDifficulty[i] - jobDifficulty[index]);
                }
                if (!stack.isEmpty()) {
                    dp[now][i] = Math.min(dp[now][i], dp[now][stack.peek()]);
                }
                stack.push(i);
            }
        }
        return dp[now][l - 1];
    }
}


// OJ: https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
// Author: https://leetcode.com/charlesna/
// Time: O(n*n*d)
// Space: O(n*d)
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int l = jobDifficulty.length;
        if (l < d) {
            return -1;
        }
        // dp[i][j] means the total diff for first ith job in j days.
        int[][] dp = new int[l + 1][d + 1];
        int[][] max = new int[l][l];
        for (int len = 1; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    max[i][j] = jobDifficulty[i];
                    continue;
                }
                max[i][j] = Math.max(max[i + 1][j], max[i][j - 1]);
            }
        }
        for (int i = 1; i <= l; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= l; i++) {
            for (int k = 1; k <= Math.min(i, d); k++) {
                if (k == 1) {
                    dp[i][k] = Math.max(dp[i - 1][k], jobDifficulty[i - 1]);
                    continue;
                }
                for (int j = k - 1; j < i; j++) {
                    dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + max[j][i - 1]);
                }
            }
        }
        return dp[l][d];
    }
}