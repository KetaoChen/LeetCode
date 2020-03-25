// OJ: https://leetcode.com/problems/delete-operation-for-two-strings/
// Author: https://leetcode.com/charlesna/
// Time: O(n*m)
// Space: O(n*m)
class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j] means the minimum number of delete to make w1, w2 the same.
        // if w1[i] == w2[j], dp[i][j] = dp[i - 1][j - 1]
        // else dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + 1, delete w1[i] or w2[j]
        int l1 = word1.length(), l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (i == 0) {
                    dp[0][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][0] = i;
                    continue;
                }
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                dp[i][j] = Math.min(dp[i][j], Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
            }
        }
        return dp[l1][l2];
    }
}

// OJ: https://leetcode.com/problems/delete-operation-for-two-strings/
// Author: https://leetcode.com/charlesna/
// Time: O(n*m)
// Space: O(2*m)
class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j] means the minimum number of delete to make w1, w2 the same.
        // if w1[i] == w2[j], dp[i][j] = dp[i - 1][j - 1]
        // else dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + 1, delete w1[i] or w2[j]
        int l1 = word1.length(), l2 = word2.length();
        int[][] dp = new int[2][l2 + 1];
        int now = 1, old = 0;
        for (int j = 0; j <= l2; j++) {
            dp[now][j] = j;
        }
        for (int i = 1; i <= l1; i++) {
            old = now;
            now = 1 - now;
            dp[now][0] = i;
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[now][j] = dp[old][j - 1];
                }
                else {
                    dp[now][j] = Math.min(dp[old][j], dp[now][j - 1]) + 1;
                }
            }
        }
        return dp[now][l2];
    }
}