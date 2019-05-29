935. Knight Dialer
class Solution {
    int mod = (int) 1E9 + 7;
    public int knightDialer(int N) {
        int[][] steps = new int[10][];
        long[][] memo = new long[10][N];
        for (long[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        steps[0] = new int[]{4, 6};
        steps[1] = new int[]{6, 8};
        steps[2] = new int[]{7, 9};
        steps[3] = new int[]{4, 8};
        steps[4] = new int[]{0, 3, 9};
        steps[5] = new int[]{};
        steps[6] = new int[]{0, 1, 7};
        steps[7] = new int[]{2, 6};
        steps[8] = new int[]{1, 3};
        steps[9] = new int[]{2, 4};
        long res = 0;
        for (int i = 0; i <= 9; i++) {
            res = (res + helper(i, N - 1, steps, memo)) % mod;
        }  
        return (int) res;
    }
    
    private long helper(int cur, int N, int[][] steps, long[][] memo) {
        if (memo[cur][N] != - 1) {
            return memo[cur][N];
        }
        if (N == 0) {
            memo[cur][N] = 1;
            return 1;
        }
        
        long res = 0;
        for (int next : steps[cur]) {
            res = (res + helper(next, N - 1, steps, memo)) % mod ;
        }        
        memo[cur][N] = res;
        return res;
    }
}