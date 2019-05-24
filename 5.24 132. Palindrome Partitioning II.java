132. Palindrome Partitioning II
class Solution {
    public int minCut(String s) {
        //pretreat the data, find out all the palindrome in the s.
        //isPal[i][j] represent whether s[i,j] is a palindrome
        //this is also a dp problem
        
        //dp[i] represent the min cuts of first ith chars;
        //for (int k = [0,i]) find out the min of dp[i][k - 1] + 1, when isPal[k][i]
        int l = s.length();
        if (l == 0) {
            return 0;
        }
        boolean[][] isPal = getAllPalin(s);  
        int[] dp = new int[l];
        //init when i == j, dp[i][j] = 0;
        for (int i = 0; i < l; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int k = 0; k <= i; k++) {
                if (isPal[k][i]) {
                    dp[i] = k == 0 ? 0 : Math.min(dp[i], dp[k - 1] + 1);
                }
            }
            // for (int j = 0; j < l; j++) {
            //     System.out.print(dp[j] + " ");
            // }
            // System.out.println();
        }
        
        return dp[l - 1];
    }
    
    private boolean[][] getAllPalin(String s) {
        int l = s.length();
        boolean[][] dp = new boolean[l][l];
        //init when i = j
        for (int i = 0; i < l; i++) {
            dp[i][i] = true;
        }
        //length starts from 2;
        for (int k = 2; k <= l; k++) {
            for (int i = 0; i + k <= l; i++) {
                int j = i + k - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = k == 2 ? true : dp[i + 1][j - 1];
                }
            }
        }
        // for (int i = 0; i < l; i++) {
        //     for (int j = 0; j < l; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return dp;
    }
}