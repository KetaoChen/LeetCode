---
tags:
  - leetcode
  - dynamic programming
created: '2019/6/19 下午6:46:10'
difficulty: hard
---

# 1092-shortest-common-supersequence

## Problem

Given two strings `str1` and `str2`, return the shortest string that has both `str1` and `str2` as subsequences.  If multiple answers exist, you may return any of them.  
  


_\(A string S is a subsequence of string T if deleting some number of characters from T \(possibly 0, and the characters are chosen anywhere from T\) results in the string S.\)_  
  


**Example 1:**  
  


```text
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a substring of "cabac" because we can delete the first "c".
str2 = "cab" is a substring of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
```

**Note:**  
  


1. 2. `1 <= str1.length, str2.length <= 1000`
3. 4. `str1` and `str2` consist of lowercase English letters.
5. 
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

