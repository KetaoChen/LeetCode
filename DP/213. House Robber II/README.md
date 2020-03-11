# [213. House Robber II (Medium)](https://leetcode.com/problems/house-robber-ii/)

<p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are <strong>arranged in a circle.</strong> That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and&nbsp;<b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>

<p>Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight <strong>without alerting the police</strong>.</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> [2,3,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
&nbsp;            because they are adjacent houses.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
&nbsp;            Total amount you can rob = 1 + 3 = 4.</pre>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [House Robber (Easy)](https://leetcode.com/problems/house-robber/)
* [Paint House (Easy)](https://leetcode.com/problems/paint-house/)
* [Paint Fence (Easy)](https://leetcode.com/problems/paint-fence/)
* [House Robber III (Medium)](https://leetcode.com/problems/house-robber-iii/)
* [Non-negative Integers without Consecutive Ones (Hard)](https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/)
* [Coin Path (Hard)](https://leetcode.com/problems/coin-path/)

## Solution 

```java
// OJ: https://leetcode.com/problems/house-robber-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int rob(int[] nums) {
        // rob the first or not.
        // if rob the first, can not rob the last one.
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(helper(nums, 0, nums.length - 2), helper(nums, 1, nums.length - 1));
    }
    private int helper(int[] nums, int first, int last) {
        int rob = 0;
        int notRob = 0;
        for (int i = first; i <= last; i++) {
            int temp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(notRob, temp);
        }
        return Math.max(rob, notRob);
    }
}
```