# [611. Valid Triangle Number (Medium)](https://leetcode.com/problems/valid-triangle-number/)

Given an array consists of non-negative integers,  your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> [2,2,3,4]
<b>Output:</b> 3
<b>Explanation:</b>
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
</pre>
<p></p>

<p><b>Note:</b><br>
</p><ol>
<li>The length of the given array won't exceed 1000.</li>
<li>The integers in the given array are in the range of [0, 1000].</li>
</ol>
<p></p>


**Companies**:  
[LinkedIn](https://leetcode.com/company/linkedin), [Bloomberg](https://leetcode.com/company/bloomberg)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/)

**Similar Questions**:
* [3Sum Smaller (Medium)](https://leetcode.com/problems/3sum-smaller/)

## Solution 

```java
// OJ: https://leetcode.com/problems/valid-triangle-number/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(1)
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    res += right - left;
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        return res;
    }
}
```

## Solution 2: Binary Search O(logn * n2)

```java

```