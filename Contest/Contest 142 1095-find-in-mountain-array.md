---
tags: ["leetcode"]
created: "2019/6/23 下午9:39:48"
difficulty: "hard"
---

# [1095-find-in-mountain-array](https://leetcode.com/problems/find-in-mountain-array/)

## Problem
<div><p><em>(This problem is an&nbsp;<strong>interactive problem</strong>.)</em></p><br><br><p>You may recall that an array&nbsp;<code>A</code> is a <em>mountain array</em> if and only if:</p><br><br><ul><br>	<li><code>A.length &gt;= 3</code></li><br>	<li>There exists some&nbsp;<code>i</code>&nbsp;with&nbsp;<code>0 &lt; i&nbsp;&lt; A.length - 1</code>&nbsp;such that:<br>	<ul><br>		<li><code>A[0] &lt; A[1] &lt; ... A[i-1] &lt; A[i]</code></li><br>		<li><code>A[i] &gt; A[i+1] &gt; ... &gt; A[A.length - 1]</code></li><br>	</ul><br>	</li><br></ul><br><br><p>Given a mountain&nbsp;array <code>mountainArr</code>, return the <strong>minimum</strong>&nbsp;<code>index</code> such that <code>mountainArr.get(index) == target</code>.&nbsp; If such an <code>index</code>&nbsp;doesn't exist, return <code>-1</code>.</p><br><br><p><strong>You can't access the mountain array directly.</strong>&nbsp; You may only access the array using a&nbsp;<code>MountainArray</code>&nbsp;interface:</p><br><br><ul><br>	<li><code>MountainArray.get(k)</code> returns the element of the array at index <code>k</code>&nbsp;(0-indexed).</li><br>	<li><code>MountainArray.length()</code>&nbsp;returns the length of the array.</li><br></ul><br><br><p>Submissions making more than <code>100</code> calls to&nbsp;<code>MountainArray.get</code>&nbsp;will be judged <em>Wrong Answer</em>.&nbsp; Also, any solutions that attempt to circumvent the judge&nbsp;will result in disqualification.</p><br><br><ol><br></ol><br><br><p>&nbsp;</p><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input:</strong> array = [1,2,3,4,5,3,1], target = 3<br><strong>Output:</strong> 2<br><strong>Explanation:</strong> 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.</pre><br><br><p><strong>Example 2:</strong></p><br><br><pre><strong>Input:</strong> array = [0,1,2,4,2,1], target = 3<br><strong>Output:</strong> -1<br><strong>Explanation:</strong> 3 does not exist in <code>the array,</code> so we return -1.<br></pre><br><br><p>&nbsp;</p><br><p><strong>Constraints:</strong></p><br><br><ol><br>	<li><code>3 &lt;= mountain_arr.length() &lt;= 10000</code></li><br>	<li><code>0 &lt;= target &lt;= 10^9</code></li><br>	<li><code>0 &lt;= mountain_arr.get(index) &lt;=&nbsp;10^9</code></li><br></ol><br></div>

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
