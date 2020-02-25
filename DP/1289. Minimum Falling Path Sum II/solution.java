// OJ: https://leetcode.com/problems/minimum-falling-path-sum-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n*m)
// Space: O(1) 
class Solution {
    public int minFallingPathSum(int[][] arr) {
        //dp[i][j] represent, for the ith row, the min sum when we pick jth column
        //dp[i][j] = min(dp[i - 1][k] + arr[i][j]) for (k = [0, l] && k != j)
        int l = arr.length;
        int[] min = {-1, 0};
        int[] secondMin = {-1, 0};
        for (int i = 0; i < l; i++) {
            int[] tempMin = new int[]{-1, 10000};
            int[] tempSecondMin = new int[]{-1, 10000};
            for (int j = 0; j < l; j++) {
                int cur = j == min[0] ? secondMin[1] + arr[i][j] : min[1] + arr[i][j];
                if (cur <= tempMin[1]) {
                    tempSecondMin[0] = tempMin[0];
                    tempSecondMin[1] = tempMin[1];
                    tempMin[0] = j;
                    tempMin[1] = cur;
                }
                else if (cur < tempSecondMin[1]) {
                    tempSecondMin[0] = j;
                    tempSecondMin[1] = cur;
                }
            }
            min = tempMin;
            secondMin = tempSecondMin;
            //System.out.println(min[0] + " " + min[1] + " " + secondMin[0] + " " + secondMin[1]);
        }
        return min[1];
    }
}