552. Student Attendance Record II
class Solution {
    public int checkRecord(int n) {
        //each pos has three choice A, L, P
        //if there is A bofore, can not put A
        //if there are two continous L, can not put L
//problem: we need to know the state, whether has A, or the last two choice
//so we need to according to the situation to record
        //dp represent the number of ways with i records
        //dp[k][0][i]   with no A
        //dp[k][1][i]   with A
        
        //dp[0][j][i]   with 1L
        //dp[1][j][i]   with 2L
        //dp[2][j][i]   with P
        //dp[3][j][i]   with A
        
        if (n == 0) return 1;
        if (n == 1) return 3;
        if (n == 2) return 8;
        int mod = (int) 1E9 + 7;
        long[][][] dp = new long[4][2][2];
        int now = 0;
        int old = 1;
        dp[0][0][0] = 1; dp[1][0][0] = 1; dp[2][0][0] = 2; dp[0][1][0] = 1; dp[1][1][0] = 0; dp[2][1][0] = 1; dp[3][1][0] = 2;
        for (int i = 3; i <= n; i++) {
            old = now;
            now = 1- now;
            for (int j = 0; j <= 1; j++) {
                dp[0][j][now] = (dp[2][j][old] + dp[3][j][old]) % mod;
                dp[1][j][now] = dp[0][j][old];
                dp[2][j][now] = (dp[0][j][old] + dp[1][j][old] + dp[2][j][old] + dp[3][j][old]) % mod;
                dp[3][j][now] = j == 1 ? (dp[0][0][old] + dp[1][0][old] + dp[2][0][old]) % mod : 0;
            }         
        }
        long res = 0;
        //System.out.println(dp[1][0]);
        for (int i = 0; i < 4; i++) {
            //System.out.println(dp[i][0][now] + " " + dp[i][1][now]);
            res = (res + dp[i][0][now]) % mod;
            res = (res + dp[i][1][now]) % mod;
        }
        return (int) (res % mod);
    }
}