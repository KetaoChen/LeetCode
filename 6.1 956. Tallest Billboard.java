956. Tallest Billboard

//寒神method 
class Solution {
    public int tallestBillboard(int[] rods) {
        //there should be at most three parts, first used, second used and not used.
        //dp[d] represents the max sum of first and second piles when diff = d
        //then when we add another rods[k], we update dp[], this rod can be added to the longer one or shorter one.
        //if (d + rods[k] <= sum) dp[d + rods[k]] = Math.max(dp[d + rods[k]], dp[d] + rods[k]);  ---> add to the longer
        //if (d - rods[k] >= 0 or < 0) dp[d- rods[k]] = Math.max(dp[abs(d - rods[k])], dp[d] + rods[k]);
        //return dp[0] / 2;
        
        int l = rods.length;
        if (l <= 1) {
            return 0;
        }
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }
        int[] dp = new int[sum / 2 + 1];
        Arrays.fill(dp, -100000);
        dp[0] = 0;
        
        for (int k = 0; k < l; k++) {
            int[] temp = dp.clone();
            for (int d = 0; d <= sum / 2; d++) {
                if (dp[d] != -100000) {
                    if (d + rods[k] <= sum / 2) {
                        dp[d + rods[k]] = Math.max(dp[d + rods[k]], temp[d] + rods[k]);
                    }
                    if (Math.abs(d - rods[k]) <= sum / 2) {
                        dp[Math.abs(d - rods[k])] = Math.max(dp[Math.abs(d - rods[k])], temp[d] + rods[k]);
                    }
                }
            }
        }
        
        return dp[0] / 2;
    }
}


//自己TLE的方法。。Time O(L*(Sum)2))
class Solution {
    public int tallestBillboard(int[] rods) {
        //for each number, it will be in the first pile, or second file, or neither of both.
        //dp[i][j] represent whether it is possible to have two piles with length i, j
        //for each item, update dp[i][j]
        //dp[i][j] = dp[i - rod][j] || dp[i][j - rod]
        
        int l = rods.length;
        if (l <= 1) {
            return 0;
        }
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }
        boolean[][] dp = new boolean[sum / 2 + 1][sum / 2 + 1];
        //initilze i == 0, j == 0, dp[i][j] = true
        dp[0][0] = true;
        //start dp
        for (int k = 0; k < l; k++) {
            for (int i = sum / 2; i >= 0; i--) {
                for (int j = sum / 2; j>= 0; j--) {
                    if (i >= rods[k]) {
                        dp[i][j] |= dp[i - rods[k]][j];
                    }
                    if (j >= rods[k]) {
                        dp[i][j] |= dp[i][j - rods[k]];
                    }
                }
            }
        }
        
        for (int i = sum / 2; i >= 0; i--) {
            if (dp[i][i]) {
                return i;
            }
        }
        return 0;
    }
}