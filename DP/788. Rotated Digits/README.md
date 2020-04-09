# [788. Rotated Digits (Easy)](https://leetcode.com/problems/rotated-digits/)

<p>X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.&nbsp; Each digit must be rotated - we cannot choose to leave it alone.</p>

<p>A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other (on this case they are rotated in a different direction, in other words 2 or 5 gets mirrored); 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.</p>

<p>Now&nbsp;given a positive number <code>N</code>, how many numbers X from <code>1</code> to <code>N</code> are good?</p>

<pre><strong>Example:</strong>
<strong>Input:</strong> 10
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
</pre>

<p><strong>Note:</strong></p>

<ul>
	<li>N&nbsp; will be in range <code>[1, 10000]</code>.</li>
</ul>


**Companies**:  
[Facebook](https://leetcode.com/company/facebook)

**Related Topics**:  
[String](https://leetcode.com/tag/string/)

## Solution 

```java
// OJ: https://leetcode.com/problems/rotated-digits/
// Author: https://leetcode.com/charlesna/
// Time: O(logN)
// Space: O(1)
class Solution {
    public int rotatedDigits(int N) {
        int[] valid = {0, 1, 8};
        int[] rotate = {2, 5, 6, 9};
        Set<Integer> set = new HashSet<>();
        Set<Integer> good = new HashSet<>();
        for (int v : valid) set.add(v);
        for (int r : rotate) good.add(r);
        // per[i] is the number permutations with length i, if we can choose any number from valid and rotate.
        int[] per = new int[6];
        per[0] = 1;
        for (int i = 1; i < 6; i++) per[i] = per[i - 1] * 7;
        // dp[i] is the number of permutations with length i, if we have to choose with at least 1 good number. 
        int[] dp = new int[6]; 
        for (int i = 1; i < 6; i++) {
            for (int pickGood = 1; pickGood <= i; pickGood++) {
                dp[i] += combo(i, pickGood) * Math.pow(4, pickGood) * Math.pow(3, (i - pickGood));
            }
        }        
        // get all the digits
        List<Integer> list = new ArrayList<>();
        while (N > 0) {
            list.add(N % 10);
            N /= 10;
        }
        int res = 0, l = list.size();
        boolean hasGood = false;
        // start from the first digit
        for (int i = l - 1; i >= 0; i--) {
            int val = list.get(i);
            for (int k = 0; k < val; k++) {
                if (!set.contains(k) && !good.contains(k)) continue;
                if (hasGood) {
                    res += per[i];
                    continue;
                }
                if (good.contains(k)) res += per[i];
                else res += dp[i];
            }
            if (!set.contains(val) && !good.contains(val)) return res;
            if (good.contains(val)) hasGood = true;
        }
        if (hasGood) res++;
        return res;
    }
    private int combo(int i, int j) {
        int res = 1;
        for (int k = 0; k < j; k++) {
            res *= (i - k);
            res /= (k + 1);
        }
        return res;
    }
}
```