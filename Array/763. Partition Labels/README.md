# [763. Partition Labels (Medium)](https://leetcode.com/problems/partition-labels/)

<p>
A string <code>S</code> of lowercase letters is given.  We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
</p><p>

</p><p><b>Example 1:</b><br>
</p><pre><b>Input:</b> S = "ababcbacadefegdehijhklij"
<b>Output:</b> [9,7,8]
<b>Explanation:</b>
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
</pre>
<p></p>

<p><b>Note:</b><br></p><ol>
<li><code>S</code> will have length in range <code>[1, 500]</code>.</li>
<li><code>S</code> will consist of lowercase letters (<code>'a'</code> to <code>'z'</code>) only.</li>
</ol><p></p>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Two Pointers](https://leetcode.com/tag/two-pointers/), [Greedy](https://leetcode.com/tag/greedy/)

**Similar Questions**:
* [Merge Intervals (Medium)](https://leetcode.com/problems/merge-intervals/)

## Solution 1: Slide Window + Last Index

```java
// OJ: https://leetcode.com/problems/partition-labels/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) {
            int c = S.charAt(i) - 'a';
            last[c] = i;
        }
        List<Integer> res = new ArrayList<>();
        int end = 0, start = -1;
        for (int i = 0; i < S.length(); i++) {
            int c = S.charAt(i) - 'a';
            end = Math.max(end, last[c]);
            if (i == end) {
                res.add(i - start);
                start = i;
            }
        }
        return res;
    }
}
```

## Solution 2: Map & Set

```java
// OJ: https://leetcode.com/problems/partition-labels/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public List<Integer> partitionLabels(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        int last = -1;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i); 
            set.add(c);
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) set.remove(c);
            if (set.size() == 0) {
                res.add(i - last);
                last = i;
            }
        }
        return res;
    }
}
```