---
tags: ["leetcode","hash table"]
created: "2019/6/19 下午6:49:12"
difficulty: "easy"
---

# [1078-occurrences-after-bigram](https://leetcode.com/problems/occurrences-after-bigram/)

## Problem
<div><p>Given words <code>first</code> and <code>second</code>, consider occurrences in some&nbsp;<code>text</code> of the form "<code>first second third</code>", where <code>second</code> comes immediately after <code>first</code>, and <code>third</code> comes immediately after <code>second</code>.</p><br><br><p>For each such occurrence, add "<code>third</code>" to the answer, and return the answer.</p><br><br><p>&nbsp;</p><br><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input: </strong>text = <span id="example-input-1-1">"alice is a good girl she is a good student"</span>, first = <span id="example-input-1-2">"a"</span>, second = <span id="example-input-1-3">"good"</span><br><strong>Output: </strong><span id="example-output-1">["girl","student"]</span><br></pre><br><br><div><br><p><strong>Example 2:</strong></p><br><br><pre><strong>Input: </strong>text = <span id="example-input-2-1">"we will we will rock you"</span>, first = <span id="example-input-2-2">"we"</span>, second = <span id="example-input-2-3">"will"</span><br><strong>Output: </strong><span id="example-output-2">["we","rock"]</span><br></pre><br><br><p>&nbsp;</p><br><br><p><strong>Note:</strong></p><br><br><ol><br>	<li><code>1 &lt;= text.length &lt;= 1000</code></li><br>	<li><code>text</code> consists of space separated words, where each word consists of lowercase English letters.</li><br>	<li><code>1 &lt;= first.length, second.length &lt;= 10</code></li><br>	<li><code>first</code> and <code>second</code> consist of lowercase English letters.</li><br></ol><br></div></div>

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
