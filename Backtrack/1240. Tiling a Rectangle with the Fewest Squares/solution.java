// OJ: https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/
// Author: https://leetcode.com/charlesna/
// Time: O(n!)
// Space: O(n)
class Solution {
    int res;
    public int tilingRectangle(int n, int m) {
        res = n * m;
        int[] base = new int[n];
        helper(base, n, m, 0);
        return res;
    }
    private void helper(int[] base, int n, int m, int k) {
        // prune. 
        if (k >= res) return;
        // find the left-bottom cell to put square.
        int min = m, index = -1;
        for (int i = 0; i < n; i++) {
            if (base[i] < min) {
                min = base[i];
                index = i;
            }
        }
        // when it is full.
        if (min == m) {
            res = Math.min(res, k);
            return;
        }
        // choosing from the large to small.
        for (int i = Math.min(n - index, m - base[index]); i >= 1; i--) {
            int[] next = base.clone();
            boolean valid = true;
            for (int j = 0; j < i; j++) {
                next[index + j] += i;
                if (next[index + j] > m) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                helper(next, n, m, k + 1);    
            }
        }
    }
}