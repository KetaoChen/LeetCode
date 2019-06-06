837. New 21 Game

//优化思路！！发现N和K永远相差相同的数值，因为K和N是同时被减的。
//所以由此可以发现不用把每一个N和K都遍历一遍，所以降了一维。
//另外看W的维度，对于每一个新的N，都要从N-1到N-W给遍历一遍，所以可以引入一个slide window来记录当前这个区间里的总和，又把一个O(n)的操作进化为O(1)
//这就是这个题目的魅力了。
class Solution {
    public double new21Game(int N, int K, int W) {
        int start = N - K + 1;
        //always less than N when reach K points.
        if (start >= W) {
            return 1.0;
        }
        
        //dp[n] represent with n = n, k = n - (N - K), the probablity to get the value <= n;
        //initialize when k = 1, n = 1 + (N - K)
        double[] dp = new double[N + 1];
        for (int i = 0; i < start; i++) {
            dp[i] = 1.0;
        }
        double sum = start;
        for (int i = start; i <= N; i++) {
            dp[i] = sum / W;
            if (i >= W) {
                sum -= dp[i - W];
            }
            sum += dp[i];
        }
        
        // for (int i = 0; i < dp.length; i++) {
        //     System.out.print(dp[i] + " ");
        // }
        return dp[N];
    }
}

class Solution {
    public double new21Game(int N, int K, int W) {
        //for each time, Alice drow one number, there W cases with same probability
        //for each situation, there is a subproblem, have new targer N - val, K - val, W
        //so the exit is that when K <= 0
        //dp[i][j] represent the probability when N = i, K = k
        //for (int j = 1; j <= W; j++)
        //dp[i][k] += dp[i - 1][k - j]
        double[][] dp = new double[N + 1][K + 1];
        //initial, when N == 0 dp[0][k] = 0 can always get N points
        //when k == 0, when n > 0, its impossible to reach N, so dp[n][0] = 1;
        for (int n = 0; n <= N; n++) {
            dp[n][0] = 1.0;
        }
       
        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                for (int j = 1; j <= W; j++) {
                    if (j <= n && j <= k) {
                        dp[n][k] += dp[n - j][k - j];
                    }
                    else if (j > k && j <= n) {
                        dp[n][k] += 1.0;   
                    }
                }
                dp[n][k] /= W;
            }
        }
        
        // for (int i = 0; i <= N; i++) {
        //     for (int j = 0; j <= K; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return dp[N][K];
    }
}