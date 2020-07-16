// OJ: https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
// Author: https://leetcode.com/charlesna/
// Time: O(nlogn)
// Space: O(n)
class TreeAncestor {
    int[][] fa;
    public TreeAncestor(int n, int[] parent) {
        fa = new int[n][16];
        for (int i = 0; i < n; i++) {
            fa[i][0] = parent[i];
        }
        for (int k = 1; k < 16; k++) {
            for (int i = 0; i < n; i++) {
                fa[i][k] = fa[i][k - 1] == -1 ? -1 : fa[fa[i][k - 1]][k - 1];
            }
        }
    }
    public int getKthAncestor(int node, int k) {
        for (int i = 0; i < 16; i++) {
            if ((k >> i & 1) == 1) {
                node = fa[node][i];
                if (node == -1) {
                    return node;
                }
            }
        }
        return node;
    }
}
/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */