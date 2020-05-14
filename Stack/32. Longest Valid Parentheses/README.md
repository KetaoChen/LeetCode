# [32. Longest Valid Parentheses (Hard)](https://leetcode.com/problems/longest-valid-parentheses/)

<p>Given a string containing just the characters <code>'('</code> and <code>')'</code>, find the length of the longest valid (well-formed) parentheses substring.</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> "(()"
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest valid parentheses substring is <code>"()"</code>
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> "<code>)()())</code>"
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest valid parentheses substring is <code>"()()"</code>
</pre>


**Companies**:  
[Facebook](https://leetcode.com/company/facebook), [Microsoft](https://leetcode.com/company/microsoft), [Amazon](https://leetcode.com/company/amazon), [Apple](https://leetcode.com/company/apple), [Oracle](https://leetcode.com/company/oracle), [Citadel](https://leetcode.com/company/citadel)

**Related Topics**:  
[String](https://leetcode.com/tag/string/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Valid Parentheses (Easy)](https://leetcode.com/problems/valid-parentheses/)

## Solution 1: Counter O(1) space

```java
// OJ: https://leetcode.com/problems/longest-valid-parentheses/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, res = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            }
            else {
                right++;
            }
            if (left == right) {
                res = Math.max(res, left * 2);
            }
            if (right > left) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            }
            else {
                right++;
            }
            if (left == right) {
                res = Math.max(res, left * 2);
            }
            if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return res;
    }
}
```


## Solution 2: Monotonic Stack

```java
// OJ: https://leetcode.com/problems/longest-valid-parentheses/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0, l = s.length();
        int[] d = new int[l + 1];
        stack.push(0);
        for (int i = 1; i <= l; i++) {
            d[i] = s.charAt(i - 1) == '(' ? d[i - 1] + 1 : d[i - 1] - 1;
            int end = i;
            while (!stack.isEmpty() && d[i] <= d[stack.peek()]) {
                end = stack.pop();
            }
            if (d[i] == d[end]) {
                stack.push(end);
                res = Math.max(res, i - end);
            }
            else {
                stack.push(i);
            }
            d[i] = Math.max(d[i], 0);
        }
        return res;
    }
}
```

