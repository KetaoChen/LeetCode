# [1062. Longest Repeating Substring (Medium)](https://leetcode.com/problems/longest-repeating-substring/)

<p>Given a string <code>S</code>, find out the length of the longest repeating substring(s). Return <code>0</code> if no repeating substring exists.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">"abcd"</span>
<strong>Output: </strong><span id="example-output-1">0</span>
<strong>Explanation: </strong>There is no repeating substring.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong><span id="example-input-2-1">"abbaba"</span>
<strong>Output: </strong><span id="example-output-2">2</span>
<strong>Explanation: </strong>The longest repeating substrings are "ab" and "ba", each of which occurs twice.
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input: </strong><span id="example-input-3-1">"aabcaabdaab"</span>
<strong>Output: </strong><span id="example-output-3">3</span>
<strong>Explanation: </strong>The longest repeating substring is "aab", which occurs <code>3</code> times.
</pre>

<p><strong>Example 4:</strong></p>

<pre><strong>Input: </strong><span id="example-input-4-1">"aaaaa"</span>
<strong>Output: </strong><span id="example-output-4">4</span>
<strong>Explanation: </strong>The longest repeating substring is "aaaa", which occurs twice.
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li>The string <code>S</code> consists of only lowercase English letters from <code>'a'</code> - <code>'z'</code>.</li>
	<li><code>1 &lt;= S.length &lt;= 1500</code></li>
</ol>

**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[String](https://leetcode.com/tag/string/)

## Solution 1: Binary Search & Rolling Hash

```java
// OJ: https://leetcode.com/problems/longest-repeating-substring/
// Author: https://leetcode.com/charlesna/
// Time: O(nlogn)
// Space: O(n)
class Solution {
    public int longestRepeatingSubstring(String S) {
        int left = 0, right = S.length();
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (haveRepeat(S, mid)) {
                left = mid;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
    private boolean haveRepeat(String s, int len) {
        Set<Long> set = new HashSet<>();
        int l = s.length();
        int p = 31;
        long mod = (long) (1e9 + 7), minus = 1, hash = 0;
        char[] ch = s.toCharArray();
        for (int i = 0; i < len; i++) {
            minus = (minus * p) % mod;
        }
        for (int i = 0, j = 0; i < l; i++) {
            hash = (hash * p + s.charAt(i)) % mod;
            if (i - j + 1 > len) {
                hash = ((hash - s.charAt(j++) * minus) % mod + mod) % mod;
            }
            if (set.contains(hash)) return true;
            set.add(hash);
        }
        return false;
    }
}
```

## Solution 2: KMP

```java
// OJ: https://leetcode.com/problems/longest-repeating-substring/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n)
class Solution {
    public int longestRepeatingSubstring(String S) {
        int l = S.length(), res = 0;
        for (int i = 0; i < l; i++) {
            res = Math.max(res, runKMP(S, i));
        }
        return res;
    }
    private int runKMP(String s, int start) {
        int l = s.length(), res = 0;
        int[] next = new int[l + 1];
        char[] ch = s.toCharArray();
        for (int i = start + 1, j = start + 1; i < l; i++) {
            while (j != start + 1 && ch[i] != ch[j - 1]) {
                // System.out.println(j + " " + next[j - 1]);
                j = next[j - 1] + start + 1;
            } 
            if (ch[i] == ch[j - 1]) j++;
            next[i + 1] = j - start - 1;
            res = Math.max(res, j - start - 1);
        }
        return res;
    }
}
```

## Solution 3: Trie Tree

```java
// OJ: https://leetcode.com/problems/longest-repeating-substring/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n)
class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
    }
    public int longestRepeatingSubstring(String S) {
        TrieNode root = new TrieNode();
        char[] ch = S.toCharArray();
        int res = 0, l = ch.length, max = 0;
        for (int i = 0; i < l; i++) {
            TrieNode cur = root;
            max = 0;
            for (int j = i; j < l; j++) {
                int val = ch[j] - 'a';
                if (cur.children[val] == null) {
                    cur.children[val] = new TrieNode();
                    max = 0;
                }
                else {
                    max++;
                    res = Math.max(max, res);
                }
                cur = cur.children[val];
            }
        }
        return res;
    }
}
```

## Solution 4: DP

```java
// OJ: https://leetcode.com/problems/longest-repeating-substring/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int longestRepeatingSubstring(String S) {
        // dp[i][j] represent the longest array end with i, and j
        // if (s[i] == s[j]) dp[i][j] = dp[i - 1][j - 1] + 1
        // else dp[i][j] = 0;
        int l = S.length(), res = 0;;
        int[][] dp = new int[l + 1][l + 1];
        char[] ch = S.toCharArray();
        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= l; j++) {
                if (i == j) continue;
                dp[i][j] = ch[i - 1] == ch[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
```