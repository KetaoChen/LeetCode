# [316. Remove Duplicate Letters (Hard)](https://leetcode.com/problems/remove-duplicate-letters/)

<p>Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.</p>

<p><b>Example 1:</b></p>

<pre><b>Input:</b> <code>"bcabc"</code>
<b>Output:</b> <code>"abc"</code>
</pre>

<p><b>Example 2:</b></p>

<pre><b>Input:</b> <code>"cbacdcbc"</code>
<b>Output:</b> <code>"acdb"</code>
</pre>

<p><strong>Note:</strong> This question is the same as 1081:&nbsp;<a href="https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/">https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/</a></p>


**Companies**:  
[ByteDance](https://leetcode.com/company/bytedance), [Google](https://leetcode.com/company/google)

**Related Topics**:  
[Stack](https://leetcode.com/tag/stack/), [Greedy](https://leetcode.com/tag/greedy/)

## Solution 

```java
// OJ: https://leetcode.com/problems/remove-duplicate-letters/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(26)
class Solution {
    public String removeDuplicateLetters(String s) {
        // record the last index of each character.
        int[] index = new int[26];
        int l = s.length();
        Arrays.fill(index, -1);
        for (int i = l - 1; i >= 0; i--) {
            char c = s.charAt(i);
            index[c - 'a'] = Math.max(index[c - 'a'], i);
        }
        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            while (!stack.isEmpty() && !visited[c - 'a'] && c < stack.peek() && index[stack.peek() - 'a'] > i) {
                visited[stack.pop() - 'a'] = false;
            }
            if (!visited[c - 'a']) {
                stack.push(c);
                visited[c - 'a'] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
```