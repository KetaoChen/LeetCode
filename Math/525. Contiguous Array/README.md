# [525. Contiguous Array (Medium)](https://leetcode.com/problems/contiguous-array/)

<p>Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1. </p>


<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> [0,1]
<b>Output:</b> 2
<b>Explanation:</b> [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
</pre>
<p></p>

<p><b>Example 2:</b><br>
</p><pre><b>Input:</b> [0,1,0]
<b>Output:</b> 2
<b>Explanation:</b> [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
</pre>
<p></p>

<p><b>Note:</b>
The length of the given binary array will not exceed 50,000.
</p>

**Companies**:  
[Quora](https://leetcode.com/company/quora), [Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Hash Table](https://leetcode.com/tag/hash-table/)

**Similar Questions**:
* [Maximum Size Subarray Sum Equals k (Medium)](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/)

## Solution 

```java
// OJ: https://leetcode.com/problems/contiguous-array/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
            else {
                res = Math.max(res, i - map.get(sum));
            }
        }
        return res;
    }
}
```