---
tags: ["leetcode","backtracking"]
created: "2019/6/19 下午6:49:45"
difficulty: "medium"
---

# [1079-letter-tile-possibilities](https://leetcode.com/problems/letter-tile-possibilities/)

## Problem
<div><p>You have a set of <code>tiles</code>, where each tile has one letter <code>tiles[i]</code> printed on it.&nbsp; Return the number of possible non-empty sequences of letters you can make.</p><br><br><p>&nbsp;</p><br><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-1-1">"AAB"</span><br><strong>Output: </strong><span id="example-output-1">8</span><br><strong>Explanation: </strong>The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".<br></pre><br><br><div><br><p><strong>Example 2:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-2-1">"AAABBC"</span><br><strong>Output: </strong><span id="example-output-2">188</span><br></pre><br><br><p>&nbsp;</p><br></div><br><br><p><strong>Note:</strong></p><br><br><ol><br>	<li><code>1 &lt;= tiles.length &lt;= 7</code></li><br>	<li><code>tiles</code> consists of uppercase English letters.</li><br></ol></div>

## Solution

java
```java
class Solution {
    int res;
    public int numTilePossibilities(String tiles) {
        res = 0;
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        boolean[] visited = new boolean[chars.length];
        helper(chars, visited);        
        return res;
    }
    
    private void helper(char[] chars, boolean[] visited) {
        if (allVisit(visited)) {
            return;
        }
                                         
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i != 0 && !visited[i - 1] && chars[i] == chars[i - 1] ) {
                continue;
            }
            visited[i] = true;
            res++;
            helper(chars, visited);
            visited[i] = false;
        }
    }
    
    private boolean allVisit(boolean[] visited) {
        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
    }
}
​
```
