730. Count Different Palindromic Subsequences

class Solution {
    int mod = (int) 1E9 + 7;
    //dp[i][j] represent the number of palin sequence from ith number to jth number
    //if s[i] != s[j] dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j -1]
    //if s[i] == s[j]!! most complicated part.
        //if char c = s[i] which has not been shown before --> 
            //all the sequece will be used, and two more sequence are added. dp[i][j] = dp[i][j] * 2 + 2;
        //if c has been shown once.   a......a......a  --> dp[i][j] = dp[i][j] * 2 = 1
        //if c has been shown two times or more   a...a...a...a, the index are i, m, n, j
            //dp[i][j] = dp[i + 1][j - 1] * 2 - dp[m][n]
    public int countPalindromicSubsequences(String S) {    
        int l = S.length();
        long[][] dp = new long[l][l];
        
        for (int k = 1; k <= l; k++) {
            for (int i = 0; i + k <= l; i++) {
                int j = i + k - 1;
                if (k <= 2) {
                    dp[i][j] = k;
                    continue;
                }
                char first = S.charAt(i);
                char last = S.charAt(j);
                if (first != last) {
                    dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]) % mod;
                    continue;
                }
                int start = i + 1;
                int end = j - 1;
                while (S.charAt(start) != first) {
                    start++;
                }
                while (S.charAt(end) != last) {
                    end--;
                }
                //situation one or two, no same char or one same char in the mid
                if (start == j && end == i) {
                    dp[i][j] = (dp[i + 1][j - 1] * 2 + 2) % mod;
                }
                else if (start == end) {
                    dp[i][j] = (dp[i + 1][j - 1] * 2 + 1) % mod;
                }
                else {
                    dp[i][j] = (dp[i + 1][j - 1] * 2 - dp[start + 1][end - 1]) % mod;
                }
            }
        }
        return (int) (dp[0][l - 1] + mod) % mod;
    }
}