// OJ: https://leetcode.com/problems/word-break/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3) (i*j*substring)
// Space: O(n)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int l = s.length();
        boolean[] dp = new boolean[l + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        for (int i = 1; i <= l; i++) {
            for (int j = i; j >= 1; j--) {
                if (set.contains(s.substring(j- 1, i))) {
                    dp[i] |= dp[j - 1];
                }
            }
        }
        return dp[l];
    }
}