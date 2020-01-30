---
tags:
  - leetcode
  - hash table
created: '2019/6/19 下午6:49:12'
difficulty: easy
---

# 1078-occurrences-after-bigram

## Problem

Given words `first` and `second`, consider occurrences in some `text` of the form "`first second third`", where `second` comes immediately after `first`, and `third` comes immediately after `second`.  
  


For each such occurrence, add "`third`" to the answer, and return the answer.  
  


**Example 1:**  
  


```text
Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
Output: ["girl","student"]
```

**Example 2:**  
  


```text
Input: text = "we will we will rock you", first = "we", second = "will"
Output: ["we","rock"]
```

**Note:**  
  


1. 2. `1 <= text.length <= 1000`
3. 4. `text` consists of space separated words, where each word consists of lowercase English letters.
5. 6. `1 <= first.length, second.length <= 10`
7. 8. `first` and `second` consist of lowercase English letters.
9. 
## Solution

java

```java
class Solution {
    public String[] findOcurrences(String text, String first, String second) {

        String[] str = text.split(" ");
        if (str.length <= 2) {
            return new String[0];
        }

        List<String> res = new ArrayList<>();
        for (int i = 2; i < str.length; i++) {
            if (str[i - 2].equals(first) && str[i - 1].equals(second)) {
                res.add(str[i]);
            }
        }

        String[] result = new String[res.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
​
```

