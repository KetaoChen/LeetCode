// OJ: https://leetcode.com/problems/number-of-islands-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int l = m * n, size = 0;
        int[] p = new int[l];
        for (int i = 0; i < l; i++) {
            p[i] = i;
        }
        List<Integer> res = new ArrayList<>();
        int num = 0;
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        Set<Integer> set = new HashSet<>();
        for (int[] add : positions) {
            int x = add[0], y = add[1], cur = x * n + y;
            if (set.contains(cur)) {
                res.add(num);
                continue;
            } 
            set.add(cur);
            int connected = 0;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d], next = nx * n + ny;
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && set.contains(next)) {
                    int pc = find(p, cur);
                    int pn = find(p, next);
                    if (pc != pn) {
                        connected++;
                        p[pn] = pc;
                    }
                }
            }
            num += 1 - connected;
            res.add(num);
        }
        return res;
    }
    private int find(int[] p, int x) {
        if (p[x] == x) return x;
        p[x] = find(p, p[x]);
        return p[x];
    }
}