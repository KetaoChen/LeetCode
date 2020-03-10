# [347. Top K Frequent Elements (Medium)](https://leetcode.com/problems/top-k-frequent-elements/)

<p>Given a non-empty array of integers, return the <b><i>k</i></b> most frequent elements.</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>nums = <span id="example-input-1-1">[1,1,1,2,2,3]</span>, k = <span id="example-input-1-2">2</span>
<strong>Output: </strong><span id="example-output-1">[1,2]</span>
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong>nums = <span id="example-input-2-1">[1]</span>, k = <span id="example-input-2-2">1</span>
<strong>Output: </strong><span id="example-output-2">[1]</span></pre>
</div>

<p><b>Note: </b></p>

<ul>
	<li>You may assume <i>k</i> is always valid, 1 ¡Ü <i>k</i> ¡Ü number of unique elements.</li>
	<li>Your algorithm's time complexity <b>must be</b> better than O(<i>n</i> log <i>n</i>), where <i>n</i> is the array's size.</li>
</ul>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Facebook](https://leetcode.com/company/facebook), [Microsoft](https://leetcode.com/company/microsoft), [Yelp](https://leetcode.com/company/yelp), [Uber](https://leetcode.com/company/uber), [Google](https://leetcode.com/company/google), [Apple](https://leetcode.com/company/apple), [Snapchat](https://leetcode.com/company/snapchat), [Oracle](https://leetcode.com/company/oracle)

**Related Topics**:  
[Hash Table](https://leetcode.com/tag/hash-table/), [Heap](https://leetcode.com/tag/heap/)

**Similar Questions**:
* [Word Frequency (Medium)](https://leetcode.com/problems/word-frequency/)
* [Kth Largest Element in an Array (Medium)](https://leetcode.com/problems/kth-largest-element-in-an-array/)
* [Sort Characters By Frequency (Medium)](https://leetcode.com/problems/sort-characters-by-frequency/)
* [Split Array into Consecutive Subsequences (Medium)](https://leetcode.com/problems/split-array-into-consecutive-subsequences/)
* [Top K Frequent Words (Medium)](https://leetcode.com/problems/top-k-frequent-words/)
* [K Closest Points to Origin (Medium)](https://leetcode.com/problems/k-closest-points-to-origin/)

## Solution 

```java
// OJ: https://leetcode.com/problems/top-k-frequent-elements/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // get freq
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // count the number of numbers by freq
        int[] count = new int[nums.length + 1];
        for (int num : map.keySet()) {
            count[map.get(num)]++;
        }
        // find the least freq to be included in the res
        int leastF = nums.length;
        int sum = 0;
        for (int i = nums.length; i >= 0; i--) {
            sum += count[i];
            if (sum == k) {
                leastF = i;
                break;
            }
        }
        // get the res
        List<Integer> res = new ArrayList<>();
        for (int num :  map.keySet()) {
            if (map.get(num) >= leastF) {
                res.add(num);
            }
        }
        return res;
    }
}
```