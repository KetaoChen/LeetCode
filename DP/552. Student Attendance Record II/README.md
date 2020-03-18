# [552. Student Attendance Record II (Hard)](https://leetcode.com/problems/student-attendance-record-ii/)

<p>Given a positive integer <b>n</b>, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 10<sup>9</sup> + 7.</p>

<p>A student attendance record is a string that only contains the following three characters:</p>

<p>
</p><ol>
<li><b>'A'</b> : Absent. </li>
<li><b>'L'</b> : Late.</li>
<li> <b>'P'</b> : Present. </li>
</ol>
<p></p>

<p>
A record is regarded as rewardable if it doesn't contain <b>more than one 'A' (absent)</b> or <b>more than two continuous 'L' (late)</b>.</p>

<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> n = 2
<b>Output:</b> 8 
<b>Explanation:</b>
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 
</pre>
<p></p>

<p><b>Note:</b>
The value of <b>n</b> won't exceed 100,000.
</p>




**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Student Attendance Record I (Easy)](https://leetcode.com/problems/student-attendance-record-i/)

## Solution 

```java
// OJ: https://leetcode.com/problems/student-attendance-record-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int checkRecord(int n) {
        int mod = (int) (1e9 + 7);
        long[][][] dp = new long[2][4][2];
        int now = 1, old = 0;
        dp[now][0][0] = 1;
        // dp[i] is at ith pos, the total number of possibilities.
        // dp[i][0] cur state = present.
        // dp[i][1] cur state = 1 late
        // dp[i][2] cur state = 2 lates
        // dp[i][3] cur state = absent
        // dp[i][j][0] no absent.
        // dp[i][j][1] 1 absent.
        for (int i = 1; i <= n; i++) {
            now = old;
            old = 1 - old;
            dp[now][0][0] = (dp[old][0][0] + dp[old][1][0] + dp[old][2][0]) % mod;
            dp[now][1][0] = dp[old][0][0];
            dp[now][2][0] = dp[old][1][0];
            dp[now][3][1] = dp[now][0][0];
            dp[now][0][1] = (dp[old][0][1] + dp[old][1][1] + dp[old][2][1] + dp[old][3][1]) % mod;
            dp[now][1][1] = (dp[old][0][1] + dp[old][3][1]) % mod;
            dp[now][2][1] = dp[old][1][1];
        }
        return (int) ((dp[now][0][0] + dp[now][1][0] + dp[now][2][0] + dp[now][3][1] + dp[now][0][1] + dp[now][1][1] + dp[now][2][1]) % mod);
    }
}
```