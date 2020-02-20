# [221. Maximal Square (Medium)](https://leetcode.com/problems/maximal-square/)

<p>Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.</p>

<p><strong>Example:</strong></p>

<pre><strong>Input: 
</strong>
1 0 1 0 0
1 0 <font color="red">1</font> <font color="red">1</font> 1
1 1 <font color="red">1</font> <font color="red">1</font> 1
1 0 0 1 0

<strong>Output: </strong>4
</pre>

**Companies**:  
[Google](https://leetcode.com/company/google), [Amazon](https://leetcode.com/company/amazon), [Uber](https://leetcode.com/company/uber), [Huawei](https://leetcode.com/company/huawei), [VMware](https://leetcode.com/company/vmware), [Facebook](https://leetcode.com/company/facebook), [Bloomberg](https://leetcode.com/company/bloomberg), [Adobe](https://leetcode.com/company/adobe)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Maximal Rectangle (Hard)](https://leetcode.com/problems/maximal-rectangle/)
* [Largest Plus Sign (Medium)](https://leetcode.com/problems/largest-plus-sign/)

## Solution 1: Rolling Array to optimize space

```java
// OJ: https://leetcode.com/problems/maximal-square/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(n)
class Solution {
    public int maximalSquare(char[][] matrix) {
        //dp[i][j] represent the length of largest square, when i,j is the right bottom corner
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int col = matrix[0].length;
        int[][] dp = new int[2][col + 1];
        int res = 0;
        int prev = 1;
        int now = 0;
        for (int i = 1; i <= row; i++) {
            prev = now;
            now = 1 - now;
            for (int j = 1; j <= col; j++) {
                char val = matrix[i - 1][j - 1];
                if (val == '0') {
                    dp[now][j] = 0;
                    continue;
                }
                dp[now][j] = Math.min(dp[prev][j - 1], Math.min(dp[prev][j], dp[now][j - 1])) + 1;
                res = Math.max(res, dp[now][j]);
            }
        }
        return res * res;
    }
}

```

## Solution 2: General DP

```java
// OJ: https://leetcode.com/problems/maximal-square/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(m*n)
class Solution {
    public int maximalSquare(char[][] matrix) {
        //dp[i][j] represent the length of largest square, when i,j is the right bottom corner
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];
        int res = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                char val = matrix[i - 1][j - 1];
                if (val == '0') {
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res * res;
    }
}
```