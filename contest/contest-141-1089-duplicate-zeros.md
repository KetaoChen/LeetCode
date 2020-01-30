---
tags:
  - leetcode
  - array
created: '2019/6/19 下午6:47:57'
difficulty: easy
---

# 1089-duplicate-zeros

## Problem

Given a fixed length array `arr` of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.  
  


Note that elements beyond the length of the original array are not written.  
  


Do the above modifications to the input array **in place**, do not return anything from your function.  
  


**Example 1:**  
  


```text
Input: [1,0,2,3,0,4,5,0]
Output: null
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
```

**Example 2:**  
  


```text
Input: [1,2,3]
Output: null
Explanation: After calling your function, the input array is modified to: [1,2,3]
```

**Note:**  
  


1. 2. `1 <= arr.length <= 10000`
3. 4. `0 <= arr[i] <= 9`
5. 
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

