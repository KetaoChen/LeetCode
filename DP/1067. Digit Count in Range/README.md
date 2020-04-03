# [1067. Digit Count in Range (Hard)](https://leetcode.com/problems/digit-count-in-range/)

Given an integer <code>d</code> between <code>0</code> and <code>9</code>, and two positive integers <code>low</code> and <code>high</code> as lower and upper bounds, respectively. Return the number of times that <code>d</code> occurs as a digit in all integers between <code>low</code> and <code>high</code>, including the bounds <code>low</code> and <code>high</code>.
<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>d = <span id="example-input-1-1">1</span>, low = <span id="example-input-1-2">1</span>, high = <span id="example-input-1-3">13</span>
<strong>Output: </strong><span id="example-output-1">6</span>
<strong>Explanation: </strong>
The digit <code>d=1</code> occurs <code>6</code> times in <code>1,10,11,12,13</code>. Note that the digit <code>d=1</code> occurs twice in the number <code>11</code>.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong>d = <span id="example-input-2-1">3</span>, low = <span id="example-input-2-2">100</span>, high = <span id="example-input-2-3">250</span>
<strong>Output: </strong><span id="example-output-2">35</span>
<strong>Explanation: </strong>
The digit <code>d=3</code> occurs <code>35</code> times in <code>103,113,123,130,131,...,238,239,243</code>.
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= d &lt;= 9</code></li>
	<li><code>1 &lt;= low &lt;= high &lt;= 2¡Á10^8</code></li>
</ol>
</div>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Math](https://leetcode.com/tag/math/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)

**Similar Questions**:
* [Number of Digit One (Hard)](https://leetcode.com/problems/number-of-digit-one/)

## Solution 

```java
// OJ: https://leetcode.com/problems/digit-count-in-range/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int digitsCount(int d, int low, int high) {
        return count(high, d) -  count(low - 1, d);
    }
    private int count(int num, int d) {
        if (num == 0 && d == 0) {
            return 1;
        }
        if (num <= 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 10);
            num /= 10;
        }
        int res = 0, count = 0, l = list.size();
        int[] f = new int[l + 1];
        f[1] = 1;
        for (int i = 2; i <= l; i++) {
            f[i] = i * (int)Math.pow(10, i - 1);
        }
        for (int i = l - 1; i >= 0; i--) {
            int val = list.get(i);
            for (int k = 0; k < val; k++) {
                res += f[i];
                res += count * Math.pow(10, i);
                if (k == d) {
                    res += Math.pow(10, i);
                }
            }
            if (val == d) count++;
        }
        // System.out.println(res);
        if (d == 0) {
            for (int i = 1; i < l; i++) {
                res -= Math.pow(10, i);
            }
        }
        // System.out.println(res);
        return res + count;
    }
}
```