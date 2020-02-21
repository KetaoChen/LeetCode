// OJ: https://leetcode.com/problems/count-square-submatrices-with-all-ones/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(1)
class Solution {
    public int countSquares(int[][] A) {
        int res = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                if (A[i][j] > 0 && i > 0 && j > 0) {
                    int l = Math.min(A[i - 1][j], A[i][j - 1]);
                    A[i][j] = A[i - l][j - l] > 0 ? l + 1 : l;
                }
                res += A[i][j];
            }
        }
        return res;
    }
}