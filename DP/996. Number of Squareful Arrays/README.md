# [996. Number of Squareful Arrays (Hard)](https://leetcode.com/problems/number-of-squareful-arrays/)

<p>Given an array <code>A</code> of non-negative integers, the array is <em>squareful</em> if for every pair of adjacent elements, their sum is a perfect square.</p>

<p>Return the number of permutations of A that are squareful.&nbsp; Two permutations <code>A1</code> and <code>A2</code> differ if and only if there is some index <code>i</code> such that <code>A1[i] != A2[i]</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">[1,17,8]</span>
<strong>Output: </strong><span id="example-output-1">2</span>
<strong>Explanation: </strong>
[1,8,17] and [17,8,1] are the valid permutations.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong><span id="example-input-2-1">[2,2,2]</span>
<strong>Output: </strong><span id="example-output-2">1</span>
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 12</code></li>
	<li><code>0 &lt;= A[i] &lt;= 1e9</code></li>
</ol>

**Companies**:  
[Apple](https://leetcode.com/company/apple)

**Related Topics**:  
[Math](https://leetcode.com/tag/math/), [Backtracking](https://leetcode.com/tag/backtracking/), [Graph](https://leetcode.com/tag/graph/)

**Similar Questions**:
* [Permutations II (Medium)](https://leetcode.com/problems/permutations-ii/)

## Solution 1: BitMask

```java
// OJ: https://leetcode.com/problems/number-of-squareful-arrays/
// Author: https://leetcode.com/charlesna/
// Time: O(2^n*n^2)
// Space: O(2^n*n)
class Solution {
    public int numSquarefulPerms(int[] A) {
        int l = A.length;
        // dp[i][j]: the number of permutation when we have some numbers end with j.
        // i represent the set of numbers. j is the last number in this set.
        // because we need to know the adjacent sum, so we need to track the last number.
        int[][] dp = new int[1 << l][l];
        // init: when we have 1 number, there is one way.
        for (int j = 0; j < l; j++) {
            dp[1 << j][j] = 1;
        }
        // transfer equation: if we add a new number after the sequence, which satisfy the requirement.
        // since we add a number every time, so value of state will become larger after doing transfer. 
        // so it is safe to start from a smaller state to a larger state
        for (int i = 1; i < (1 << l); i++) {
            // loop all the values that can be added
            for (int k = 0; k < l; k++) {
                // make sure this number is not added before
                if (((i >> k) & 1) == 0) {
                    // check the last number and check the sum of these two number is a squire or not.
                    for (int j = 0; j < l; j++) {
                        if (isSquare(A[j] + A[k]) && (((i >> j) & 1) == 1)) {
                            dp[(i ^ (1 << k))][k] += dp[i][j];
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int j = 0; j < l; j++) {
            res += dp[(1 << l) - 1][j];
        }
        // since there can be duplicated numbers, we need to remove the duplicate permutations.
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            res /= per(map.get(num));
        }
        return res;
    }
    private int per(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }
    private boolean isSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
```

## Solution 2: BackTrack

```java
// OJ: https://leetcode.com/problems/number-of-squareful-arrays/
// Author: https://leetcode.com/charlesna/
// Time: O(n!)
// Space: O(n)
class Solution {
    int res;
    public int numSquarefulPerms(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        res = 0;
        helper(A, map, 0, -1);
        return res;
    }
    private void helper(int[] A, Map<Integer, Integer> map, int count, int val) {
        if (count == A.length) {
            res++;
            return;
        }
        for (int num : map.keySet()) {
            if (val == -1 || map.get(num) > 0 && isS(val + num)) {
                map.put(num, map.get(num) - 1);
                helper(A, map, count + 1, num);
                map.put(num, map.get(num) + 1);
            }
        }
    }
    private boolean isS(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}
```