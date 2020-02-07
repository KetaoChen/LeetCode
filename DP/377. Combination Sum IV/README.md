# [377. Combination Sum IV (Medium)](https://leetcode.com/problems/combination-sum-iv/)

<p>Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.</p>

<p><b>Example:</b></p>

<pre><i><b>nums</b></i> = [1, 2, 3]
<i><b>target</b></i> = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is <i><b>7</b></i>.
</pre>

<p>&nbsp;</p>

<p><b>Follow up:</b><br>
What if negative numbers are allowed in the given array?<br>
How does it change the problem?<br>
What limitation we need to add to the question to allow negative numbers?</p>

<p><b>Credits:</b><br>
Special thanks to <a href="https://leetcode.com/pbrother/">@pbrother</a> for adding this problem and creating all test cases.</p>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Combination Sum (Medium)](https://leetcode.com/problems/combination-sum/)

## Solution 

```java
// OJ: https://leetcode.com/problems/combination-sum-iv/
// Author: https://leetcode.com/charlesna/
// Time: O(target*n)
// Space: O(target)
class Solution {
    public int combinationSum4(int[] nums, int target) {
        //dp[i] is the number of ways to get i.        
        int[] dp = new int[target + 1];
        //initialize
        dp[0] = 1;
        //for each target;
        //we 
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i < num) {
                    break;
                }
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
```