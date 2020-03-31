# [842. Split Array into Fibonacci Sequence (Medium)](https://leetcode.com/problems/split-array-into-fibonacci-sequence/)

<p>Given a string <code>S</code>&nbsp;of digits, such as <code>S = "123456579"</code>, we can split it into a <em>Fibonacci-like sequence</em>&nbsp;<code>[123, 456, 579].</code></p>

<p>Formally, a Fibonacci-like sequence is a list&nbsp;<code>F</code> of non-negative integers such that:</p>

<ul>
	<li><code>0 &lt;= F[i] &lt;= 2^31 - 1</code>, (that is,&nbsp;each integer fits a 32-bit signed integer type);</li>
	<li><code>F.length &gt;= 3</code>;</li>
	<li>and<code> F[i] + F[i+1] = F[i+2] </code>for all <code>0 &lt;= i &lt; F.length - 2</code>.</li>
</ul>

<p>Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.</p>

<p>Return any Fibonacci-like sequence split from <code>S</code>, or return <code>[]</code> if it cannot be done.</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>"123456579"
<strong>Output: </strong>[123,456,579]
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong>"11235813"
<strong>Output: </strong>[1,1,2,3,5,8,13]
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input: </strong>"112358130"
<strong>Output: </strong>[]
<strong>Explanation: </strong>The task is impossible.
</pre>

<p><strong>Example 4:</strong></p>

<pre><strong>Input: </strong>"0123"
<strong>Output: </strong>[]
<strong>Explanation: </strong>Leading zeroes are not allowed, so "01", "2", "3" is not valid.
</pre>

<p><strong>Example 5:</strong></p>

<pre><strong>Input: </strong>"1101111"
<strong>Output: </strong>[110, 1, 111]
<strong>Explanation: </strong>The output [11, 0, 11, 11] would also be accepted.
</pre>

<p><strong>Note: </strong></p>

<ol>
	<li><code>1 &lt;= S.length&nbsp;&lt;= 200</code></li>
	<li><code>S</code> contains only digits.</li>
</ol>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[String](https://leetcode.com/tag/string/), [Backtracking](https://leetcode.com/tag/backtracking/), [Greedy](https://leetcode.com/tag/greedy/)

**Similar Questions**:
* [Additive Number (Medium)](https://leetcode.com/problems/additive-number/)
* [Fibonacci Number (Easy)](https://leetcode.com/problems/fibonacci-number/)

## Solution 

```java
// OJ: https://leetcode.com/problems/split-array-into-fibonacci-sequence/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < S.length(); i++) {
            long first = Long.parseLong(S.substring(0, i));
            if (first > Integer.MAX_VALUE || (S.charAt(0) == '0' && i > 1)) return res;
            res.add((int) first);
            for (int j = i + 1; j <= S.length(); j++) {
                if (Math.max(i, j - i) > S.length() - j) break;
                long second = Long.parseLong(S.substring(i, j));
                if (second > Integer.MAX_VALUE || (S.charAt(i) == '0' && j - i > 1)) break;
                res.add((int) second);
                if (helper(S, res, first, second, j)) {
                    return res;
                }
                res.remove(res.size() - 1);
            }
            res.remove(res.size() - 1);
        }
        return res;
    }
    private boolean helper(String S, List<Integer> res, long f, long s, int index) {
        if (index == S.length()) {
            return true;
        }
        for (int i = index + 1; i <= S.length(); i++) {
            if (S.charAt(index) == 0 && i > index + 1) return false;
            long l = Long.parseLong(S.substring(index, i));
            if (l > Integer.MAX_VALUE || l > f + s) return false;
            if (l == f + s) {
                res.add((int) l);
                if (helper(S, res, s, l, i)) {
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }
        return false;
    }
}
```