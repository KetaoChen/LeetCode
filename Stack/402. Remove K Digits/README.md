# [402. Remove K Digits (Medium)](https://leetcode.com/problems/remove-k-digits/)

<p>Given a non-negative integer <i>num</i> represented as a string, remove <i>k</i> digits from the number so that the new number is the smallest possible.
</p>

<p><b>Note:</b><br>
</p><ul>
<li>The length of <i>num</i> is less than 10002 and will be ¡Ý <i>k</i>.</li>
<li>The given <i>num</i> does not contain any leading zero.</li>
</ul>

<p></p>

<p><b>Example 1:</b>
</p><pre>Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
</pre>
<p></p>

<p><b>Example 2:</b>
</p><pre>Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
</pre>
<p></p>

<p><b>Example 3:</b>
</p><pre>Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
</pre>
<p></p>

**Companies**:  
[Oracle](https://leetcode.com/company/oracle)

**Related Topics**:  
[Stack](https://leetcode.com/tag/stack/), [Greedy](https://leetcode.com/tag/greedy/)

**Similar Questions**:
* [Create Maximum Number (Hard)](https://leetcode.com/problems/create-maximum-number/)
* [Monotone Increasing Digits (Medium)](https://leetcode.com/problems/monotone-increasing-digits/)

## Solution 

```java
// OJ: https://leetcode.com/problems/remove-k-digits/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public String removeKdigits(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            // get cur value;
            char cur = s.charAt(i);
            // while the value we have is larger than current value, we keep removing the number we have.
            while (sb.length() > 0 && k > 0 && sb.charAt(sb.length() - 1) > cur) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            if (sb.length() == 0  && cur == '0') continue;
            sb.append(cur);
        }
        String res = sb.toString().substring(0, sb.length() - Math.min(sb.length(), k));
        return res.length() == 0 ? "0" : res;
    }
}
```