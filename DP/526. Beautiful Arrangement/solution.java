// OJ: https://leetcode.com/problems/beautiful-arrangement/
// Author: https://leetcode.com/charlesna/
// Time: O(2^n)
// Space: O(2^n)
class Solution {
    public int countArrangement(int N) {
        int[] dp = new int[1 << N];
        dp[0] = 1;
        for (int i = 0; i < (1 << N); i++) {
            int c = Integer.bitCount(i) + 1;
            for (int k = 1; k <= N; k++) {
                if ((i & (1 << (k - 1))) == 0 && (c % k == 0 || k % c == 0)) {
                    dp[i ^ (1 << (k - 1))] += dp[i];
                }
            }
        }
        return dp[(1 << N) - 1];
    }
}

// Backtrack
// O(n!)

class Solution {
    int res;
    public int countArrangement(int N) {
        res = 0;
        helper(1, new boolean[N + 1]);
        return res;
    }
    
    private void helper(int cur, boolean[] used) {
        if (cur == used.length) {
            res++;
            return;
        }
        
        for (int i = 1; i < used.length; i++) {
            if ((i % cur == 0 || cur % i == 0) && !used[i]) {
                used[i] = true;
                helper(cur + 1, used);
                used[i] = false;
            }
        }
    }
}