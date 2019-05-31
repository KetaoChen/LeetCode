940. Distinct Subsequences II
class Solution {
    
    public int distinctSubseqII(String S) {
        //dp[i] represent the number of different sequence for the first ith numbers.
        //dp[i] = dp[i - 1] * 2 + 1 if s[i] does not appear before
        // ...a.....a
        //    j     i
        //dp[i] = dp[i - 1] * 2 - dp[j - 1];
        int mod = (int) 1E9 + 7;
        int l = S.length();
        long[] dp = new long[l + 1];
        dp[0] = 0;
        //map to record the last index when this char appear.
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 1; i <= l; i++) {
            dp[i] = (dp[i - 1] * 2) % mod;
            char cur = S.charAt(i - 1);
            if (map.containsKey(cur)) {
                dp[i] = (dp[i] - dp[map.get(cur) - 1]) % mod;
            }
            else {
                dp[i]++;
            }
            map.put(cur, i);
        }
        return (int) (dp[l] + mod) % mod;
    }
}