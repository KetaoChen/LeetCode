# [943. Find the Shortest Superstring (Hard)](https://leetcode.com/problems/find-the-shortest-superstring/)

<p>Given an array A of strings, find any&nbsp;smallest string that contains each string in <code>A</code> as a&nbsp;substring.</p>

<p>We may assume that no string in <code>A</code> is substring of another string in <code>A</code>.</p>

<div>&nbsp;</div>

<div>
<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">["alex","loves","leetcode"]</span>
<strong>Output: </strong><span id="example-output-1">"alexlovesleetcode"</span>
<strong>Explanation: </strong>All permutations of "alex","loves","leetcode" would also be accepted.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong><span id="example-input-2-1">["catg","ctaagt","gcta","ttca","atgcatc"]</span>
<strong>Output: </strong><span id="example-output-2">"gctaagttcatgcatc"</span></pre>

<p>&nbsp;</p>
</div>
</div>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 12</code></li>
	<li><code>1 &lt;= A[i].length &lt;= 20</code></li>
</ol>

<div>
<div>&nbsp;</div>
</div>

**Companies**:  
[Google](https://leetcode.com/company/google), [Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 

```java
// OJ: https://leetcode.com/problems/find-the-shortest-superstring/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public String shortestSuperstring(String[] A) {
        // tsp + bitmask
        // dp[i][j]: i is the bit mask of all visited nodes
        // j is the last node, dp[i][j] is its min length 
        int[][] costs = cal(A);
        int l = A.length;
        int[][] dp = new int[1 << l][l];
        int[][] pi = new int[1 << l][l];
        for (int i = 0; i < (1 << l); i++) {
            for (int j = 0; j < l; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                pi[i][j] = -1;
            }
        }
        for (int i = 0; i < l; i++) {
            dp[1 << i][i] = A[i].length();
            pi[1 << i][i] = -1;
        }
        for (int i = 0; i < (1 << l); i++) {
            for (int k = 0; k < l; k++) {
                // if this is an available state
                if (dp[i][k] != Integer.MAX_VALUE) {
                    // update the next state
                    for (int next = 0; next < l; next++) {
                        // if next node has been visited, continue;
                        if ((1 & (i >> next)) == 1) {
                            continue;
                        }
                        if (dp[i][k] + costs[k][next] < dp[i ^ (1 << next)][next]) {
                            dp[i ^ (1 << next)][next] = dp[i][k] + costs[k][next];
                            pi[i ^ (1 << next)][next] = k;
                        }
                    }    
                }
            }
        }
        int[] s = new int[l];
        int index = l - 1, cur = (1 << l) - 1;
        int min = Integer.MAX_VALUE, node = -1;
        for (int i = 0; i < l; i++) {
            if (dp[cur][i] < min) {
                min = dp[cur][i];
                node = i;
            }
        }       
        s[index--] = node;
        while (index >= 0) {
            // System.out.println(cur + " " + s[index + 1]);
            s[index] = pi[cur][s[index + 1]];
            cur = (cur ^ (1 << s[index + 1]));
            index--;
            // System.out.println(s[index]);
        }
        StringBuilder sb = new StringBuilder(A[s[0]]);
        // System.out.println(s[0] + " " + A[s[0]]);
        for (int i = 1; i < l; i++) {
            // System.out.println(s[i] + " " + A[s[i]].substring(A[s[i]].length() - costs[s[i - 1]][s[i]]));
            sb.append(A[s[i]].substring(A[s[i]].length() - costs[s[i - 1]][s[i]]));
        }
        return sb.toString();
    }
    private int[][] cal(String[] s) {
        int l = s.length;
        int[][] res = new int[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                res[i][j] = getCommon(s[i], s[j]);
                // System.out.print(res[i][j] + " ");
            }
            // System.out.println();
        }
        return res;
    }
    // calculate the longest suffix in s1 is the same as prefix in s2
    private int getCommon(String s1, String s2) {
        int res = 0;
        for (int l = 1; l <= Math.min(s1.length(), s2.length()); l++) {
            if (s1.substring(s1.length() - l).equals(s2.substring(0, l))) {
                res = l;
            }
        }
        return s2.length() - res;
    }
}
```