# [523. Continuous Subarray Sum (Medium)](https://leetcode.com/problems/continuous-subarray-sum/)

<p>Given a list of <b>non-negative</b> numbers and a target <b>integer</b> k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of <b>k</b>, that is, sums up to n*k where n is also an <b>integer</b>.</p>

<p>&nbsp;</p>

<p><b>Example 1:</b></p>

<pre><b>Input:</b> [23, 2, 4, 6, 7],  k=6
<b>Output:</b> True
<b>Explanation:</b> Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
</pre>

<p><b>Example 2:</b></p>

<pre><b>Input:</b> [23, 2, 6, 4, 7],  k=6
<b>Output:</b> True
<b>Explanation:</b> Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li>The length of the array won't exceed 10,000.</li>
	<li>You may assume the sum of all the numbers is in the range of a signed 32-bit integer.</li>
</ol>


**Companies**:  
[Facebook](https://leetcode.com/company/facebook), [Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Math](https://leetcode.com/tag/math/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Subarray Sum Equals K (Medium)](https://leetcode.com/problems/subarray-sum-equals-k/)

## Solution 

```java
// OJ: https://leetcode.com/problems/continuous-subarray-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(k)
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // 1.just need to check
        // 2. at least two
        // if we have a prefixSum[i] % k == mod && curPrefixSum % k == mod && cur != i+1 nums[i-cur]
        // 3. 0 is the also a multiple of k.
		// 4. k can be negative numbers
		
        // [23,2,6,4,7], k=6;
        //  sum = 35
        //  set: 5,1,1,5
        if (k == 0) {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1] && nums[i] == 0) {
                    return true;
                }
            }
            return false;
        }
        k = Math.abs(k);
        int sum = 0;
        // keep the prev mod. 
        int prev = 0;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int num : nums) {
            sum += num;
            int mod = sum % k;
            if (set.contains(mod) && prev != mod) {
                return true;
            }
            prev = set.contains(mod) ? -1 : mod;
            set.add(mod);
        }
        return false;
    }
}
```