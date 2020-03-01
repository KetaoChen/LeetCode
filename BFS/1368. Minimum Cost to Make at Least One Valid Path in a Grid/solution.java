// OJ: https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(m*n)
class Solution {
    public int minCost(int[][] grid) {
        // dp[i][j] is the min chagne to reach right bot at point[i][j]
        // dp[i][j] = 
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        dfs(grid, new int[]{0, 0}, q, visited);
        int res = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!q.isEmpty()) {
            int size = q.size();
            //System.out.println(size);
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                if (cur[0] == n - 1 && cur[1] == m - 1) {
                    return res;
                }
                for (int d = 0; d < 4; d++) {
                    int ni = cur[0] + dx[d];
                    int nj = cur[1] + dy[d];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && !visited[ni][nj]) {
                        dfs(grid, new int[]{ni, nj}, q, visited);
                    }
                }
            }
            res++;
        }
        return res;
    }
    private void dfs(int[][] grid, int[] cur, Queue<int[]> q, boolean[][] visited) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int ni = cur[0];
        int nj = cur[1];
        while (ni >= 0 && ni < n && nj >= 0 && nj < m && !visited[ni][nj]) {
            //System.out.println("before " + ni + " " + nj);
            visited[ni][nj] = true;
            q.offer(new int[]{ni, nj});
            int i = ni + dx[grid[ni][nj] - 1];
            int j = nj + dy[grid[ni][nj] - 1];
            ni = i;
            nj = j;
            //System.out.println("after " + ni + " " + nj);
        }
    }
}