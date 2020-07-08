# [139. Word Break (Medium)](https://leetcode.com/problems/word-break/)

<p>Given a <strong>non-empty</strong> string <em>s</em> and a dictionary <em>wordDict</em> containing a list of <strong>non-empty</strong> words, determine if <em>s</em> can be segmented into a space-separated sequence of one or more dictionary words.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The same word in the dictionary may be reused multiple times in the segmentation.</li>
	<li>You may assume the dictionary does not contain duplicate words.</li>
</ul>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> s = "leetcode", wordDict = ["leet", "code"]
<strong>Output:</strong> true
<strong>Explanation:</strong> Return true because <code>"leetcode"</code> can be segmented as <code>"leet code"</code>.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> s = "applepenapple", wordDict = ["apple", "pen"]
<strong>Output:</strong> true
<strong>Explanation:</strong> Return true because <code>"</code>applepenapple<code>"</code> can be segmented as <code>"</code>apple pen apple<code>"</code>.
&nbsp;            Note that you are allowed to reuse a dictionary word.
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
<strong>Output:</strong> false
</pre>


**Companies**:  
[Facebook](https://leetcode.com/company/facebook), [Amazon](https://leetcode.com/company/amazon), [Bloomberg](https://leetcode.com/company/bloomberg), [Microsoft](https://leetcode.com/company/microsoft), [Google](https://leetcode.com/company/google), [Qualtrics](https://leetcode.com/company/qualtrics), [Oracle](https://leetcode.com/company/oracle), [Snapchat](https://leetcode.com/company/snapchat), [Uber](https://leetcode.com/company/uber), [Hulu](https://leetcode.com/company/hulu), [Adobe](https://leetcode.com/company/adobe), [Audible](https://leetcode.com/company/audible)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Word Break II (Hard)](https://leetcode.com/problems/word-break-ii/)

## Solution : Trie can be used to optimize the time complexity by not using substring

```java
// OJ: https://leetcode.com/problems/word-break/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(dictionary + l)
class Solution {
    class Node {
        Node[] children;
        boolean isStart;
        public Node() {
            children = new Node[26];
        }
    }
    private void add(Node root, String s) {
        Node cur = root;
        for (int i = s.length() - 1; i >= 0; i--) {
            int val = s.charAt(i) - 'a';
            if (cur.children[val] == null) {
                cur.children[val] = new Node();
            }
            cur = cur.children[val];
        }
        cur.isStart = true;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Node root = new Node();
        for (String word : wordDict) {
            add(root, word);
        }
        int l = s.length();
        boolean[] dp = new boolean[l + 1];
        dp[0] = true;
        for (int i = 1; i <= l; i++) {
            Node cur = root;
            for (int j = i - 1; j >= 0; j--) {
                int val = s.charAt(j) - 'a';
                if (cur.children[val] == null) {
                    break;
                }
                cur = cur.children[val];
                if (cur.isStart) {
                    dp[i] |= dp[j];
                }
                if (dp[i]) break;
            }
        }
        return dp[l];
    }
}
```

```java
// OJ: https://leetcode.com/problems/word-break/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3) (i*j*substring)
// Space: O(n)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int l = s.length();
        boolean[] dp = new boolean[l + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        for (int i = 1; i <= l; i++) {
            for (int j = i; j >= 1; j--) {
                if (set.contains(s.substring(j- 1, i))) {
                    dp[i] |= dp[j - 1];
                }
            }
        }
        return dp[l];
    }
}
```