---
tags: ["leetcode","hash table","greedy"]
created: "2019/6/19 下午6:47:01"
difficulty: "medium"
---

# [1090-largest-values-from-labels](https://leetcode.com/problems/largest-values-from-labels/)

## Problem
<div><p>We have a set of items: the <code>i</code>-th item has value <code>values[i]</code> and label <code>labels[i]</code>.</p><br><br><p>Then, we choose&nbsp;a subset <code>S</code> of these items, such that:</p><br><br><ul><br>	<li><code>|S| &lt;= num_wanted</code></li><br>	<li>For every label <code>L</code>, the number of items in <code>S</code> with&nbsp;label <code>L</code> is <code>&lt;= use_limit</code>.</li><br></ul><br><br><p>Return the largest possible sum of the subset <code>S</code>.</p><br><br><p>&nbsp;</p><br><br><div><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input: </strong>values = <span id="example-input-1-1">[5,4,3,2,1]</span>, labels = <span id="example-input-1-2">[1,1,2,2,3]</span>, <code>num_wanted </code>= <span id="example-input-1-3">3</span>, use_limit = <span id="example-input-1-4">1</span><br><strong>Output: </strong><span id="example-output-1">9</span><br><strong>Explanation: </strong>The subset chosen is the first, third, and fifth item.<br></pre><br><br><div><br><p><strong>Example 2:</strong></p><br><br><pre><strong>Input: </strong>values = <span id="example-input-2-1">[5,4,3,2,1]</span>, labels = <span id="example-input-2-2">[1,3,3,3,2]</span>, <code>num_wanted </code>= <span id="example-input-2-3">3</span>, use_limit = <span id="example-input-2-4">2</span><br><strong>Output: </strong><span id="example-output-2">12</span><br><strong>Explanation: </strong>The subset chosen is the first, second, and third item.<br></pre><br><br><div><br><p><strong>Example 3:</strong></p><br><br><pre><strong>Input: </strong>values = <span id="example-input-3-1">[9,8,8,7,6]</span>, labels = <span id="example-input-3-2">[0,0,0,1,1]</span>, <code>num_wanted </code>= <span id="example-input-3-3">3</span>, use_limit = <span id="example-input-3-4">1</span><br><strong>Output:</strong>&nbsp;16<br><strong>Explanation: </strong>The subset chosen is the first and fourth item.<br></pre><br><br><div><br><p><strong>Example 4:</strong></p><br><br><pre><strong>Input: </strong>values = <span id="example-input-4-1">[9,8,8,7,6]</span>, labels = <span id="example-input-4-2">[0,0,0,1,1]</span>, <code>num_wanted </code>= <span id="example-input-4-3">3</span>, use_limit = <span id="example-input-4-4">2</span><br><strong>Output: </strong><span id="example-output-4">24</span><br><strong>Explanation: </strong>The subset chosen is the first, second, and fourth item.<br></pre><br><br><p>&nbsp;</p><br><br><p><strong>Note:</strong></p><br><br><ol><br>	<li><code>1 &lt;= values.length == labels.length &lt;= 20000</code></li><br>	<li><code>0 &lt;= values[i], labels[i]&nbsp;&lt;= 20000</code></li><br>	<li><code>1 &lt;= num_wanted, use_limit&nbsp;&lt;= values.length</code></li><br></ol><br></div><br></div><br></div><br></div></div>

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
