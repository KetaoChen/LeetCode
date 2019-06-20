---
tags: ["leetcode","array"]
created: "2019/6/19 下午6:47:57"
difficulty: "easy"
---

# [1089-duplicate-zeros](https://leetcode.com/problems/duplicate-zeros/)

## Problem
<div><p>Given a fixed length&nbsp;array <code>arr</code> of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.</p><br><br><p>Note that elements beyond the length of the original array are not written.</p><br><br><p>Do the above modifications to the input array <strong>in place</strong>, do not return anything from your function.</p><br><br><p>&nbsp;</p><br><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-1-1">[1,0,2,3,0,4,5,0]</span><br><strong>Output: </strong>null<br><strong>Explanation: </strong>After calling your function, the <strong>input</strong> array is modified to: <span id="example-output-1">[1,0,0,2,3,0,0,4]</span><br></pre><br><br><p><strong>Example 2:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-2-1">[1,2,3]</span><br><strong>Output: </strong>null<br><strong>Explanation: </strong>After calling your function, the <strong>input</strong> array is modified to: <span id="example-output-2">[1,2,3]</span><br></pre><br><br><p>&nbsp;</p><br><br><p><strong>Note:</strong></p><br><br><ol><br>	<li><code>1 &lt;= arr.length &lt;= 10000</code></li><br>	<li><code>0 &lt;= arr[i] &lt;= 9</code></li><br></ol></div>

## Solution

java
```java
class Solution {
    public void duplicateZeros(int[] arr) {
        int index = 0;
        while (index < arr.length) {
            if (arr[index] == 0) {
                for (int i = arr.length - 1; i > index; i--) {
                    arr[i] = arr[i - 1];
                }
                if (index + 1 < arr.length) {
                    arr[index + 1] = 0;
                }
                index ++;
            }
            index++;
        }
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.print(arr[i] + " ");
        // }
    }
}
​
```
