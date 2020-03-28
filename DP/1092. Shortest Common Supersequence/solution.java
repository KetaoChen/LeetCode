// OJ: https://leetcode.com/problems/shortest-common-supersequence/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        // 1. abac, cab  ==> LCS = ab. ==> l1 + l2 - LCS
        int l1 = str1.length(), l2 = str2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }        
        // print out the reversed LCS
        StringBuilder lcs = new StringBuilder();
        int i = l1, j = l2;
        while (i > 0 && j > 0) {
            char c1 = str1.charAt(i - 1), c2 = str2.charAt(j - 1);
            if (c1 == c2) {
                lcs.append(c1);
                i--;
                j--;
            }
            else {
                if (dp[i - 1][j] > dp[i][j - 1]) i--;
                else j--;
            }
        }
        //System.out.println(lcs.reverse().toString());
        lcs.reverse();
        StringBuilder sb = new StringBuilder();
        i = 0;
        j = 0;
        int index = 0;
        while (index < lcs.length()) {
            while (str1.charAt(i) != lcs.charAt(index)) {
                sb.append(str1.charAt(i++));
            }
            while (str2.charAt(j) != lcs.charAt(index)) {
                sb.append(str2.charAt(j++));
            }
            sb.append(lcs.charAt(index++));
            i++;
            j++;
        }
        while (i < l1) {
            sb.append(str1.charAt(i++));
        }
        while (j < l2) {
            sb.append(str2.charAt(j++));
        }
        return sb.toString();
    }
}