// OJ: https://leetcode.com/problems/valid-permutations-for-di-sequence/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int numPermsDISequence(String S) {
        int l = S.length(), mod = (int) (1e9 + 7);
        if (l == 0) return 0;
        // dp[i][j] is the number of permutation for first ith number, ending with j.
        long[][] dp = new long[l + 1][l + 1];
        // initialize, the first number 
        dp[0][0] = 1;
        // dp[i][j] = c == 'D' ? sum(dp[i-1][j:i-1]) : sum(dp[i-1][0:j-1]);
        for (int i = 1; i <= l; i++) {
            char c = S.charAt(i - 1);
            long sum = 0;
            if (c == 'D') {
                for (int j = i - 1; j >= 0; j--) {
                    sum = (sum + dp[i - 1][j]) % mod;
                    dp[i][j] = sum;
                }
            }
            else {
                for (int j = 1; j <= i; j++) {
                    sum = (sum + dp[i - 1][j - 1]) % mod;
                    dp[i][j] = sum;
                }
            }
        }
        long res = 0;
        for (int j = 0; j <= l; j++) {
            res = (res + dp[l][j]) % mod;
        }
        return (int) res;
    }
}

// OJ: https://leetcode.com/problems/valid-permutations-for-di-sequence/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3)
// Space: O(n^2)
class Solution {
    int mod = (int) (1e9 + 7);
    long[][] f;
    Map<String, Integer> map = new HashMap<>();
    private void getCombo() {
        f = new long[201][201];
        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    f[i][j] = 1;
                    continue;
                }
                f[i][j] = (f[i - 1][j] + f[i - 1][j - 1]) % mod;
            }
        } 
    }
    public int numPermsDISequence(String S) {
        int l = S.length();
        if (l <= 1) return 1;
        if (map.containsKey(S)) return map.get(S);
        if (f == null) getCombo();
        long res = S.charAt(0) == 'D' ? numPermsDISequence(S.substring(1)) : 0;
        for (int i = 0; i < l - 1; i++) {
            if (S.charAt(i) == 'I' && S.charAt(i + 1) == 'D') {
                res = (res + (f[l][i + 1] * (numPermsDISequence(S.substring(0, i))) % mod * numPermsDISequence(S.substring(i + 2)))) % mod;
            }
        }
        if (S.charAt(l - 1) == 'I') res = (res + numPermsDISequence(S.substring(0, l - 1))) % mod;
        res = (res + mod) % mod;
        map.put(S, (int) res);
        return (int)res;
    }
}