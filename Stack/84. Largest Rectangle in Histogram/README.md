# [84. Largest Rectangle in Histogram (Hard)](https://leetcode.com/problems/largest-rectangle-in-histogram/)

<p>Given <em>n</em> non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.</p>

<p>&nbsp;</p>

<p><img src="https://assets.leetcode.com/uploads/2018/10/12/histogram.png" style="width: 188px; height: 204px;"><br>
<small>Above is a histogram where width of each bar is 1, given height = <code>[2,1,5,6,2,3]</code>.</small></p>

<p>&nbsp;</p>

<p><img src="https://assets.leetcode.com/uploads/2018/10/12/histogram_area.png" style="width: 188px; height: 204px;"><br>
<small>The largest rectangle is shown in the shaded area, which has area = <code>10</code> unit.</small></p>

<p>&nbsp;</p>

<p><strong>Example:</strong></p>

<pre><strong>Input:</strong> [2,1,5,6,2,3]
<strong>Output:</strong> 10
</pre>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Microsoft](https://leetcode.com/company/microsoft), [Google](https://leetcode.com/company/google), [Bloomberg](https://leetcode.com/company/bloomberg), [Flipkart](https://leetcode.com/company/flipkart)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/), [Stack](https://leetcode.com/tag/stack/)

**Similar Questions**:
* [Maximal Rectangle (Hard)](https://leetcode.com/problems/maximal-rectangle/)

## Solution 1 : Monotonic Stack 1 Pass

```java
// OJ: https://leetcode.com/problems/largest-rectangle-in-histogram/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        //stack is used to store the index.
        //the heights corresponding to the index in the stack is increasing.
            //so the everytime
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int index = stack.pop();
                //the index of first number smaller than current on the left is the peek of the stack
                int left = stack.isEmpty() ? -1 : stack.peek();
                //the index of first number smaller than current on the right is i;
                res = Math.max(res, heights[index] * (i - left - 1));
            }
            stack.push(i);
        }
        return res;
    }
}
```

## Solution 2 : DP 3 Pass

```java
// OJ: https://leetcode.com/problems/largest-rectangle-in-histogram/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        //find the first number smaller on the right
        int l = heights.length;
        int[] smallR = new int[l];
        //use dp to find the index of first number smaller than current number
        //start from the right most number
        smallR[l - 1] = l;
        for (int i = l - 2; i >= 0; i--) {
            int index = i + 1;
            while (index < l && heights[index] >=  heights[i]) {
                index = smallR[index];
            }
            smallR[i] = index;
        }
        int[] smallL = new int[l];
        smallL[0] = -1;
        for (int i = 1; i < l; i++) {
            int index = i - 1;
            while (index >= 0 && heights[index] >= heights[i]) {
                index = smallL[index];
            }
            smallL[i] = index;
        }
        int res = 0;
        for (int i = 0; i < l; i++) {
            res = Math.max(res, heights[i] * (smallR[i] - smallL[i] - 1));
        }
        return res;
    }
}
```

## Solution 3 : Monotonic Stack 3 Pass

```java
// OJ: https://leetcode.com/problems/largest-rectangle-in-histogram/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        //find the first number smaller on the right
        int l = heights.length;
        int[] smallR = new int[l];
        //use monotonic stack instead of dp to find the index of first number smaller than current number
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < l; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                smallR[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            smallR[stack.pop()] = l;
        }
        int[] smallL = new int[l];
        for (int i = l - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                smallL[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            smallL[stack.pop()] = -1;
        }
        int res = 0;
        for (int i = 0; i < l; i++) {
            res = Math.max(res, heights[i] * (smallR[i] - smallL[i] - 1));
        }
        return res;
    }
}
```