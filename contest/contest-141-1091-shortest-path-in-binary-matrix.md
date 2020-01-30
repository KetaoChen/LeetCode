---
tags:
  - leetcode
  - breadth-first search
created: '2019/6/19 下午6:45:23'
difficulty: medium
---

# 1091-shortest-path-in-binary-matrix

## Problem

In an N by N square grid, each cell is either empty \(0\) or blocked \(1\).  
  


A _clear path from top-left to bottom-right_ has length `k` if and only if it is composed of cells `C_1, C_2, ..., C_k` such that:  
  


* * Adjacent cells `C_i` and `C_{i+1}` are connected 8-directionally \(ie., they are different and share an edge or corner\)
* * `C_1` is at location `(0, 0)` \(ie. has value `grid[0][0]`\)
* * `C_k` is at location `(N-1, N-1)` \(ie. has value `grid[N-1][N-1]`\)
* * If `C_i` is located at `(r, c)`, then `grid[r][c]` is empty \(ie. `grid[r][c] == 0`\).
* 
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.  
  


**Example 1:**  
  


```text
Input: [[0,1],[1,0]]
Output: 2
```

**Example 2:**  
  


```text
Input: [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
```

**Note:**  
  


1. 2. `1 <= grid.length == grid[0].length <= 100`
3. 4. `grid[r][c]` is `0` or `1`
5. 
## Solution

java

```java
class Solution {
    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        if (grid[0][0] == 1 || grid[row - 1][col - 1] == 1) {
            return -1;
        }
        boolean[][] visited = new boolean[row][col];
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));
        visited[0][0] = true;

        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            step++;
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();
                int x = cur.x;
                int y = cur.y;
                if (x == row - 1 && y == col - 1) {
                    return step;
                }
                for (int d = 0; d < 8; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col && !visited[nx][ny]) {
                        if (grid[nx][ny] == 0) {
                            q.offer(new Point(nx, ny));
                        }
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}
​
```

