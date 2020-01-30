---
tags:
  - leetcode
created: '2019/6/23 下午9:39:56'
difficulty: medium
---

# 1094-car-pooling

## Problem

You are driving a vehicle that has `capacity` empty seats initially available for passengers.  The vehicle **only** drives east \(ie. it **cannot** turn around and drive west.\)  
  


Given a list of `trips`, `trip[i] = [num_passengers, start_location, end_location]` contains information about the `i`-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.  
  


Return `true` if and only if it is possible to pick up and drop off all passengers for all the given trips.   
  


**Example 1:**  
  


```text
Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
```

**Example 2:**  
  


```text
Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
```

**Example 3:**  
  


```text
Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true
```

**Example 4:**  
  


```text
Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true
```

**Constraints:**  
  


1. 2. `trips.length <= 1000`
3. 4. `trips[i].length == 3`
5. 6. `1 <= trips[i][0] <= 100`
7. 8. `0 <= trips[i][1] < trips[i][2] <= 1000`
9. 10. `1 <= capacity <= 100000`
11. 
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

