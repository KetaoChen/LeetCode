// OJ: https://leetcode.com/problems/bricks-falling-when-hit/
// Author: https://leetcode.com/charlesna/
// Time: O(n*m)
// Space: O(n*m)
class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int l = hits.length;
        int[] res = new int[l];
        if (grid.length == 0 || grid[0].length == 0) return res;
        int row = grid.length, col = grid[0].length, tot = row * col;
        int[] uf = new int[tot + 1], g = new int[tot + 1];
        for (int i = 0; i <= tot; i++) {
            uf[i] = i;
            g[i] = 1;
        }
        // validate the hits
        boolean[] valid = new boolean[l];
        for (int i = 0; i <l; i++) {
            int x = hits[i][0], y = hits[i][1];
            if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) continue;
            grid[x][y] = 0;
            valid[i] = true;
        }
        // check the final state
        int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) continue;
                int index = i * col + j;
                if (i == 0) union(uf, g, index, tot);
                for (int d = 0; d < 4; d++) {
                    int x = i + dx[d], y = j + dy[d];
                    if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 1) {
                        int nIndex = x * col + y;
                        union(uf, g, index, nIndex);
                    }
                }
            }
        }
        // reverse the hit
        for (int i = l - 1; i >= 0; i--) {
            if (!valid[i]) continue;
            int x = hits[i][0], y = hits[i][1];
            int index = x * col + y;
            grid[x][y] = 1;
            int old = g[find(uf, tot)];
            if (x == 0) union(uf, g, index, tot);
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && grid[nx][ny] == 1) {
                    int nIndex = nx * col + ny;
                    union(uf, g, index, nIndex);
                }
            }
            res[i] = Math.max(0, g[find(uf, tot)] - old - 1);
        }
        return res;
    }
    private int find(int[] uf, int i) {
        if (uf[i] == i) return i;
        uf[i] = find(uf, uf[i]);
        return uf[i];
    }
    private void union(int[] uf, int[] g, int i, int j) {
        int pi = find(uf, i);
        int pj = find(uf, j);
        if (pi != pj) {
            uf[pi] = pj;
            g[pj] += g[pi];
        }
    }
}