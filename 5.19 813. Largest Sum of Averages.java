class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        //adjacent group!! the largest score.
        //check the last group, check how many numbers are there.
        //the subproblem is that the rest of numbers should devide into k - 1 group with largest average sum.
        //dp[i][k] represent the largest average sum of first ith items when deviding into kth group.
        //dp[i][k] = for int j from [k, i] get max of (dp[j - 1][k - 1] + ave of A[j - i])
        
        int l = A.length;
        //get prefixSum
        int[] preSum = new int[l + 1];
        for (int i = 0; i < l; i++) {
            preSum[i + 1] = preSum[i] + A[i];
        }
        
        double[][] dp = new double[l + 1][K + 1];
        //init: when the first 0 item, i == 0, dp[0][j] = 0;
        //when devide into 0 groups, k == 0, dp[i][0] = 0;
        for (int i = 1; i <= l; i++) {
            for (int k = 1; k <= K && k <= i; k++) {
                if (k == 1) {
                    dp[i][k] = preSum[i] / ((double) i);
                    continue;
                }
                // dp[i][k] = dp[i - 1][k - 1] + A[i - 1];
                for (int j = k - 1; j <= i; j++) {
                    dp[i][k] = Math.max(dp[i][k], dp[j - 1][k - 1] + (preSum[i] - preSum[j - 1]) / (double) (i - j + 1));
                }
            }
        }
        // for (int i = 0; i <= l; i++) {
        //     for (int j = 0; j <= K; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return dp[l][K];
    }
}