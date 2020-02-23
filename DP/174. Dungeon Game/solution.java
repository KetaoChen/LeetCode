// OJ: https://leetcode.com/problems/dungeon-game/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(n)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];
        // dp[i][j] is the smallest energy before fight with demons in matrix[i][j]
        // next step will go to matrix[i + 1][j] or matrix[i][j + 1]. 
        // we will choose the one with a lower energy need. 
        // in order to have enough energy to go to next room with dp[i + 1][j]
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                dp[i][j] = i == row - 1 && j == col - 1 ? Math.max(1, -dungeon[i][j] + 1) : Integer.MAX_VALUE;
                if (i < row - 1) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(1, dp[i + 1][j] - dungeon[i][j]));
                }
                if (j < col - 1) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(1, dp[i][j + 1] - dungeon[i][j]));
                }
            }
        }
        // get the path of hero, start from the first position, go the dp[next] with smaller required energy
        int nrow = 0, ncol = 0;
        System.out.println(nrow + " " + ncol + " " + dp[nrow][ncol]);
        while (nrow != row - 1 || ncol != col - 1) {
            if (nrow + 1 == row) {
                ncol++;
            }
            else if (ncol + 1 == col) {
                nrow++;
            }
            else {
                if (dp[nrow + 1][ncol] >= dp[nrow][ncol + 1]) {
                    ncol++;
                }
                else {
                    nrow++;
                }
            }
            System.out.println(nrow + " " + ncol + " " + dp[nrow][ncol]);
        }
        return dp[0][0];
    }
}

// OJ: https://leetcode.com/problems/dungeon-game/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(n)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[2][col];
        // dp[i][j] is the smallest energy before fight with demons in matrix[i][j]
        // next step will go to matrix[i + 1][j] or matrix[i][j + 1]. 
        // we will choose the one with a lower energy need. 
        // in order to have enough energy to go to next room with dp[i + 1][j]
        int now = 0, old = 1;
        for (int i = row - 1; i >= 0; i--) {
            old = now;
            now = 1 - now;
            for (int j = col - 1; j >= 0; j--) {
                dp[now][j] = i == row - 1 && j == col - 1 ? Math.max(1, -dungeon[row - 1][col - 1] + 1) : Integer.MAX_VALUE;
                if (i < row - 1) {
                    dp[now][j] = Math.min(dp[now][j], Math.max(1, dp[old][j] - dungeon[i][j]));
                }
                if (j < col - 1) {
                    dp[now][j] = Math.min(dp[now][j], Math.max(1, dp[now][j + 1] - dungeon[i][j]));
                }
            }
        }
        return dp[now][0];
    }
}

