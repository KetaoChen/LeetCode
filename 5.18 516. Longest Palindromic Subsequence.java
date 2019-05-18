class Solution {
    public int longestPalindromeSubseq(String s) {
        //if the first and the last char are different, dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
        //if the same. dp[i][j] = dp[i + 1][j - 1] + 2;
        
        if (s == null || s.length() == 0) {
            return 0;
        }
        int l = s.length();
        int[][] dp = new int[l][l];
        //init for odd; and even
        for (int i = 0; i < l; i++) {
            dp[i][i] = 1;
            if (i + 1 < l && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = 2;
            }
        }
        
        for (int len = 3; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][l - 1];
    }
}