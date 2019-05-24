10. Regular Expression Matching
class Solution {
    public boolean isMatch(String s, String p) {
        //check the last char
        //dp[i][j] represent whether s[0,i] matches p[0,j];
        //p: three situation: char, ',' and '*';
        //if s[i] == p[j] || p[j] == '.', dp[i][j] = dp[i - 1][j - 1];
        //if p[j] == '*' if (p[j - 1] == s[i] || p[j] == '.') dp[i][j] = dp[i - 1][j]: represent one or more 
        //  represent 0 previous char:  else dp[i][j] = dp[i][j - 2];
        
        int ls = s.length();
        int lp = p.length();
        boolean[][] dp = new boolean[ls + 1][lp + 1];
        //when j = 0, only s is empty is true, which is dp[0][0] = true;
        dp[0][0] = true;
        //init when i == 0, s is empty, only p is empty or can be empty(with '*' at all even index)
        for (int j = 2; j <= lp; j = j + 2) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        
        for (int i = 1; i <= ls; i++) {
            for (int j = 1; j <= lp; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (j >= 2 && p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                    dp[i][j] |= dp[i][j - 2];
                }
            }
        }
        return dp[ls][lp];
    }
}
//Combine those two steps
class Solution {
    public int minCut(String s) {
        int l = s.length();
        if (l == 0) {
            return 0;
        }
        boolean[][] isPal = new boolean[l][l];  
        int[] dp = new int[l];
        //init when i == j, dp[i][j] = 0;
        for (int i = 0; i < l; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int k = 0; k <= i; k++) {
                if (s.charAt(i) == s.charAt(k) && (k + 1 > i - 1 || isPal[k + 1][i - 1])) {
                    isPal[k][i] = true;
                    dp[i] = k == 0 ? 0 : Math.min(dp[i], dp[k - 1] + 1);
                }
            }
        }
        return dp[l - 1];
    }
}