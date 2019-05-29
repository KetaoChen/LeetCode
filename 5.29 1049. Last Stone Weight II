1049. Last Stone Weight II
class Solution {
    public int lastStoneWeightII(int[] stones) {
        //each rock has to be pos or neg in the smash.
        //all the stones must be devided into two parts. one part is pos, one is neg
        //now the question becomes that what is the answ closest to a target, which is 0 for the initial
        
        //after we take the last stone as pos or neg, all the stones left need to become the new target
        //as the number of stones are less than 30 and the val of stone is smaller than 100, so the max val = 3000
        //dp[i][j] represent whether it is possible for the first ith stones to get val j, which j [0, 6000]
        
        int l = stones.length;
        boolean[][] dp = new boolean[l + 1][6001];
        //init 6000 is from -3000 to 3000
        dp[0][3000] = true;
        for (int i = 1; i <= l; i++) {
            for (int j = 0; j <= 6000; j++) {
                if (j - stones[i - 1] >= 0) {
                    dp[i][j] |= dp[i - 1][j - stones[i - 1]];
                }
                if (j + stones[i - 1] <= 6000) {
                    dp[i][j] |= dp[i - 1][j + stones[i - 1]];
                }
            }
        }
        for (int k = 0; k <=30; k++) {        
            if (dp[l][3000 + k] || dp[l][3000 - k]) {
                return k;
            }
        }
        return -1;
    }
}