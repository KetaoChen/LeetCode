# [260. Single Number III (Medium)](https://leetcode.com/problems/single-number-iii/)

<p>Given an array of numbers <code>nums</code>, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.</p>

<p><strong>Example:</strong></p>

<pre><strong>Input:</strong>  <code>[1,2,1,3,2,5]</code>
<strong>Output:</strong> <code>[3,5]</code></pre>

<p><b>Note</b>:</p>

<ol>
	<li>The order of the result is not important. So in the above example, <code>[5, 3]</code> is also correct.</li>
	<li>Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?</li>
</ol>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Bit Manipulation](https://leetcode.com/tag/bit-manipulation/)

**Similar Questions**:
* [Single Number (Easy)](https://leetcode.com/problems/single-number/)
* [Single Number II (Medium)](https://leetcode.com/problems/single-number-ii/)

## Solution 

```java
// OJ: https://leetcode.com/problems/single-number-iii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int[] singleNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        int k = 0;
        while (((sum >> k) & 1) == 0) {
            k++;
        }
        int s = 0;
        for (int num : nums) {
            if (((num >> k) & 1) == 1) {
                s ^= num;
            }
        }
        return new int[]{s, sum ^ s};
    }
}
```