# [233. Number of Digit One (Hard)](https://leetcode.com/problems/number-of-digit-one/)

<p>Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.</p>

<p><strong>Example:</strong></p>

<pre><strong>Input:</strong> 13
<strong>Output:</strong> 6 
<strong>Explanation: </strong>Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
</pre>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Apple](https://leetcode.com/company/apple)

**Related Topics**:  
[Math](https://leetcode.com/tag/math/)

**Similar Questions**:
* [Factorial Trailing Zeroes (Easy)](https://leetcode.com/problems/factorial-trailing-zeroes/)
* [Digit Count in Range (Hard)](https://leetcode.com/problems/digit-count-in-range/)

## Solution 

```java
// OJ: https://leetcode.com/problems/number-of-digit-one/
// Author: https://leetcode.com/charlesna/
// Time: O(l)
// Space: O(l)
class Solution {
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        //          X XXXXXX
        // 0...X-1   X
        // if not on the upper bound, we can choose any number for the rest of digits.
        // suppose we have 5 digit, XXXXX, we can choose 0-9 for each digit. 
        // the number of combo is 10^4*5. there are 10^4 choices when we choose 1 for 
        int res = 0, count = 0, l = list.size();
        int[] f = new int[l + 1];
        f[1] = 1;
        for (int i = 2; i <= l; i++) {
            f[i] = i * (int) Math.pow(10.0, i - 1);
            // System.out.println(i + " " + f[i]);
        }
        for (int i = l - 1; i >= 0; i--) {
            int val = list.get(i);
            for (int k = 0; k < val; k++) {
                res += f[i];
                res += count * Math.pow(10, i);
                if (k == 1) {
                    res += Math.pow(10, i);
                }
            }
            if (val == 1) count++;
        }
        res += count;
        return res;
    }
}
```