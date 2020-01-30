---
tags:
  - leetcode
  - backtracking
created: '2019/6/19 下午6:49:45'
difficulty: medium
---

# 1079-letter-tile-possibilities

## Problem

You have a set of `tiles`, where each tile has one letter `tiles[i]` printed on it.  Return the number of possible non-empty sequences of letters you can make.  
  


**Example 1:**  
  


```text
Input: "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
```

**Example 2:**  
  


```text
Input: "AAABBC"
Output: 188
```

**Note:**  
  


1. 2. `1 <= tiles.length <= 7`
3. 4. `tiles` consists of uppercase English letters.
5. 
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

