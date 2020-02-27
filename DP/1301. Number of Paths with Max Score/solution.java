// OJ: https://leetcode.com/problems/number-of-paths-with-max-score/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int l = board.size();
        long[][][] dp = new long[l][l][2];
        int mod = 1000000007;
        //dp[i][j][k] represent the number of ways to get i, j.
        //dp[i][j][k] = sum(dp[i - 1][j][] + dp[i][j - 1][k -cur]) + dp[i - 1][j - 1][k - cur]
        dp[l - 1][l - 1][1] = 1;
        //for the last row
        for (int j = l - 2; j >= 0; j--) {
            if (board.get(l - 1).charAt(j) == 'X') {
                break;
            }
            dp[l - 1][j][0] = board.get(l - 1).charAt(j) - '0' + dp[l - 1][j + 1][0];
            dp[l - 1][j][1] = 1;
        }
        //last column
        for (int i = l - 2; i >= 0; i--) {
            if (board.get(i).charAt(l - 1) == 'X') {
                break;
            }
            dp[i][l - 1][0] = board.get(i).charAt(l - 1) - '0' + dp[i + 1][l - 1][0];
            dp[i][l - 1][1] = 1;
        }
        int[] dx = {0, -1, -1};
        int[] dy = {-1, 0, -1};
        for (int i = l - 2; i >= 0; i--) {
            String s = board.get(i);
            for (int j = l - 2; j >= 0; j--) {
                if (s.charAt(j) == 'X') {
                    dp[i][j][1] = 0;
                    continue;
                }
                for (int d = 0; d < 3; d++) {
                    if (dp[i - dx[d]][j - dy[d]][1] == 0) {
                        continue;
                    }
                    long sum = s.charAt(j) == 'E' ? dp[i - dx[d]][j - dy[d]][0] : dp[i - dx[d]][j - dy[d]][0] + s.charAt(j) - '0';
                    if (dp[i][j][0] == sum) {
                        dp[i][j][1] = (dp[i][j][1] + dp[i - dx[d]][j - dy[d]][1]) % mod;
                    }
                    else if (dp[i][j][0] < sum) {
                        dp[i][j][0] = sum;
                        dp[i][j][1] = dp[i - dx[d]][j - dy[d]][1];
                    }
                }
            }
        }
        return new int[]{(int)dp[0][0][0], (int)dp[0][0][1]};
    }
}
