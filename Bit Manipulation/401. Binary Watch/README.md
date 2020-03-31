# [401. Binary Watch (Easy)](https://leetcode.com/problems/binary-watch/)

<p>A binary watch has 4 LEDs on the top which represent the <b>hours</b> (<b>0-11</b>), and the 6 LEDs on the bottom represent the <b>minutes</b> (<b>0-59</b>).</p>
<p>Each LED represents a zero or one, with the least significant bit on the right.</p>
<img src="https://upload.wikimedia.org/wikipedia/commons/8/8b/Binary_clock_samui_moon.jpg" height="300">
<p>For example, the above binary watch reads "3:25".</p>

<p>Given a non-negative integer <i>n</i> which represents the number of LEDs that are currently on, return all possible times the watch could represent.</p>

<p><b>Example:</b>
</p><pre>Input: n = 1<br>Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]</pre>
<p></p>

<p><b>Note:</b><br>
</p><ul>
<li>The order of output does not matter.</li>
<li>The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".</li>
<li>The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".</li>
</ul>
<p></p>

**Companies**:  
[Apple](https://leetcode.com/company/apple)

**Related Topics**:  
[Backtracking](https://leetcode.com/tag/backtracking/), [Bit Manipulation](https://leetcode.com/tag/bit-manipulation/)

**Similar Questions**:
* [Letter Combinations of a Phone Number (Medium)](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)
* [Number of 1 Bits (Easy)](https://leetcode.com/problems/number-of-1-bits/)

## Solution 

```java
// OJ: https://leetcode.com/problems/binary-watch/
// Author: https://leetcode.com/charlesna/
// Time: O(12*60)
// Space: O(12*60*5)
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == num) {
                    String add = m < 10 ? h + ":0" + m : h + ":" + m;
                    res.add(add);
                }
            }
        }
        return res;
    }
}
```