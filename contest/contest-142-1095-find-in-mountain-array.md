---
tags:
  - leetcode
created: '2019/6/23 下午9:39:48'
difficulty: hard
---

# 1095-find-in-mountain-array

## Problem

_\(This problem is an **interactive problem**.\)_  
  


You may recall that an array `A` is a _mountain array_ if and only if:  
  


* * `A.length >= 3`
* * There exists some `i` with `0 < i < A.length - 1` such that: 
  * * `A[0] < A[1] < ... A[i-1] < A[i]`
  * * `A[i] > A[i+1] > ... > A[A.length - 1]`
  * 
* 
Given a mountain array `mountainArr`, return the **minimum** `index` such that `mountainArr.get(index) == target`.  If such an `index` doesn't exist, return `-1`.  
  


**You can't access the mountain array directly.**  You may only access the array using a `MountainArray` interface:  
  


* * `MountainArray.get(k)` returns the element of the array at index `k` \(0-indexed\).
* * `MountainArray.length()` returns the length of the array.
* 
Submissions making more than `100` calls to `MountainArray.get` will be judged _Wrong Answer_.  Also, any solutions that attempt to circumvent the judge will result in disqualification.  
  


1. 
**Example 1:**  
  


```text
Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
```

**Example 2:**  
  


```text
Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.
```

**Constraints:**  
  


1. 2. `3 <= mountain_arr.length() <= 10000`
3. 4. `0 <= target <= 10^9`
5. 6. `0 <= mountain_arr.get(index) <= 10^9`
7. 
## Solution

java

```java
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    int step = 0;
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int l = mountainArr.length();
        int left = 0;
        int right = l - 1;

        //find the top
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (mountainArr.get(mid) > mountainArr.get(mid - 1)) {
                left = mid;
            }
            else {
                right = mid;
            }
        }

        int res = Math.min(checkLeft(mountainArr, 0, left, target), checkRight(mountainArr, right, l - 1, target));
        if (res == 10001) {
            return -1;
        }
        return res;
    }

    private int checkLeft(MountainArray arr, int start, int end, int target) {
        int left = start;
        int right = end;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (arr.get(mid) == target) {
                return mid;
            }
            if (arr.get(mid) < target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        if (arr.get(left) == target) {
            return left;
        }
        if (arr.get(right) == target) {
            return right;
​
```

