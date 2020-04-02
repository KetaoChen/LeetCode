# [526. Beautiful Arrangement (Medium)](https://leetcode.com/problems/beautiful-arrangement/)

<p>Suppose you have <b>N</b> integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these <b>N</b> numbers successfully if one of the following is true for the i<sub>th</sub> position (1 &lt;= i &lt;= N) in this array:</p>

<ol>
	<li>The number at the i<sub>th</sub> position is divisible by <b>i</b>.</li>
	<li><b>i</b> is divisible by the number at the i<sub>th</sub> position.</li>
</ol>

<p>&nbsp;</p>

<p>Now given N, how many beautiful arrangements can you construct?</p>

<p><b>Example 1:</b></p>

<pre><b>Input:</b> 2
<b>Output:</b> 2
<b>Explanation:</b> 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li><b>N</b> is a positive integer and will not exceed 15.</li>
</ol>

<p>&nbsp;</p>


**Companies**:  
[Mathworks](https://leetcode.com/company/mathworks)

**Related Topics**:  
[Backtracking](https://leetcode.com/tag/backtracking/)

**Similar Questions**:
* [Beautiful Arrangement II (Medium)](https://leetcode.com/problems/beautiful-arrangement-ii/)

## Solution 1: BitMask

```java
// OJ: https://leetcode.com/problems/beautiful-arrangement/
// Author: https://leetcode.com/charlesna/
// Time: O(2^n)
// Space: O(2^n)
class Solution {
    public int countArrangement(int N) {
        int[] dp = new int[1 << N];
        dp[0] = 1;
        for (int i = 0; i < (1 << N); i++) {
            int c = Integer.bitCount(i) + 1;
            for (int k = 1; k <= N; k++) {
                if ((i & (1 << (k - 1))) == 0 && (c % k == 0 || k % c == 0)) {
                    dp[i ^ (1 << (k - 1))] += dp[i];
                }
            }
        }
        return dp[(1 << N) - 1];
    }
}
```


## Solution 2: Backtrack
```java
// O(n!)

class Solution {
    int res;
    public int countArrangement(int N) {
        res = 0;
        helper(1, new boolean[N + 1]);
        return res;
    }
    
    private void helper(int cur, boolean[] used) {
        if (cur == used.length) {
            res++;
            return;
        }
        
        for (int i = 1; i < used.length; i++) {
            if ((i % cur == 0 || cur % i == 0) && !used[i]) {
                used[i] = true;
                helper(cur + 1, used);
                used[i] = false;
            }
        }
    }
}
```