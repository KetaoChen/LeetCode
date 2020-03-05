# [494. Target Sum (Medium)](https://leetcode.com/problems/target-sum/)

<p>
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols <code>+</code> and <code>-</code>. For each integer, you should choose one from <code>+</code> and <code>-</code> as its new symbol.
</p> 

<p>Find out how many ways to assign symbols to make sum of integers equal to target S.  
</p>

<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> nums is [1, 1, 1, 1, 1], S is 3. 
<b>Output:</b> 5
<b>Explanation:</b> 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
</pre>
<p></p>

<p><b>Note:</b><br>
</p><ol>
<li>The length of the given array is positive and will not exceed 20. </li>
<li>The sum of elements in the given array will not exceed 1000.</li>
<li>Your output answer is guaranteed to be fitted in a 32-bit integer.</li>
</ol>
<p></p>

**Companies**:  
[Facebook](https://leetcode.com/company/facebook)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/), [Depth-first Search](https://leetcode.com/tag/depth-first-search/)

**Similar Questions**:
* [Expression Add Operators (Hard)](https://leetcode.com/problems/expression-add-operators/)

## Solution 1: DP

```java
// OJ: https://leetcode.com/problems/target-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n*sum)
// Space: O(n*sum)
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (S > 1000) {
            return 0;
        }
        // dp[i][j] is for first ith numbers, the number of ways to have sum j
        int l = nums.length;
        int[][] dp = new int[l + 1][2001];
        // init: there is 1 way to have 0 for 0 numebers.
        dp[0][1000] = 1;
        // dp[i][j] = we can add this number or minus this number
        // dp[i][j] = dp[i - 1][j + num] + dp[i - 1][j - num];
        for (int i = 1; i <= l; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= 2000; j++) {
                if (j + num <= 2000) {
                    dp[i][j] += dp[i - 1][j + num];
                }
                if (j - num >= 0) {
                    dp[i][j ] += dp[i - 1][j - num];
                }
            }
        }
        return dp[l][1000 + S];
    }
}
```

## Solution 2: DFS & Memo

```java
// OJ: https://leetcode.com/problems/target-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n*sum)
// Space: O(n*sum)
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        Map<Integer, Integer>[] memo = new HashMap[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = new HashMap<>();
        }
        return helper(nums, 0, S, memo);
    }
    private int helper(int[] nums, int index, int S, Map<Integer, Integer>[] memo) {
        int res = 0;
        if (index == nums.length) {
            return S == 0 ? 1 : 0;
        }
        if (memo[index].containsKey(S)) {
            return memo[index].get(S);
        }
        res += helper(nums, index + 1, S - nums[index], memo) + 
            helper(nums, index + 1, S + nums[index], memo);
        memo[index].put(S, res);
        return res;
    }
}
```