// OJ: https://leetcode.com/problems/palindrome-partitioning-iii/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2*k)
// Space: O(n^2)
class Solution {
    public int palindromePartition(String s, int k) {
        // dp[i][k] means the min change to make s[0-i] split into k palin
        // base case i == k dp[i][k] = 1;
        // dp[i][k] = loop the last palin and find the min: min(dp[m][k-1] + change[m+1][i])
        int l = s.length();
        int[][] dp = new int[l][k + 1];
        int[][] change = new int[l][l];
        for (int len = 1; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                if (len <= 2) {
                    change[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                    continue;
                }
                change[i][j] = s.charAt(i) == s.charAt(j) ? change[i + 1][j - 1] : change[i + 1][j - 1] + 1;
            }
        }
        for (int m = 1; m <= k; m++) {
            for (int i = m - 1; i < l; i++) {
                dp[i][m] = i;
                if (m == 1) {
                    dp[i][m] = change[0][i];
                    continue;
                }
                for (int j = m - 2; j < i ; j++) {
                    dp[i][m] = Math.min(dp[i][m], dp[j][m - 1] + change[j + 1][i]);
                }
            }
        }
        return dp[l - 1][k];
    }
}

// OJ: https://leetcode.com/problems/palindrome-partitioning-iii/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3*k)
// Space: O(n^2*k)
class Solution {
    public int palindromePartition(String s, int k) {
        // dp[i][j][k] means the min change to make s[i-j] split into k palin
        // base case j-i+1 == k dp[i][j][k] = 1;
        // dp[i][j][k] = loop the last palin find the min: min(dp[i][m][k-1] + dp[m+1][j][1])
        int l = s.length();
        int[][][] dp = new int[l][l][k + 1];
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                for (int m = 1; m <= k; m++) {
                    dp[i][j][m] = l;
                }
            }
        }
        for (int len = 1; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                for (int m = 1; m <= Math.min(len, k); m++) {
                    if (len == m) {
                        dp[i][j][m] = 0;
                        continue;
                    }
                    if (m == 1) {
                        dp[i][j][m] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1][m] : dp[i + 1][j - 1][m] + 1;
                        continue;
                    }
                    for (int n = i + m - 2; n < j; n++) {
                        dp[i][j][m] = Math.min(dp[i][j][m], dp[i][n][m - 1] + dp[n + 1][j][1]);
                    }
                }
            }
        }

        return dp[0][l-1][k];
    }
}