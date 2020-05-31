// OJ: https://leetcode.com/problems/course-schedule-iv/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3)
// Space: O(n^2)
class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] pre, int[][] queries) {
        boolean[][] f = new boolean[n][n];
        for (int[] p : pre) {
            f[p[0]][p[1]] = true;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (f[i][k] && f[k][j]) {
                        f[i][j] = true;
                    }
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(f[q[0]][q[1]]);
        }
        return res;
    }
}

// OJ: https://leetcode.com/problems/course-schedule-iv/
// Author: https://leetcode.com/charlesna/
// Time: O(n*Q)
// Space: O(E)
class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] pre, int[][] que) {
        List<Boolean> res = new ArrayList<>();
        List<Integer>[] list = new List[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] p : pre) {
            list[p[0]].add(p[1]);
        }
        for (int[] qu : que) {
            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[n];
            q.offer(qu[0]);
            visited[qu[0]] = true;
            boolean is = false;
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == qu[1]) {
                    is = true;
                    break;
                }
                for (int next : list[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            res.add(is);
        }
        return res;
    }
}