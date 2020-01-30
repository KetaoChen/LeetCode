---
tags:
  - leetcode
  - hash table
  - greedy
created: '2019/6/19 下午6:47:01'
difficulty: medium
---

# 1090-largest-values-from-labels

## Problem

We have a set of items: the `i`-th item has value `values[i]` and label `labels[i]`.  
  


Then, we choose a subset `S` of these items, such that:  
  


* * `|S| <= num_wanted`
* * For every label `L`, the number of items in `S` with label `L` is `<= use_limit`.
* 
Return the largest possible sum of the subset `S`.  
  


**Example 1:**  
  


```text
Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
Output: 9
Explanation: The subset chosen is the first, third, and fifth item.
```

**Example 2:**  
  


```text
Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
Output: 12
Explanation: The subset chosen is the first, second, and third item.
```

**Example 3:**  
  


```text
Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
Output: 16
Explanation: The subset chosen is the first and fourth item.
```

**Example 4:**  
  


```text
Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
Output: 24
Explanation: The subset chosen is the first, second, and fourth item.
```

**Note:**  
  


1. 2. `1 <= values.length == labels.length <= 20000`
3. 4. `0 <= values[i], labels[i] <= 20000`
5. 6. `1 <= num_wanted, use_limit <= values.length`
7. 
## Solution

java

```java
class Solution {

    class Pair {
        int val;
        int label;
        public Pair(int val, int label) {
            this.val = val;
            this.label = label;
        }
    }
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(10, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p2.val - p1.val;
            }
        }); 
        for (int i = 0; i < values.length; i++) {
            pq.offer(new Pair(values[i], labels[i]));
        }
        //map to record the number of items choisen 
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int number = 0;
        while (number < num_wanted && !pq.isEmpty()) {
            Pair max = pq.poll();
            //System.out.println(max.val + " " + max.label);
            map.put(max.label, map.getOrDefault(max.label, 0) + 1);
            if (map.get(max.label) <= use_limit) {
                res += max.val;
                number++;
            }
        }
        return res;
    }
}
​
```

