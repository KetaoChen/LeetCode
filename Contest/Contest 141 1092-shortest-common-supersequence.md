---
tags: ["leetcode","dynamic programming"]
created: "2019/6/19 下午6:46:10"
difficulty: "hard"
---

# [1092-shortest-common-supersequence](https://leetcode.com/problems/shortest-common-supersequence/)

## Problem
<div><p>Given two strings <code>str1</code> and <code>str2</code>,&nbsp;return the shortest string that has both <code>str1</code>&nbsp;and <code>str2</code>&nbsp;as subsequences.&nbsp;&nbsp;If multiple answers exist, you may return any of them.</p><br><br><p><em>(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen <u>anywhere</u> from T) results in the string S.)</em></p><br><br><p>&nbsp;</p><br><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input: </strong>str1 = <span id="example-input-1-1">"abac"</span>, str2 = <span id="example-input-1-2">"cab"</span><br><strong>Output: </strong><span id="example-output-1">"cabac"</span><br><strong>Explanation: </strong><br>str1 = "abac" is a substring of "cabac" because we can delete the first "c".<br>str2 = "cab" is a substring of "cabac" because we can delete the last "ac".<br>The answer provided is the shortest such string that satisfies these properties.<br></pre><br><br><p>&nbsp;</p><br><br><p><strong>Note:</strong></p><br><br><ol><br>	<li><code>1 &lt;= str1.length, str2.length &lt;= 1000</code></li><br>	<li><code>str1</code> and <code>str2</code> consist of lowercase English letters.</li><br></ol></div>

## Solution

java
```java
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
    //find the longest common subsequence
        //dp[i][j] represent the longest number 
        int l1 = str1.length();
        int l2 = str2.length();
        
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    if (dp[i][j - 1] >= dp[i - 1][j]) {
                        dp[i][j] = dp[i][j - 1];
                    }
                    else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        StringBuilder common = new StringBuilder();
        int first = l1;
        int second = l2;
        int length = dp[l1][l2];
        while (first >= 1 && second >= 1) {
            if (dp[first - 1][second - 1] == length - 1 && str1.charAt(first - 1) == str2.charAt(second - 1)) {
                common.append(str1.charAt(first - 1));
                first--;
                second--;
                length--;
            }
            else {
                if (dp[first - 1][second] == length) {
                    first--;
                }
                else {
                    second--;
                }
            }
        }
        
        String longest = common.reverse().toString();
        //System.out.println(longest);
        StringBuilder res = new StringBuilder();
        first = 0;
        second = 0;
        int index = 0;
        while (first < l1 && second < l2 && index < longest.length()) {
            while (first < l1 && str1.charAt(first) != longest.charAt(index)) {
                res.append(str1.charAt(first));
                first++;
            }
            while (second < l2 && str2.charAt(second) != longest.charAt(index)) {
                res.append(str2.charAt(second));
                second++;
            }
            res.append(longest.charAt(index));
            first++;
            second++;
            index++;
        }
        
        while (first < l1) {
            res.append(str1.charAt(first));
            first++;
        }
        while (second < l2) {
            res.append(str2.charAt(second));
            second++;
        }
        return res.toString();
            
    }
}
​
```
