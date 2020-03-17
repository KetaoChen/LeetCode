# [740. Delete and Earn (Medium)](https://leetcode.com/problems/delete-and-earn/)

<p>Given an array <code>nums</code> of integers, you can perform operations on the array.</p>

<p>In each operation, you pick any <code>nums[i]</code> and delete it to earn <code>nums[i]</code> points. After, you must delete <b>every</b> element equal to <code>nums[i] - 1</code> or <code>nums[i] + 1</code>.</p>

<p>You start with 0 points. Return the maximum number of points you can earn by applying such operations.</p>

<p><b>Example 1:</b></p>

<pre><b>Input:</b> nums = [3, 4, 2]
<b>Output:</b> 6
<b>Explanation:</b> 
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.
</pre>

<p>&nbsp;</p>

<p><b>Example 2:</b></p>

<pre><b>Input:</b> nums = [2, 2, 3, 3, 3, 4]
<b>Output:</b> 9
<b>Explanation:</b> 
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ul>
	<li>The length of <code>nums</code> is at most <code>20000</code>.</li>
	<li>Each element <code>nums[i]</code> is an integer in the range <code>[1, 10000]</code>.</li>
</ul>

<p>&nbsp;</p>


**Companies**:  
[Pocket Gems](https://leetcode.com/company/pocket-gems)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [House Robber (Easy)](https://leetcode.com/problems/house-robber/)

## Solution 

```java
// OJ: https://leetcode.com/problems/delete-and-earn/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10001];
        for (int num : nums) {
            count[num] += num;
        }
        // if we pick i, then i-1 and i+1 can not be picked.
        int[] dp = new int[10001];
        // dp[i] is the maximum we can get for first i element.
        // if we get i, then we can not get i+1.
        int pick = 0, notPick = 0;
        for (int i = 1; i <= 10000; i++) {
            int temp = notPick;
            notPick = Math.max(notPick, pick);
            pick = temp + count[i];
        }
        return Math.max(pick, notPick);
    }
}
```