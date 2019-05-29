1043. Partition Array for Maximum Sum
//Tiem O(nk) space O(n)
class Solution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        //using dfs thinking, there must be soem partition, but we need to figure where is the partision
        //and what's left is the subproblem 
        //dp[i] represent the max sum of first ith numbers
        int l = A.length;
        int[] dp = new int[l + 1];
        for (int i = 1; i <= l; i++) {
            int max = A[i - 1];
            for (int k = 1; k <= K && i - k >= 0; k++) {
                max = Math.max(max, A[i - k]);
                dp[i] = Math.max(dp[i], dp[i - k] + max * k);
            }
        }
        
        return dp[l];
    }
}

//time O(n2K) space O(n2)
class Solution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        //using dfs thinking, if the first partition is at pos between 0-1, 
        //dp[i][j] = for k (0, K - 1) max of dp[i][i + k] + dp[i + k + 1][j];
        int l = A.length;
        int[][] dp = new int[l][l];
        for (int k = 1; k <= l; k++) {
            for (int i = 0; i + k <= l; i++) {
                int j = i + k - 1;
                if (k <= K) {
                    int max = 0;
                    for (int index = i; index <= j; index++) {
                        max = Math.max(max, A[index]);
                    }
                    dp[i][j] = k * max;
                }
                else {
                    for (int index = i; index < j; index++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][index] + dp[index + 1][j]);
                    }
                }
            }
        }
        return dp[0][l - 1];
    }
}