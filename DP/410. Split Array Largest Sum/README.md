# [410. Split Array Largest Sum (Hard)](https://leetcode.com/problems/split-array-largest-sum/)

<p>Given an array which consists of non-negative integers and an integer <i>m</i>, you can split the array into <i>m</i> non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these <i>m</i> subarrays.
</p>

<p><b>Note:</b><br>
If <i>n</i> is the length of array, assume the following constraints are satisfied:
</p><ul>
<li>1 ¡Ü <i>n</i> ¡Ü 1000</li>
<li>1 ¡Ü <i>m</i> ¡Ü min(50, <i>n</i>)</li>
</ul>
<p></p>

<p><b>Examples: </b>
</p><pre>Input:
<b>nums</b> = [7,2,5,10,8]
<b>m</b> = 2

Output:
18

Explanation:
There are four ways to split <b>nums</b> into two subarrays.
The best way is to split it into <b>[7,2,5]</b> and <b>[10,8]</b>,
where the largest sum among the two subarrays is only 18.
</pre>
<p></p>

**Companies**:  
[Google](https://leetcode.com/company/google), [Amazon](https://leetcode.com/company/amazon), [Facebook](https://leetcode.com/company/facebook)

**Related Topics**:  
[Binary Search](https://leetcode.com/tag/binary-search/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 1: Binary Search Ans

```java
// OJ: https://leetcode.com/problems/split-array-largest-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(l*log(sum)
// Space: O(1)
class Solution {
    public int splitArray(int[] nums, int m) {
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (canSplit(nums, mid, m)) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
    private boolean canSplit(int[] nums, int target, int m) {
        // System.out.println(target);
        int res = 0, index = 0, l = nums.length;
        long sum = 0;
        while (index < l) {
            boolean add = false;
            while (index < l && sum + nums[index] <= target) {
                sum += nums[index++];
                add = true;
            }
            if (!add) {
                return false;
            }
            res++;
            sum = 0;
        }
        return res <= m;
    }
}
```

## Solution 2: Interval DP

```java
// OJ: https://leetcode.com/problems/split-array-largest-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n*n*m)
// Space: O(n*m)
class Solution {
    public int splitArray(int[] nums, int m) {
        //dp[i][j] is the min sum for the first jth numbers, when breaking into i parts
        //dp[i][j] = max(dp[i - 1][k], sum[k+1][j])
        int l = nums.length, sum = 0;
        int[][] dp = new int[m][l];
        //init when there is only one part
        for (int i = 0; i < l; i++) {
            sum += nums[i];
            dp[0][i] = sum;
        }
        for (int i = 1; i < m; i++) {
            for (int j = i; j < l; j++) {
                sum = 0;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = j; k >= i; k--) {
                    sum += nums[k];
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k - 1], sum));
                }
            }
        }
        return dp[m - 1][l - 1];
    }
}
```