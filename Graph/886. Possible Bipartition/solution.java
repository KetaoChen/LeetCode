// OJ: https://leetcode.com/problems/possible-bipartition/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] st = new int[N + 1];
        List<Integer>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dis : dislikes) {
            graph[dis[0]].add(dis[1]);
            graph[dis[1]].add(dis[0]);
        }
        for (int i = 1; i <= N; i++) {
            if (st[i] != 0) continue;
            st[i] = 1;
            if (!valid(graph, st, i, 1)) return false;
        }
        return true;
    }
    private boolean valid(List<Integer>[] graph, int[] st, int index, int cur) {
        for (int next : graph[index]) {
            if (st[next] == cur) return false;
            if (st[next] == 3 - cur) continue;
            st[next] = 3 - cur;
            valid(graph, st, next, 3 - cur);
        }
        return true;
    }
}