# [54. Spiral Matrix (Medium)](https://leetcode.com/problems/spiral-matrix/)

<p>Given a matrix of <em>m</em> x <em>n</em> elements (<em>m</em> rows, <em>n</em> columns), return all elements of the matrix in spiral order.</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong>
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
<strong>Output:</strong> [1,2,3,6,9,8,7,4,5]
</pre>

<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong>
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
<strong>Output:</strong> [1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Microsoft](https://leetcode.com/company/microsoft), [Oracle](https://leetcode.com/company/oracle), [Goldman Sachs](https://leetcode.com/company/goldman-sachs), [Apple](https://leetcode.com/company/apple), [Google](https://leetcode.com/company/google), [Visa](https://leetcode.com/company/visa), [Epic Systems](https://leetcode.com/company/epic-systems), [Adobe](https://leetcode.com/company/adobe), [Facebook](https://leetcode.com/company/facebook), [Snapchat](https://leetcode.com/company/snapchat)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/)

**Similar Questions**:
* [Spiral Matrix II (Medium)](https://leetcode.com/problems/spiral-matrix-ii/)
* [Spiral Matrix III (Medium)](https://leetcode.com/problems/spiral-matrix-iii/)

## Solution 1: Rotate the Direction

```java
// OJ: https://leetcode.com/problems/spiral-matrix/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(m*n)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        int d = 1, x = 0, y = 0, row = matrix.length, col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int k = 0; k < row * col; k++) {
            res.add(matrix[x][y]);
            visited[x][y] = true;
            int nx = x + dx[d], ny = y + dy[d];
            if (nx < 0 || nx >= row || ny < 0 || ny >= col || visited[nx][ny]) {
                d = (d + 1) % 4;
            }
            x += dx[d];
            y += dy[d];
        }
        return res;
    }
}
```

## Solution 2: Four While Loop Simulation

```java
// OJ: https://leetcode.com/problems/spiral-matrix/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(1)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = matrix.length - 1;
        int colEnd = matrix[0].length - 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            //add the row from left to right
            for (int i = colStart; i <= colEnd; i++) {
                res.add(matrix[rowStart][i]);
            }
            rowStart++;
            //add the col from top to bottom
            for (int i = rowStart; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            //add the row from right to left
            if (rowEnd >= rowStart) {
                for (int i = colEnd; i >= colStart; i--) {
                    res.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }
            //add the col from bottom to top
            if (colEnd >= colStart) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    res.add(matrix[i][colStart]);
                }
                colStart++;
            }            
        }
        return res;
    }
}
```