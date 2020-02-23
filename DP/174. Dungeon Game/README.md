# [174. Dungeon Game (Hard)](https://leetcode.com/problems/dungeon-game/)

<style type="text/css">table.dungeon, .dungeon th, .dungeon td {
  border:3px solid black;
}

 .dungeon th, .dungeon td {
    text-align: center;
    height: 70px;
    width: 70px;
}
</style>
<p>The demons had captured the princess (<strong>P</strong>) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (<strong>K</strong>) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.</p>

<p>The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.</p>

<p>Some of the rooms are guarded by demons, so the knight loses health (<em>negative</em> integers) upon entering these rooms; other rooms are either empty (<em>0's</em>) or contain magic orbs that increase the knight's health (<em>positive</em> integers).</p>

<p>In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.</p>

<p>&nbsp;</p>

<p><strong>Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.</strong></p>

<p>For example, given the dungeon below, the initial health of the knight must be at least <strong>7</strong> if he follows the optimal path <code>RIGHT-&gt; RIGHT -&gt; DOWN -&gt; DOWN</code>.</p>

<table class="dungeon">
	<tbody>
		<tr>
			<td>-2 (K)</td>
			<td>-3</td>
			<td>3</td>
		</tr>
		<tr>
			<td>-5</td>
			<td>-10</td>
			<td>1</td>
		</tr>
		<tr>
			<td>10</td>
			<td>30</td>
			<td>-5 (P)</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The knight's health has no upper bound.</li>
	<li>Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.</li>
</ul>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Binary Search](https://leetcode.com/tag/binary-search/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Unique Paths (Medium)](https://leetcode.com/problems/unique-paths/)
* [Minimum Path Sum (Medium)](https://leetcode.com/problems/minimum-path-sum/)
* [Cherry Pickup (Hard)](https://leetcode.com/problems/cherry-pickup/)

## Solution 1: Print the Path

```java
// OJ: https://leetcode.com/problems/dungeon-game/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(n)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];
        // dp[i][j] is the smallest energy before fight with demons in matrix[i][j]
        // next step will go to matrix[i + 1][j] or matrix[i][j + 1]. 
        // we will choose the one with a lower energy need. 
        // in order to have enough energy to go to next room with dp[i + 1][j]
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                dp[i][j] = i == row - 1 && j == col - 1 ? Math.max(1, -dungeon[i][j] + 1) : Integer.MAX_VALUE;
                if (i < row - 1) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(1, dp[i + 1][j] - dungeon[i][j]));
                }
                if (j < col - 1) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(1, dp[i][j + 1] - dungeon[i][j]));
                }
            }
        }
        // get the path of hero, start from the first position, go the dp[next] with smaller required energy
        int nrow = 0, ncol = 0;
        System.out.println(nrow + " " + ncol + " " + dp[nrow][ncol]);
        while (nrow != row - 1 || ncol != col - 1) {
            if (nrow + 1 == row) {
                ncol++;
            }
            else if (ncol + 1 == col) {
                nrow++;
            }
            else {
                if (dp[nrow + 1][ncol] >= dp[nrow][ncol + 1]) {
                    ncol++;
                }
                else {
                    nrow++;
                }
            }
            System.out.println(nrow + " " + ncol + " " + dp[nrow][ncol]);
        }
        return dp[0][0];
    }
}
```


## Solution 2: Optimize the Space

```java
// OJ: https://leetcode.com/problems/dungeon-game/
// Author: https://leetcode.com/charlesna/
// Time: O(m*n)
// Space: O(n)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[2][col];
        // dp[i][j] is the smallest energy before fight with demons in matrix[i][j]
        // next step will go to matrix[i + 1][j] or matrix[i][j + 1]. 
        // we will choose the one with a lower energy need. 
        // in order to have enough energy to go to next room with dp[i + 1][j]
        int now = 0, old = 1;
        for (int i = row - 1; i >= 0; i--) {
            old = now;
            now = 1 - now;
            for (int j = col - 1; j >= 0; j--) {
                dp[now][j] = i == row - 1 && j == col - 1 ? Math.max(1, -dungeon[row - 1][col - 1] + 1) : Integer.MAX_VALUE;
                if (i < row - 1) {
                    dp[now][j] = Math.min(dp[now][j], Math.max(1, dp[old][j] - dungeon[i][j]));
                }
                if (j < col - 1) {
                    dp[now][j] = Math.min(dp[now][j], Math.max(1, dp[now][j + 1] - dungeon[i][j]));
                }
            }
        }
        return dp[now][0];
    }
}
```