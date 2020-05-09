// OJ: https://leetcode.com/problems/image-overlap/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3)
// Space: O(n)
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int row = A.length, col = A[0].length;
        int[] a = new int[row], b = new int[row];
        for (int i = 0; i < row; i++) {
            int sa = 0, sb = 0;
            for (int j = 0; j < col; j++) {
                sa = sa << 1 | A[i][j];
                sb = sb << 1 | B[i][j];
            }
            a[i] = sa;
            b[i] = sb;
            // System.out.println(i + " " + a[i] + " "+ b[i]);
        }
        int res = 0;
        // iterate the starting point of A.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int tempA = 0, tempB = 0;
                // calculate the overlap of each row
                for (int k = 0; k + i < row; k++) {
                    tempA += Integer.bitCount(a[k] >> j & b[k + i]);
                    tempB += Integer.bitCount(b[k] >> j & a[k + i]);
                    // System.out.println(i + " " + j + " " + temp);
                }
                res = Math.max(res, Math.max(tempA, tempB));
            }
        }
        return res;
    }
}