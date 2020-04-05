# [368. Largest Divisible Subset (Medium)](https://leetcode.com/problems/largest-divisible-subset/)

<p>Given a set of <b>distinct</b> positive integers, find the largest subset such that every pair (S<sub>i</sub>, S<sub>j</sub>) of elements in this subset satisfies:</p>

<p>S<sub>i</sub> % S<sub>j</sub> = 0 or S<sub>j</sub> % S<sub>i</sub> = 0.</p>

<p>If there are multiple solutions, return any subset is fine.</p>

<p><strong>Example 1:</strong></p>

<div>
<pre><strong>Input: </strong><span id="example-input-1-1">[1,2,3]</span>
<strong>Output: </strong><span id="example-output-1">[1,2] </span>(of course, [1,3] will also be ok)
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong><span id="example-input-2-1">[1,2,4,8]</span>
<strong>Output: </strong><span id="example-output-2">[1,2,4,8]</span>
</pre>
</div>
</div>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Math](https://leetcode.com/tag/math/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

## Solution 

```java
// OJ: https://leetcode.com/problems/largest-divisible-subset/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n)
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int l = nums.length;
        if (l == 0) return new ArrayList<>();
        Arrays.sort(nums);
        // dp[i] is the largest size of set, which including nums[i];
        // since it is an ascending array. if a number can be divided by this number, it can be divided by others
        int[] dp = new int[l], pi = new int[l];
        int max = 1, index = 0;
        dp[0] = 1;
        pi[0] = -1;
        for (int i = 1; i < l; i++) {
            dp[i] = 1;
            pi[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    pi[i] = j;
                    if (dp[i] > max) {
                        max = dp[i];
                        index = i;
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index >= 0) {
            res.add(nums[index]);
            index = pi[index];
        }
        return res;
    }
}
```