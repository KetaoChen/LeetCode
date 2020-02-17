// OJ: https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    class UnionFind{
        private int[] parent;
        private int count;
        public UnionFind(int l) {
            parent = new int[l];
            for (int i = 0; i < l; i++) {
                parent[i] = i;
            }
            count = l;
        }
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                parent[parentX] = parentY;
                count--;
            }
        }
        public int getNum() {
            return count;
        }
    }
    public int removeStones(int[][] stones) {
        int l = stones.length;
        UnionFind uf = new UnionFind(l);
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        for (int i = 0; i < l; i++) {
            int[] stone = stones[i];
            int r = stone[0];
            int c = stone[1];
            if (row.containsKey(r)) {
                uf.union(i, row.get(r));
            }
            else {
                row.put(r, i);
            }
            if (col.containsKey(c)) {
                uf.union(i, col.get(c));
            }
            else {
                col.put(c, i);
            }
        }
        return l - uf.getNum();
    }
}