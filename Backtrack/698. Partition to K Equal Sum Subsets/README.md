# [698. Partition to K Equal Sum Subsets (Medium)](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/)

<p>Given an array of integers <code>nums</code> and a positive integer <code>k</code>, find whether it's possible to divide this array into <code>k</code> non-empty subsets whose sums are all equal.</p>

<p>&nbsp;</p>

<p><b>Example 1:</b></p>

<pre><b>Input:</b> nums = [4, 3, 2, 3, 5, 2, 1], k = 4
<b>Output:</b> True
<b>Explanation:</b> It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ul>
	<li><code>1 &lt;= k &lt;= len(nums) &lt;= 16</code>.</li>
	<li><code>0 &lt; nums[i] &lt; 10000</code>.</li>
</ul>


**Companies**:  
[LinkedIn](https://leetcode.com/company/linkedin), [Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/), [Recursion](https://leetcode.com/tag/recursion/)

**Similar Questions**:
* [Partition Equal Subset Sum (Medium)](https://leetcode.com/problems/partition-equal-subset-sum/)

## Solution 

```java
// OJ: https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        return helper(nums.length - 1, nums, 0, k, used, sum / k);
    }
    private boolean helper(int index, int[] nums, int sum, int part, boolean[] used, int target) {
        if (part == 0) {
            return true;
        }
        if (sum == target) {
            return helper(nums.length - 1, nums, 0, part - 1, used, target);
        }
        if (sum > target || index < 0) {
            return false;
        }
        boolean res = false;
        for (int i = index; i >= 0; i--) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            res = res || helper(i - 1, nums, sum + nums[i], part, used, target);
            used[i] = false;
        }
        return res;
    }
}
```