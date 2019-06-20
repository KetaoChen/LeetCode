---
tags: ["leetcode","string"]
created: "2019/6/19 下午6:50:07"
difficulty: "medium"
---

# [1081-smallest-subsequence-of-distinct-characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/)

## Problem
<div><p>Return the lexicographically smallest subsequence of <code>text</code> that contains all the distinct characters of <code>text</code> exactly once.</p><br><br><p>&nbsp;</p><br><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-1-1">"cdadabcc"</span><br><strong>Output: </strong><span id="example-output-1">"adbc"</span><br></pre><br><br><div><br><p><strong>Example 2:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-2-1">"abcd"</span><br><strong>Output: </strong><span id="example-output-2">"abcd"</span><br></pre><br><br><div><br><p><strong>Example 3:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-3-1">"ecbacba"</span><br><strong>Output: </strong><span id="example-output-3">"eacb"</span><br></pre><br><br><div><br><p><strong>Example 4:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-4-1">"leetcode"</span><br><strong>Output: </strong><span id="example-output-4">"letcod"</span><br></pre><br><br><p>&nbsp;</p><br><br><p><strong>Note:</strong></p><br><br><ol><br>	<li><code>1 &lt;= text.length &lt;= 1000</code></li><br>	<li><code>text</code> consists of lowercase English letters.</li><br></ol><br><br><p>&nbsp;</p><br></div><br></div><br></div></div>

## Solution

java

```java

class Solution {
    
    public String smallestSubsequence(String text) {
        //only store the last index in an 26 length arr
        //loop the text to see whether need to pop the char or to put this char in the stack
        //when the current char is smaller than the char before and also that char will appear later, then we can pop that out
        
        int[] last = new int[26];
        boolean[] used = new boolean[26];
        for (int i = 0; i < text.length(); i++) {
            int ch = text.charAt(i) - 'a';
            last[ch] = i;
        }
        
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            int ch = text.charAt(i) - 'a';
            //check whether to pop the character before it
            if (used[ch]) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > ch && last[stack.peek()] > i) {
                used[stack.pop()] = false;
            }
            stack.push(ch);
            used[ch] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i : stack) {
            sb.append((char) (i + 'a'));
        }
        return sb.toString();
    }
}
​
```

```java
class Solution {
    //Time complexity:
        //1. get last index: O(n)
        //2. remove all the index and key from the hashmap O(n)
    //space complexity:
        //need to store all the index in the HashMap O(n)
    public String smallestSubsequence(String text) {
        //same idea, find the first char of the ans.
        //it should be satisfy these two conditions: 1.as small as possible, 2.other chars must be appear later
        //so we use a HashMap<Character, List<Integer>> to store all the index of each character
        //Then start from a to z, check which one should be the first char, and remove all the index before this index
        
        //optimize: just record the last index of each char, then loop the string, check whether to put this in the answer
        
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (map.containsKey(c)) {
                List<Integer> list = map.get(c);
                list.add(i);
                map.put(c, list);
            }
            else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(c, list);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!map.isEmpty()) {
            for (int i = 0; i < 26; i++) {
                char c = (char) ('a' + i);
                if (map.containsKey(c) && canBeFirst(c, map)) {
                    sb.append(c);
                    update(map, c);
                    break;
                }
            }
        }
        
        return sb.toString();
    }
    
    private boolean canBeFirst(char c, HashMap<Character, List<Integer>> map) {
        List<Integer> cur = map.get(c);
        int index = cur.get(0);
        for (char ch : map.keySet()) {
​
```


```java

class Solution {
    public String smallestSubsequence(String text) {
        StringBuilder sb = new StringBuilder();
        while (text.length() > 0) {
            char add = getFirstChar(text);
            sb.append(add);
            text = removeFirst(text, add);
        }
        return sb.toString();
    }
    
    private char getFirstChar(String s) {
        HashMap<Character, HashSet<Character>> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            for (char ch : map.keySet()) {
                if (ch != c) {
                    HashSet<Character> set = map.get(ch);
                    set.add(c);
                    map.put(ch, set);
                }
            }
            if (!map.containsKey(c)) {
                HashSet<Character> set = new HashSet<>();
                map.put(c, set);
            }
        }
​
        char res = s.charAt(0);
        for (char c : map.keySet()) {
            System.out.println(map.size() + " the char size : " + c + " " + map.get(c).size());
            if (map.get(c).size() == map.size() - 1 && c < res) {
                //System.out.println(c);
                res = c;
            }
        }
        //System.out.println(res);
        return res;
    }
    
    private String removeFirst(String s, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.indexOf(c); i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != c) {
​
```
