# [1397. Find All Good Strings (Hard)](https://leetcode.com/problems/find-all-good-strings/)

<p>Given the strings <code>s1</code> and <code>s2</code> of size <code>n</code>, and the string <code>evil</code>. <em>Return the number of <strong>good</strong> strings</em>.</p>

<p>A <strong>good</strong> string has size <code>n</code>, it is alphabetically greater than or equal to <code>s1</code>, it is alphabetically smaller than or equal to <code>s2</code>, and it does not contain the string <code>evil</code> as a substring. Since the answer can be a huge number, return this modulo 10^9 + 7.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> n = 2, s1 = "aa", s2 = "da", evil = "b"
<strong>Output:</strong> 51 
<strong>Explanation:</strong> There are 25 good strings starting with 'a': "aa","ac","ad",...,"az". Then there are 25 good strings starting with 'c': "ca","cc","cd",...,"cz" and finally there is one good string starting with 'd': "da".&nbsp;
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
<strong>Output:</strong> 0 
<strong>Explanation:</strong> All strings greater than or equal to s1 and smaller than or equal to s2 start with the prefix "leet", therefore, there is not any good string.
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> n = 2, s1 = "gx", s2 = "gz", evil = "x"
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s1.length == n</code></li>
	<li><code>s2.length == n</code></li>
	<li><code>s1 &lt;= s2</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= evil.length &lt;= 50</code></li>
	<li>All strings consist of lowercase English letters.</li>
</ul>


**Companies**:  
[dunzo](https://leetcode.com/company/dunzo)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 1: Yxc DP

```java
// OJ: https://leetcode.com/problems/find-all-good-strings/
// Author: https://leetcode.com/charlesna/
// Time: O(n*m*26)
// Space: O(n*m)
class Solution {
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        int mod = (int) (1e9 + 7);
        // return (int)helper(s2, evil);
        long res = ((helper(s2, evil) - helper(s1, evil)) % mod + mod) % mod;
        if (s1.indexOf(evil) < 0) res++;
        return (int)res;
    }
    private long helper(String s, String e) {
        int ls = s.length(), le = e.length(), mod = (int) (1e9 + 7);
        int[] next = new int[le + 1];
        for (int i = 2, j = 0; i <= le; i++) {
            while (j != 0 && e.charAt(i - 1) != e.charAt(j)) j = next[j];
            if (e.charAt(i - 1) == e.charAt(j)) j++;
            next[i] = j;
        }
        // System.out.println(ls + " " + le);
        long[][] dp = new long[ls + 1][le + 1];
        // dp[i][j] is the number of length i, and starting with match number j.
        long res = 0;
        int match = 0;
        for (int i = 0; i < ls; i++) {
            int val = s.charAt(i) - 'a';
            for (int k = 0; k < val; k++) {
                int cur = match;
                while (cur != 0 && e.charAt(cur) - 'a' != k) cur = next[cur];
                if (e.charAt(cur) - 'a' == k) cur++;
                res = (res + f(ls - i - 1, cur, e, dp, next)) % mod;
            }
            while (match != 0 && s.charAt(i) != e.charAt(match)) match = next[match];
            if (s.charAt(i) == e.charAt(match)) match++;
            if (match == le) return res;
        }
        return res + 1;
    }
    private long f(int m, int n, String e, long[][] dp, int[] next) {
        int le = e.length(), mod = (int) (1e9 + 7);
        if (n == le) return 0;
        if (m == 0) return 1;
        // System.out.println(m + " " + n + " " + le);
        if (dp[m][n] != 0) return dp[m][n];
        long res = 0;
        for (int i = 0; i < 26; i++) {
            int cur = n;
            while (cur != 0 && e.charAt(cur) - 'a' != i) cur = next[cur];
            if (e.charAt(cur) - 'a' == i) cur++;
            res = (res + f(m - 1, cur, e, dp, next)) % mod;
        }
        dp[m][n] = res;
        return res;
    }
}
```

## Solution 2: 01 DP

```java

// OJ: https://leetcode.com/problems/find-all-good-strings/
// Author: https://leetcode.com/charlesna/
// Time: O(n * m * 26)
// Space: O(n * m)
class Solution {
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        int mod = (int) (1e9 + 7);
        // return (int)helper(s2, evil);
        int res = ((helper(s2, evil) - helper(s1, evil)) % mod + mod) % mod;
        if (s1.indexOf(evil) < 0) res++;
        return (int)res;
    }
    private int helper(String s, String e) {
        int ls = s.length(), le = e.length(), mod = (int) (1e9 + 7);
        int[] next = new int[le + 1];
        for (int i = 2, j = 0; i <= le; i++) {
            while (j != 0 && e.charAt(i - 1) != e.charAt(j)) j = next[j];
            if (e.charAt(i - 1) == e.charAt(j)) j++;
            next[i] = j;
        }
        // dp[i][j][] is the number of string start i, and starting with match number j. 
        // the string is also smaller than s
        int[][][] dp = new int[ls][le + 1][2];
        return f(s, e, 0, 0, 1, dp, next);
    }
    private int f(String s, String e, int index, int match, int bound, int[][][] dp, int[] next) {
        int ls = s.length(), le = e.length(), mod = (int) (1e9 + 7);
        if (match == le) return 0;
        if (index == ls) return 1;
        if (dp[index][match][bound] != 0) return dp[index][match][bound];
        int res = 0, val = s.charAt(index) - 'a';
        for (int k = 0; k < 26; k++) {
            if (bound == 1 && k > val) break;
            int cm = match;
            while (cm != 0 && e.charAt(cm) - 'a' != k) cm = next[cm];
            if (e.charAt(cm) - 'a' == k) cm++;
            boolean b = bound == 1 && k == val;
            res = (res + f(s, e, index + 1, cm, b ? 1 : 0, dp, next)) % mod;
        }
        dp[index][match][bound] = res;
        return (int) res;
    }
}
```