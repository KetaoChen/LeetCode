# [456. 132 Pattern (Medium)](https://leetcode.com/problems/132-pattern/)

<p>
Given a sequence of n integers a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub>, a 132 pattern is a subsequence a<sub><b>i</b></sub>, a<sub><b>j</b></sub>, a<sub><b>k</b></sub> such
that <b>i</b> &lt; <b>j</b> &lt; <b>k</b> and a<sub><b>i</b></sub> &lt; a<sub><b>k</b></sub> &lt; a<sub><b>j</b></sub>. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.</p>

<p><b>Note:</b> n will be less than 15,000.</p>

<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> [1, 2, 3, 4]

<b>Output:</b> False

<b>Explanation:</b> There is no 132 pattern in the sequence.
</pre>
<p></p>

<p><b>Example 2:</b><br>
</p><pre><b>Input:</b> [3, 1, 4, 2]

<b>Output:</b> True

<b>Explanation:</b> There is a 132 pattern in the sequence: [1, 4, 2].
</pre>
<p></p>

<p><b>Example 3:</b><br>
</p><pre><b>Input:</b> [-1, 3, 2, 0]

<b>Output:</b> True

<b>Explanation:</b> There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
</pre>
<p></p>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Oracle](https://leetcode.com/company/oracle)

**Related Topics**:  
[Stack](https://leetcode.com/tag/stack/)

## Solution 1: Monotonic Stack 
Find the maxinum from right which is smaller than current value

```java
// OJ: https://leetcode.com/problems/132-pattern/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public boolean find132pattern(int[] nums) {
        // we just want to know the maximum value from right which is smaller than cur value
        int l = nums.length;
        if (l <= 2) {
            return false;
        }
        boolean havePeak = false;
        int right = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        stack.push(right);
        for (int i = l - 1; i >= 0; i--) {
            if (havePeak && nums[i] < right) {
                return true;
            }
            if (!stack.isEmpty() && nums[i] > stack.peek()) {
                havePeak = true;
            }    
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                right = Math.max(right, stack.pop());
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
```


## Solution 2: O(n^2) Two For Loops

```java
// OJ: https://leetcode.com/problems/132-pattern/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(1)
class Solution {
    public boolean find132pattern(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(nums[i], min);
            if (nums[i] == min) {
                continue;
            }
            for (int k = nums.length - 1; k > i; k--) {
                if (nums[k] < nums[i] && nums[k] > min) {
                    //System.out.println(min + " " + nums[i] + " " + nums[k]);
                    return true;
                }
            }
        }
        return false;
    }
}
```