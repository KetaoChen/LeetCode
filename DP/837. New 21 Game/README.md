# [837. New 21 Game (Medium)](https://leetcode.com/problems/new-21-game/)

<p>Alice plays the following game, loosely based on the card game "21".</p>

<p>Alice starts with <code>0</code> points, and draws numbers while she has less than <code>K</code> points.&nbsp; During each draw, she gains an integer number of points randomly from the range <code>[1, W]</code>, where <code>W</code> is an integer.&nbsp; Each draw is independent and the outcomes have equal probabilities.</p>

<p>Alice stops drawing numbers when she gets <code>K</code> or more points.&nbsp; What is the probability&nbsp;that she has <code>N</code> or less points?</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>N = 10, K = 1, W = 10
<strong>Output: </strong>1.00000
<strong>Explanation: </strong> Alice gets a single card, then stops.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong>N = 6, K = 1, W = 10
<strong>Output: </strong>0.60000
<strong>Explanation: </strong> Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input: </strong>N = 21, K = 17, W = 10
<strong>Output: </strong>0.73278</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= K &lt;= N &lt;= 10000</code></li>
	<li><code>1 &lt;= W &lt;= 10000</code></li>
	<li>Answers will be accepted as correct if they are within <code>10^-5</code> of the correct answer.</li>
	<li>The judging time limit has been reduced for this question.</li>
</ol>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 1: PrefixSum & Probability & DP

```java
// OJ: https://leetcode.com/problems/new-21-game/
// Author: https://leetcode.com/charlesna/
// Time: O(N)
// Space: O(N)
class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        double[] dp = new double[N + 1];
        dp[0] = 1.0;
        double res = 0.0, pSum = 1.0;
        // dp[i] is the sum of possibility of previous W points sum(dp[i - W], dp[i - 1]) / W
        for (int i = 1; i <= N; i++) {
            dp[i] = pSum / W;
            if (i >= K) {
                res += dp[i];
            }
            else {
                pSum += dp[i];
            }
            if (i - W >= 0) {
                pSum -= dp[i - W];
            }
        }
        return res;
    }
}
```

## Solution 2: My TLE O(N*W) method

```java
// OJ: https://leetcode.com/problems/new-21-game/
// Author: https://leetcode.com/charlesna/
// Time: O(N*W) TLE
// Space: O(N)
class Solution {
    public double new21Game(int N, int K, int W) {
		// dp[i] is the possibility to get i points.
        double[] dp = new double[N + 1];
        double res = 0.0;
        dp[0] = 1.0;
		
		
        for (int i = 0; i <= N; i++) {
            // if i >= K, we stop drawing, and add the possibility to the res.
            if (i >= K) {
                res += dp[i];
                continue;
            }
            // if the point is smaller than k, we will draw another card.
            // so the possibility of [i + 1, i + W] will increase dp[i] / W because the new drawing cards
            for (int j = 1; j <= W && i + j <= N; j++) {
                dp[i + j] += dp[i] / W;
            }
        }
        return res;
    }
}
```