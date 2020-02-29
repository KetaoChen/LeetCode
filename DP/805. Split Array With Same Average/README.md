# [805. Split Array With Same Average (Hard)](https://leetcode.com/problems/split-array-with-same-average/)

<p>In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)</p>

<p>Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.</p>

<pre><strong>Example :</strong>
<strong>Input:</strong> 
[1,2,3,4,5,6,7,8]
<strong>Output:</strong> true
<strong>Explanation: </strong>We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
</pre>

<p><strong>Note:</strong></p>

<ul>
	<li>The length of <code>A</code> will be in the range&nbsp;[1, 30].</li>
	<li><code>A[i]</code> will be in the range of <code>[0, 10000]</code>.</li>
</ul>

<p>&nbsp;</p>


**Companies**:  
[Microsoft](https://leetcode.com/company/microsoft), [Uber](https://leetcode.com/company/uber)

**Related Topics**:  
[Math](https://leetcode.com/tag/math/)

## Solution 1: Back Track & Prune

```java
// OJ: https://leetcode.com/problems/split-array-with-same-average/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public boolean splitArraySameAverage(int[] A) {
        int l = A.length, sum = 0;
        for (int num : A) {
            sum += num;
        }
        Arrays.sort(A);
        for (int i = 1; i <= l / 2; i++) {
            if (sum * (l - i) % l != 0) {
                continue;
            }
            if (find(sum * i / l, 0, i, A)) {
                return true;
            }
        }
        return false;
    }
    private boolean find(int target, int index, int left, int[] A) {
        if (left == 0 && target == 0) {
            return true;
        }
        if (index == A.length || left == 0 || target <= 0) {
            return false;
        }
        for (int i = index; i < A.length; i++) {
            if (i != index && A[i] == A[i - 1]) {
                continue;
            }
            if (find(target - A[i], i + 1, left - 1, A)) {
                return true;
            }
        }
        return false;
    }
}
```


## Solution 2: DP

```java
// OJ: https://leetcode.com/problems/split-array-with-same-average/
// Author: https://leetcode.com/charlesna/
// Time: O(n*n*sum)
// Space: O(n*sum)
class Solution {
    public boolean splitArraySameAverage(int[] A) {
        // 1. brute force, choose one subset from all the numbers and check the avg of this set and the avg of rest of numbers. 
        // however, the time complexity is 2^n, which takes too much time.
        // 2. we can find out that the avg is the same for both parts, which is also equals to the avg of all the numbers. 
        // So, if for all numbers, if we minus the average, then we just need to find out whether we can reach 0 for all numbers.
        // but it seems that the avg is a double, we need to find another way to solve this problem.
        // 3. avg1 = avg2 ==> sum1*count2 = sum2*count1
        // then we use dp[i][j] to represent whether we can use i items to reach j value.
        int sum = 0, l = A.length;
        for (int num : A) {
            sum += num;
        }
        boolean[][] dp = new boolean[l / 2 + 1][sum + 1];
        dp[0][0] = true;
        //we just need to check l/2 items. because getting i items equivalent to getting l - i items
        for (int k = 0; k < l; k++) {
            int val = A[k];
            // the reason why we need from more item and larger sum is bacause it is dependent on the fewer items and smaller val.
            for (int i = Math.min(k + 1, l / 2); i >= 1 ; i--) {
                for (int j = sum; j >= val; j--) {
                    dp[i][j] |= dp[i - 1][j - val];
                    if (dp[i][j] && j * (l - i) == (sum - j) * i) {
                        return true;
                    }
                }   
            }
        }
        return false;
    }
}
```