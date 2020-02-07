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