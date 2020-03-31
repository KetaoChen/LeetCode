# [306. Additive Number (Medium)](https://leetcode.com/problems/additive-number/)

<p>Additive number is a string whose digits can form additive sequence.</p>

<p>A valid additive sequence should contain <b>at least</b> three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.</p>

<p>Given a string containing only digits <code>'0'-'9'</code>, write a function to determine if it's an additive number.</p>

<p><b>Note:</b> Numbers in the additive sequence <b>cannot</b> have leading zeros, so sequence <code>1, 2, 03</code> or <code>1, 02, 3</code> is invalid.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> "112358"
<strong>Output:</strong> true
<strong>Explanation:</strong> The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
&nbsp;            1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> "199100199"
<strong>Output:</strong> true
<strong>Explanation:</strong> The additive sequence is: 1, 99, 100, 199.&nbsp;
&nbsp;            1 + 99 = 100, 99 + 100 = 199
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><font face="monospace"><code>num</code>&nbsp;</font>consists only of digits <code>'0'-'9'</code>.</li>
	<li><code>1 &lt;= num.length &lt;= 35</code></li>
</ul>

<p><b>Follow up:</b><br>
How would you handle overflow for very large input integers?</p>


**Companies**:  
[Epic Systems](https://leetcode.com/company/epic-systems)

**Related Topics**:  
[Backtracking](https://leetcode.com/tag/backtracking/)

**Similar Questions**:
* [Split Array into Fibonacci Sequence (Medium)](https://leetcode.com/problems/split-array-into-fibonacci-sequence/)

## Solution 

```java
// OJ: https://leetcode.com/problems/additive-number/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public boolean isAdditiveNumber(String num) {
        int l = num.length();
        for (int i = 1; i < l; i++) {
            for (int j = i + 1; j <= l; j++) {
                if (Math.max(i, j - i) > l - j) {
                    break;
                }
                if (valid(num, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean valid(String s, int start, int i, int j) {
        if (j == s.length()) {
            return true;
        }
        int index = j + 1;
        while (index <= s.length()) {
            if (index - j - 1 > Math.max(i - start, j - i)) {
                return false;
            }
            if ((s.charAt(start) == '0' && (i - start) > 1) 
                || (s.charAt(i) == '0' && (j - i) > 1 )
                || (s.charAt(j) == '0' && index - j > 1)) {
                return false;
            }
            if (add(s.substring(start, i), s.substring(i, j)).equals(s.substring(j, index))) {
                return valid(s, i, j, index);
            }
            index++;
        }
        return false;
    }
    private String add(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length(), i = l1 - 1, j = l2 - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            int f = i >= 0 ? s1.charAt(i--) - '0' : 0;
            int s = j >= 0 ? s2.charAt(j--) - '0' : 0;
            int cur = f + s + carry;
            carry = cur / 10;
            cur = cur % 10;
            sb.append(cur);
        }
        return sb.reverse().toString();
    }
}
```