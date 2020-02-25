# [1289. Minimum Falling Path Sum II (Hard)](https://leetcode.com/problems/minimum-falling-path-sum-ii/)

<p>Given a square grid&nbsp;of integers&nbsp;<code>arr</code>, a <em>falling path with non-zero shifts</em>&nbsp;is a choice of&nbsp;exactly one element from each row of <code>arr</code>, such that no two elements chosen in adjacent rows are in&nbsp;the same column.</p>

<p>Return the&nbsp;minimum&nbsp;sum of a falling path with non-zero shifts.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> arr = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> 13
<strong>Explanation: </strong>
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is&nbsp;[1,5,7], so the answer is&nbsp;13.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length == arr[i].length &lt;= 200</code></li>
	<li><code>-99 &lt;= arr[i][j] &lt;= 99</code></li>
</ul>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Minimum Falling Path Sum (Medium)](https://leetcode.com/problems/minimum-falling-path-sum/)

## Solution 

```java
// OJ: https://leetcode.com/problems/minimum-falling-path-sum-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n*m)
// Space: O(1) 
class Solution {
    public int minFallingPathSum(int[][] arr) {
        //dp[i][j] represent, for the ith row, the min sum when we pick jth column
        //dp[i][j] = min(dp[i - 1][k] + arr[i][j]) for (k = [0, l] && k != j)
        int l = arr.length;
        int[] min = {-1, 0};
        int[] secondMin = {-1, 0};
        for (int i = 0; i < l; i++) {
            int[] tempMin = new int[]{-1, 10000};
            int[] tempSecondMin = new int[]{-1, 10000};
            for (int j = 0; j < l; j++) {
                int cur = j == min[0] ? secondMin[1] + arr[i][j] : min[1] + arr[i][j];
                if (cur <= tempMin[1]) {
                    tempSecondMin[0] = tempMin[0];
                    tempSecondMin[1] = tempMin[1];
                    tempMin[0] = j;
                    tempMin[1] = cur;
                }
                else if (cur < tempSecondMin[1]) {
                    tempSecondMin[0] = j;
                    tempSecondMin[1] = cur;
                }
            }
            min = tempMin;
            secondMin = tempSecondMin;
            //System.out.println(min[0] + " " + min[1] + " " + secondMin[0] + " " + secondMin[1]);
        }
        return min[1];
    }
}
```