# [679. 24 Game (Hard)](https://leetcode.com/problems/24-game/)

<p>
You have 4 cards each containing a number from 1 to 9.  You need to judge whether they could operated through <code>*</code>, <code>/</code>, <code>+</code>, <code>-</code>, <code>(</code>, <code>)</code> to get the value of 24.
</p>

<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> [4, 1, 8, 7]
<b>Output:</b> True
<b>Explanation:</b> (8-4) * (7-1) = 24
</pre>
<p></p>

<p><b>Example 2:</b><br>
</p><pre><b>Input:</b> [1, 2, 1, 2]
<b>Output:</b> False
</pre>
<p></p>

<p><b>Note:</b><br>
</p><ol>
<li>The division operator <code>/</code> represents real division, not integer division.  For example, 4 / (1 - 2/3) = 12.</li>
<li>Every operation done is between two numbers.  In particular, we cannot use <code>-</code> as a unary operator.  For example, with <code>[1, 1, 1, 1]</code> as input, the expression <code>-1 - 1 - 1 - 1</code> is not allowed.</li>
<li>You cannot concatenate numbers together.  For example, if the input is <code>[1, 2, 1, 2]</code>, we cannot write this as 12 + 12.</li>
</ol>
<p></p>
<p></p>

**Companies**:  
[Google](https://leetcode.com/company/google), [Microsoft](https://leetcode.com/company/microsoft)

**Related Topics**:  
[Depth-first Search](https://leetcode.com/tag/depth-first-search/)

## Solution 

```java
// OJ: https://leetcode.com/problems/24-game/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    final double delta = 0.0001;
    boolean res;
    int count;
    public boolean judgePoint24(int[] nums) {
        count = 0;
        res = false;
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        helper(list);
        return res;
    }    
    private void helper(List<Double> list) {
        if (res) return;
        if (list.size() == 1) {
            if (Math.abs(list.get(0) - 24) < delta) {
                res = true;
            }
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                    if (k == i || k == j) continue;
                    next.add(list.get(k));
                }
                double a = list.get(i), b = list.get(j);
                List<Double> add = new ArrayList<>();
                add.addAll(Arrays.asList(new Double[]{a + b, a - b, b - a, a * b}));
                if (b > delta) add.add(a / b);
                if (a > delta) add.add(b / a);
                for (double num : add) {
                    next.add(num);
                    helper(next);
                    next.remove(next.size() - 1);
                }
            }
        }
    }
}
```