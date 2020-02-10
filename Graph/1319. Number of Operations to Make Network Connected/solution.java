// OJ: https://leetcode.com/problems/number-of-operations-to-make-network-connected/
// Author: https://leetcode.com/charlesna/
// Time: O(connections)
// Space: O(n)
class Solution {
    public int makeConnected(int n, int[][] connections) {
        int l = connections.length;
        if (l < n - 1) {
            return -1;
        }
        int[] p = new int[n];
        int count = n;
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int[] connection : connections) {
            int change = union(p, connection[0], connection[1]);
            count -= change;
        }
        return count - 1;
    }
    private int union(int[] p, int x, int y) {
        int px = find(x, p);
        int py = find(y, p);
        if (px != py) {
            p[px] = py;
            return 1;
        }
        return 0;
    }
    private int find(int x, int[] p) {
        if (p[x] == x) {
            return x;
        }
        p[x] = find(p[x], p);
        return p[x];
    }
}