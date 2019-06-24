---
tags: ["leetcode"]
created: "2019/6/23 下午9:39:56"
difficulty: "medium"
---

# [1094-car-pooling](https://leetcode.com/problems/car-pooling/)

## Problem
<div><p>You are driving a vehicle that&nbsp;has <code>capacity</code> empty seats initially available for passengers.&nbsp; The vehicle <strong>only</strong> drives east (ie. it <strong>cannot</strong> turn around and drive west.)</p><br><br><p>Given a list of <code>trips</code>, <code>trip[i] = [num_passengers, start_location, end_location]</code>&nbsp;contains information about the <code>i</code>-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.&nbsp; The locations are given as the number of kilometers&nbsp;due east from your vehicle's initial location.</p><br><br><p>Return <code>true</code> if and only if&nbsp;it is possible to pick up and drop off all passengers for all the given trips.&nbsp;</p><br><br><p>&nbsp;</p><br><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input: </strong>trips = <span id="example-input-1-1">[[2,1,5],[3,3,7]]</span>, capacity = <span id="example-input-1-2">4</span><br><strong>Output: </strong><span id="example-output-1">false</span><br></pre><br><br><div><br><p><strong>Example 2:</strong></p><br><br><pre><strong>Input: </strong>trips = <span id="example-input-2-1">[[2,1,5],[3,3,7]]</span>, capacity = <span id="example-input-2-2">5</span><br><strong>Output: </strong><span id="example-output-2">true</span><br></pre><br><br><div><br><p><strong>Example 3:</strong></p><br><br><pre><strong>Input: </strong>trips = <span id="example-input-3-1">[[2,1,5],[3,5,7]]</span>, capacity = <span id="example-input-3-2">3</span><br><strong>Output: </strong><span id="example-output-3">true</span><br></pre><br><br><div><br><p><strong>Example 4:</strong></p><br><br><pre><strong>Input: </strong>trips = <span id="example-input-4-1">[[3,2,7],[3,7,9],[8,3,9]]</span>, capacity = <span id="example-input-4-2">11</span><br><strong>Output: </strong><span id="example-output-4">true</span><br></pre><br></div><br></div><br></div><br><br><div><br><div><br><div><br><div>&nbsp;</div><br></div><br></div><br></div><br><br><p>&nbsp;</p><br><p><strong>Constraints:</strong></p><br><br><ol><br>	<li><code>trips.length &lt;= 1000</code></li><br>	<li><code>trips[i].length == 3</code></li><br>	<li><code>1 &lt;= trips[i][0] &lt;= 100</code></li><br>	<li><code>0 &lt;= trips[i][1] &lt; trips[i][2] &lt;= 1000</code></li><br>	<li><code>1 &lt;=&nbsp;capacity &lt;= 100000</code></li><br></ol><br></div>

## Solution

java
```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, new Comparator<int[]>() {
           @Override
            public int compare(int[] trip1, int[] trip2) {
                return trip1[1] - trip2[1];
            }
        });
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] trip1, int[] trip2) {
                return trip1[2] - trip2[2];
            }
        });
        
        int num = 0;
        for (int i = 0; i < trips.length; i++) {
            int[] trip = trips[i];
            
            while (!pq.isEmpty()) {
                int[] top = pq.peek();
                if (top[2] <= trip[1]) {
                    num -= top[0];
                    pq.poll();
                }
                else {
                    break;
                }
            }
            if (trip[0] + num > capacity) {
                return false;
            }
            pq.offer(trip);
            num += trip[0];
        }
        return true;
    }
}
​
```
