// OJ: https://leetcode.com/problems/count-servers-that-communicate/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(1)
class Solution {
    public int countServers(int[][] grid) {
        int res = 0;
        int r = grid.length;
        int c = grid[0].length;
        int[] row = new int[r];
        int[] col = new int[c];
        int all = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;   
                    all++;
                }
            }
        }
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                if (grid[i][j] == 1) {
                    col[j]++;
                }
            }
        }
        int single = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1 && row[i] == 1 && col[j] == 1) {
                    single++;
                }
            }
        }
        //System.out.println(all + "  " + single);
        return all - single;
    }
}