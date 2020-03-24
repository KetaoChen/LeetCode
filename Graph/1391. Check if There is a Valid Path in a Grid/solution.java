// OJ: https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(m*n)
class Solution {
    public boolean hasValidPath(int[][] grid) {
        //          up, left, right, down 
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        // e.g. for the first cell, it has component left and right
        int[][] d = {{}, {1, 2}, {0, 3}, {1, 3}, {2, 3}, {0, 1}, {0, 2}};
        int n = grid.length, m = grid[0].length;
        // for each cell, check its components and check connectivity;
        int[] p = new int[n * m];
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = grid[i][j];
                for (int curd : d[val]) {
                    int nx = i + dx[curd];
                    int ny = j + dy[curd];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        int next = grid[nx][ny];
                        for (int nd : d[next]) {
                            // only left ^ right and up ^ down can get 1.
                            //        10 ^ 01        00 ^ 11
                            if ((nd ^ curd) == 3) {
                                union(p, i * m + j, nx * m + ny);
                            }
                        }
                    }
                }
            }
        }
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         System.out.print(p[i * m + j] + " ");
        //     }
        //     System.out.println();
        // }
        return find(p, 0) == find(p, m * n - 1);
    }
    private int find(int[] p, int x) {
        if (p[x] == x) {
            return x;
        }
        p[x] = find(p, p[x]);
        return p[x];
    }
    private void union(int[] p, int x, int y) {
        int px = find(p, x);
        int py = find(p, y);
        if (px != py) {
            p[py] = px;
        }
    }
}