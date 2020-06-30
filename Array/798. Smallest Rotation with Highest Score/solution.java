// OJ: https://leetcode.com/problems/smallest-rotation-with-highest-score/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int bestRotation(int[] A) {
        // 0. because the good/bad index are continuous.
        // 1. for each number, find the start index to make it (un)count.
        // 2. iterate the array, find the max prefixSum index
        int l = A.length;
        int[] index = new int[l + 1];
        for (int i = 0; i < l; i++) {
            if (A[i] == 0) continue;
            // turning to good. go to the end of the index.
            index[i + 1]++;
            // turning to bad. target = A[i] - 1.
            int turn = (i - (A[i] - 1) + l) % l;
            if (turn == 0) turn = l;
            index[turn]--;
        }
        int res = 0, sum = 0, max = 0;
        for (int i = 0; i <= l; i++) {
            // System.out.print(index[i] + " ");
            sum += index[i];
            if (sum > max) {
                max = sum;
                res = i;
            }
        }
        return res;
    }
}