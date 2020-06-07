# [126. Word Ladder II (Hard)](https://leetcode.com/problems/word-ladder-ii/)

<p>Given two words (<em>beginWord</em> and <em>endWord</em>), and a dictionary's word list, find all shortest transformation sequence(s) from <em>beginWord</em> to <em>endWord</em>, such that:</p>

<ol>
	<li>Only one letter can be changed at a time</li>
	<li>Each transformed word must exist in the word list. Note that <em>beginWord</em> is <em>not</em> a transformed word.</li>
</ol>

<p><strong>Note:</strong></p>

<ul>
	<li>Return an empty list if there is no such transformation sequence.</li>
	<li>All words have the same length.</li>
	<li>All words contain only lowercase alphabetic characters.</li>
	<li>You may assume no duplicates in the word list.</li>
	<li>You may assume <em>beginWord</em> and <em>endWord</em> are non-empty and are not the same.</li>
</ul>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong>
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

<strong>Output:</strong>
[
  ["hit","hot","dot","dog","cog"],
&nbsp; ["hit","hot","lot","log","cog"]
]
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong>
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

<strong>Output: </strong>[]

<strong>Explanation:</strong>&nbsp;The endWord "cog" is not in wordList, therefore no possible<strong>&nbsp;</strong>transformation.
</pre>

<ul>
</ul>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Facebook](https://leetcode.com/company/facebook), [Microsoft](https://leetcode.com/company/microsoft), [Oracle](https://leetcode.com/company/oracle), [Uber](https://leetcode.com/company/uber)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/), [String](https://leetcode.com/tag/string/), [Backtracking](https://leetcode.com/tag/backtracking/), [Breadth-first Search](https://leetcode.com/tag/breadth-first-search/)

**Similar Questions**:
* [Word Ladder (Medium)](https://leetcode.com/problems/word-ladder/)

## Solution 

```java
// OJ: https://leetcode.com/problems/word-ladder-ii/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        int d = getShortest(beginWord, endWord, map, wordList);
        // System.out.println(map.size());
        if (d == -1) return res;     
        dfs(res, beginWord, endWord, map, new ArrayList<>());
        return res;
    }
    private void dfs(List<List<String>> res, String beginWord, String endWord, Map<String, List<String>> map, List<String> temp) {
        temp.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);
            return;
        }
        for (String cur : map.getOrDefault(beginWord, new ArrayList<>())) {
            dfs(res, cur, endWord, map, temp);            
        }
        temp.remove(temp.size() - 1);
    }
    private int getShortest(String beginWord, String endWord, Map<String, List<String>> map, List<String> wordList) {
        Set<String> all = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        q.offer(beginWord);
        set.add(beginWord);
        int d = 0;
        while (!q.isEmpty()) {
            int s = q.size();
            boolean end = false;
            Set<String> n = new HashSet<>();
            for (int i = 0; i < s; i++) {
                String cur = q.poll();
                map.put(cur, new ArrayList<>());
                List<String> list = map.get(cur);
                end |= cur.equals(endWord);
                for (int k = 0; k < cur.length(); k++) {
                    int old = cur.charAt(k);
                    StringBuilder sb = new StringBuilder(cur);
                    for (int j = 0; j < 26; j++) {
                        if (j == old) continue;
                        sb.setCharAt(k, (char) ('a' + j));
                        String add = sb.toString();
                        if (all.contains(add) && !set.contains(add)) {
                            if (!n.contains(add)) {
                                n.add(add);
                                q.offer(add);
                            }
                            list.add(add);
                        }
                    }
                }
            }
            for (String str : n) set.add(str);
            if (end) return d;
            d++;
        }
        return -1;
    }
    private boolean check(String s, String t) {
        boolean diff = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (diff) return false;
                diff = true;
            }
        }
        return true;
    }
}
```