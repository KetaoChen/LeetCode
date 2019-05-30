91. Decode Ways
class Solution {
    public int numDecodings(String s) {
        
        int l = s.length();
        int[] dp = new int[l + 1];
        //init zero
        dp[0] = 1;
        //dp represent how many ways to decode the s[0, i]
        //dp[i] += dp[i - 1] if (s.charAt(i) != 0)
        //dp[i] += dp[i - 2] if (i >= 2 && Integer.parseInt(s.substring(i-2, i)) >= 10 && <= 26)
        
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) != '0') {
                dp[i + 1] += dp[i];
            }
            if (i >= 1) {
                int val = Integer.parseInt(s.substring(i - 1, i + 1));
                if (val >= 10 && val <= 26) {
                    dp[i + 1] += dp[i - 1];
                }
            }
        }
        // for (int i = 0; i <= l; i++) {
        //     System.out.print(dp[i]);
        // }
        return dp[l];
    }
}