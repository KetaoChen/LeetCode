688. Knight Probability in Chessboard
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        //after the knight jump to another point, the subproblem is to calculate the probability of this points
        //there are 8 possibilities to go.
        double[][][] memo = new double[N][N][K + 1];
        return helper(N, K, r, c, memo);
    }
    
    private double helper(int N, int K, int r, int c, double[][][] memo) {
        if (r < 0 || r >= N || c < 0 || c >= N) {
            return 0.0;
        }
        if (K == 0) {
            memo[r][c][K] = 1.0;
            return 1.0;
        }
        if (memo[r][c][K] != 0.0) {
            return memo[r][c][K];
        }
        int[] dc = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
        double res = 0.0;
        
        for (int k = 0; k < 8; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                res += helper(N, K - 1, nr, nc, memo);
            }
        }
        memo[r][c][K] = res / 8.0;
        return res / 8.0;
    }
}