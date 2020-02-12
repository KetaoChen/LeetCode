# [201. Bitwise AND of Numbers Range (Medium)](https://leetcode.com/problems/bitwise-and-of-numbers-range/)

<p>Given a range [m, n] where 0 &lt;= m &lt;= n &lt;= 2147483647, return the bitwise AND of all numbers in this range, inclusive.</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> [5,7]
<strong>Output:</strong> 4
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> [0,1]
<strong>Output:</strong> 0</pre>

**Related Topics**:  
[Bit Manipulation](https://leetcode.com/tag/bit-manipulation/)

## Solution 

```java
// OJ: https://leetcode.com/problems/bitwise-and-of-numbers-range/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int d = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            d++;
        }
        return m << d;
    }
}
```