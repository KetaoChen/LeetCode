// OJ: https://leetcode.com/problems/cat-and-mouse/
// Author: https://leetcode.com/charlesna/
// Time: O(n*n)
// Space: O(n*n)
class Solution {
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        // dp[i][j][k]: 0: draw, 1: mouse win, 2: cat win.
        //          k :          1: mouse turn 2: cat turn.
        int[][][] dp = new int[n][n][3];       
        // starting from the state that we are sure about the result, then use bfs.
        Queue<int[]> q = new LinkedList<>();
        // initialize
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][i][k] = 2;
                q.offer(new int[]{i, i, k});
                dp[0][i][k] = 1;
                q.offer(new int[]{0, i, k});
            }
        }
        // do bfs
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0], j = cur[1], k = cur[2];
            //  check the prev step of mouse, is win or lose
            for (int prev : graph[i]) {
                if (dp[prev][j][1] != 0 || k == 1) continue;
                // win this round.
                if (dp[i][j][k] == 1) {
                    dp[prev][j][1] = 1;
                    q.offer(new int[]{prev, j, 1});
                }
                else {
                    boolean lose = true;
                    for (int next : graph[prev]) {
                        if (dp[next][j][2] != 2) {
                            lose = false;
                            break;
                        }
                    }
                    if (lose) {
                        dp[prev][j][1] = 2;
                        q.offer(new int[]{prev, j, 1});
                    }
                }
            }
            // check the prev step of cat
            for (int prev : graph[j]) {
                if (dp[i][prev][2] != 0 || k == 2 || prev == 0) continue;
                if (dp[i][j][k] == 2) {
                    dp[i][prev][2] = 2;
                    q.offer(new int[]{i, prev, 2});
                }
                else {
                    boolean lose = true;
                    for (int next : graph[prev]) {
                        if (next == 0) continue;
                        if (dp[i][next][1] != 1) {
                            lose = false;
                            break;
                        }
                    }
                    if (lose) {
                        dp[i][prev][2] = 1;
                        q.offer(new int[]{i, prev, 2});
                    }
                }
            }
        }
        return dp[1][2][1];
    }
}