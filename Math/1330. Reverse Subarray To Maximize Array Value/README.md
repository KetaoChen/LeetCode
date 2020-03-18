# [1330. Reverse Subarray To Maximize Array Value (Hard)](https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/)

<p>You are given an integer array <code>nums</code>. The <em>value</em> of this array is defined as the sum of <code>|nums[i]-nums[i+1]|</code>&nbsp;for all&nbsp;<code>0 &lt;= i &lt; nums.length-1</code>.</p>

<p>You are allowed to select any subarray of the given array and reverse it. You can perform this operation <strong>only once</strong>.</p>

<p>Find maximum possible value of the final array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> nums = [2,3,1,5,4]
<strong>Output:</strong> 10
<b>Explanation: </b>By reversing the subarray [3,1,5] the array becomes [2,5,1,3,4] whose value is 10.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> nums = [2,4,9,24,2,1,10]
<strong>Output:</strong> 68
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3*10^4</code></li>
	<li><code>-10^5 &lt;= nums[i] &lt;= 10^5</code></li>
</ul>

**Companies**:  
[Codenation](https://leetcode.com/company/codenation)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/), [Math](https://leetcode.com/tag/math/)

## Solution 1: Absolute value to Distance

```java
// OJ: https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int maxValueAfterReverse(int[] nums) {
        //******a[b****c]d
        //delta = |a-c|+|b-d| - |a-b|-|c-d| we want to maximize this.
        //this can regarded as four distance on a line.
        //if (ab and cd have intersection, delta is small or equal to 0)
        //so we only care about the situation that ab and cd has no interseciton and the delta=abs(min(c,d) - max(a,b)).
        int res = 0, l = nums.length;;
        for (int i = 0; i < l - 1; i++) {
            res += Math.abs(nums[i] - nums[i + 1]);
        }
        int d = 0;
        // if the first element is swapped.
        for (int i = 0; i < l - 1; i++) {
            d = Math.max(d, Math.abs(nums[i + 1] - nums[0]) - Math.abs(nums[i + 1] - nums[i]));
        }
        // if the last element is swapped.
        for (int i = 1; i < l; i++) {
            d = Math.max(d, Math.abs(nums[i - 1] - nums[l - 1]) - Math.abs(nums[i - 1] - nums[i]));
        }
        // else 
        // *****a***b******c******d****
        // delta = 2*(min(c,d) - max(a,b));
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < l - 1; i++) {
            int curMax = Math.max(nums[i], nums[i + 1]);
            int curMin = Math.min(nums[i], nums[i + 1]);
            if (i == 0) {
                min = curMin;
                max = curMax;
                continue;
            }
            d = Math.max(d, 2 * Math.max(min - curMax, curMin - max));
            max = Math.min(max, curMax);
            min = Math.max(min, curMin);
        }
        return res + d;
    }
}
```