# [1335. Minimum Difficulty of a Job Schedule (Hard)](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/)

<p>You want to schedule a list of jobs in <code>d</code> days. Jobs are dependent (i.e To work on the <code>i-th</code> job, you have to finish all the jobs <code>j</code> where <code>0 &lt;= j &lt; i</code>).</p>

<p>You have to finish <strong>at least</strong> one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the <code>d</code> days. The difficulty of a day is the maximum difficulty of a job done in that day.</p>

<p>Given an array of integers <code>jobDifficulty</code> and an integer <code>d</code>. The difficulty of the <code>i-th</code>&nbsp;job is&nbsp;<code>jobDifficulty[i]</code>.</p>

<p>Return <em>the minimum difficulty</em> of a job schedule. If you cannot find a schedule for the jobs return <strong>-1</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/01/16/untitled.png" style="width: 365px; height: 230px;">
<pre><strong>Input:</strong> jobDifficulty = [6,5,4,3,2,1], d = 2
<strong>Output:</strong> 7
<strong>Explanation:</strong> First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7 
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> jobDifficulty = [9,9,9], d = 4
<strong>Output:</strong> -1
<strong>Explanation:</strong> If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> jobDifficulty = [1,1,1], d = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> The schedule is one job per day. total difficulty will be 3.
</pre>

<p><strong>Example 4:</strong></p>

<pre><strong>Input:</strong> jobDifficulty = [7,1,7,1,7,1], d = 3
<strong>Output:</strong> 15
</pre>

<p><strong>Example 5:</strong></p>

<pre><strong>Input:</strong> jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
<strong>Output:</strong> 843
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= jobDifficulty.length &lt;= 300</code></li>
	<li><code>0 &lt;=&nbsp;jobDifficulty[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= d &lt;= 10</code></li>
</ul>

**Companies**:  
[Turvo](https://leetcode.com/company/turvo)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 1: DP O(n*d)

```java
// OJ: https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int l = jobDifficulty.length;
        if (l < d) {
            return -1;
        }
        int[][] dp = new int[2][l];
        int now = 0, old = 1;
        dp[now][0] = jobDifficulty[0];
        for (int i = 1; i < l; i++) {
            dp[now][i] = Math.max(dp[now][i - 1], jobDifficulty[i]);
        }
        // System.out.println("good");
		// every day, we update our dp[i], means the min cost to finish first ith job.
        for (int day = 1; day < d; day++) {
            Stack<Integer> stack = new Stack<>();
            old = now;
            now = 1 - now;
			// for each job, if this job is less difficult
				// if it merge to the previous one, it will not increase the dp[i] = dp[i - 1].
				// if it does not merge, dp[i] = dp[old][i - 1] + diff[i]. we need to choose the min.
			// if this job is more difficult. 
				// we want to use this job to merge previous jobs which is less difficult than it.
			// that's the reason we want to use a descending monotonic stack to keep the order.
            for (int i = day; i < l; i++) {
                dp[now][i] = dp[old][i - 1] + jobDifficulty[i];
                while (!stack.isEmpty() && jobDifficulty[stack.peek()] <= jobDifficulty[i]) {
                    int index = stack.pop();
                    dp[now][i] = Math.min(dp[now][i], dp[now][index] + jobDifficulty[i] - jobDifficulty[index]);
                }
                if (!stack.isEmpty()) {
                    dp[now][i] = Math.min(dp[now][i], dp[now][stack.peek()]);
                }
                stack.push(i);
            }
        }
        return dp[now][l - 1];
    }
}
```

## Solution 2: DP O(n*n*d)

```java
// OJ: https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
// Author: https://leetcode.com/charlesna/
// Time: O(n*n*d)
// Space: O(n*d)
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int l = jobDifficulty.length;
        if (l < d) {
            return -1;
        }
        // dp[i][j] means the total diff for first ith job in j days.
        int[][] dp = new int[l + 1][d + 1];
        int[][] max = new int[l][l];
        for (int len = 1; len <= l; len++) {
            for (int i = 0; i + len <= l; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    max[i][j] = jobDifficulty[i];
                    continue;
                }
                max[i][j] = Math.max(max[i + 1][j], max[i][j - 1]);
            }
        }
        for (int i = 1; i <= l; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= l; i++) {
            for (int k = 1; k <= Math.min(i, d); k++) {
                if (k == 1) {
                    dp[i][k] = Math.max(dp[i - 1][k], jobDifficulty[i - 1]);
                    continue;
                }
                for (int j = k - 1; j < i; j++) {
                    dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + max[j][i - 1]);
                }
            }
        }
        return dp[l][d];
    }
}
```