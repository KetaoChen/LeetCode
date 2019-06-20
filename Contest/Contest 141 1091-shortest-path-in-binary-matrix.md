---
tags: ["leetcode","breadth-first search"]
created: "2019/6/19 下午6:45:23"
difficulty: "medium"
---

# [1091-shortest-path-in-binary-matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)

## Problem
<div><p>In an N by N square grid, each cell is either empty (0) or blocked (1).</p><br><br><p>A&nbsp;<em>clear&nbsp;path from top-left to bottom-right</em>&nbsp;has length <code>k</code> if and only if it is composed of cells <code>C_1, C_2, ..., C_k</code>&nbsp;such that:</p><br><br><ul><br>	<li>Adjacent cells <code>C_i</code> and <code>C_{i+1}</code> are connected 8-directionally (ie., they are different and&nbsp;share an edge or corner)</li><br>	<li><code>C_1</code> is at location <code>(0, 0)</code> (ie. has value <code>grid[0][0]</code>)</li><br>	<li><code>C_k</code>&nbsp;is at location <code>(N-1, N-1)</code> (ie. has value <code>grid[N-1][N-1]</code>)</li><br>	<li>If <code>C_i</code> is located at&nbsp;<code>(r, c)</code>, then <code>grid[r][c]</code> is empty (ie.&nbsp;<code>grid[r][c] ==&nbsp;0</code>).</li><br></ul><br><br><p>Return the length of the shortest such clear path from top-left to bottom-right.&nbsp; If such a path does not exist, return -1.</p><br><br><p>&nbsp;</p><br><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-1-1">[[0,1],[1,0]]</span><br><strong>Output: </strong>2<br></pre><br><br><div><br><p><strong>Example 2:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-2-1">[[0,0,0],[1,1,0],[1,1,0]]</span><br><strong>Output:</strong> 4</pre><br><br><p>&nbsp;</p><br></div><br><br><p><strong>Note:</strong></p><br><br><ol><br>	<li><code>1 &lt;= grid.length == grid[0].length &lt;= 100</code></li><br>	<li><code>grid[r][c]</code> is <code>0</code> or <code>1</code></li><br></ol></div>

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
