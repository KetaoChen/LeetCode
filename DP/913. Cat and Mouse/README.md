# [913. Cat and Mouse (Hard)](https://leetcode.com/problems/cat-and-mouse/)

<p>A game on an <strong>undirected</strong> graph is played by two players, Mouse and Cat, who alternate turns.</p>

<p>The graph is given as follows: <code>graph[a]</code> is a list of all nodes <code>b</code> such that <code>ab</code> is an edge of the graph.</p>

<p>Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second, and there is a Hole at node 0.</p>

<p>During each player's turn, they <strong>must</strong> travel along one&nbsp;edge of the graph that meets where they are.&nbsp; For example, if the Mouse is at node <code>1</code>, it <strong>must</strong> travel to any node in <code>graph[1]</code>.</p>

<p>Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)</p>

<p>Then, the game can end in 3 ways:</p>

<ul>
	<li>If ever the Cat occupies the same node as the Mouse, the Cat wins.</li>
	<li>If ever the Mouse reaches the Hole, the Mouse wins.</li>
	<li>If ever a position is repeated (ie.&nbsp;the players are in the same position as a previous turn, and&nbsp;it is the same player's turn to move), the game is a draw.</li>
</ul>

<p>Given a <code>graph</code>, and assuming both players play optimally, return <code>1</code>&nbsp;if the game is won by Mouse, <code>2</code>&nbsp;if the game is won by Cat, and <code>0</code>&nbsp;if the game is a draw.</p>

<p>&nbsp;</p>

<ol>
</ol>

<div>
<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">[[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]</span>
<strong>Output: </strong><span id="example-output-1">0
<strong>Explanation:</strong>
</span>4---3---1
|&nbsp; &nbsp;|
2---5
&nbsp;\&nbsp;/
&nbsp; 0
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>3 &lt;= graph.length &lt;= 50</code></li>
	<li>It is guaranteed that <code>graph[1]</code> is non-empty.</li>
	<li>It is guaranteed that <code>graph[2]</code> contains a non-zero element.&nbsp;</li>
</ol>
</div>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Breadth-first Search](https://leetcode.com/tag/breadth-first-search/), [Minimax](https://leetcode.com/tag/minimax/)

## Solution 

```java
// OJ: https://leetcode.com/problems/cat-and-mouse/
// Author: https://leetcode.com/charlesna/
// Time: O(n*n)
// Space: O(n*n)
class Solution {
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        // dp[i][j][k]: 0: draw, 1: mouse win, 2: cat win.
        //          k :          1: mouse turn 2: cat turn.
        int[][][] dp = new int[n][n][3];       
        // starting from the state that we are sure about the result, then use bfs.
        Queue<int[]> q = new LinkedList<>();
        // initialize
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][i][k] = 2;
                q.offer(new int[]{i, i, k});
                dp[0][i][k] = 1;
                q.offer(new int[]{0, i, k});
            }
        }
        // do bfs
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0], j = cur[1], k = cur[2];
            //  check the prev step of mouse, is win or lose
            for (int prev : graph[i]) {
                if (dp[prev][j][1] != 0 || k == 1) continue;
                // win this round.
                if (dp[i][j][k] == 1) {
                    dp[prev][j][1] = 1;
                    q.offer(new int[]{prev, j, 1});
                }
                else {
                    boolean lose = true;
                    for (int next : graph[prev]) {
                        if (dp[next][j][2] != 2) {
                            lose = false;
                            break;
                        }
                    }
                    if (lose) {
                        dp[prev][j][1] = 2;
                        q.offer(new int[]{prev, j, 1});
                    }
                }
            }
            // check the prev step of cat
            for (int prev : graph[j]) {
                if (dp[i][prev][2] != 0 || k == 2 || prev == 0) continue;
                if (dp[i][j][k] == 2) {
                    dp[i][prev][2] = 2;
                    q.offer(new int[]{i, prev, 2});
                }
                else {
                    boolean lose = true;
                    for (int next : graph[prev]) {
                        if (next == 0) continue;
                        if (dp[i][next][1] != 1) {
                            lose = false;
                            break;
                        }
                    }
                    if (lose) {
                        dp[i][prev][2] = 1;
                        q.offer(new int[]{i, prev, 2});
                    }
                }
            }
        }
        return dp[1][2][1];
    }
}
```