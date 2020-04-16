// OJ: https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int mctFromLeafValues(int[] arr) {
        // the order of number can not change.
        // each number need to multiply with one number. 
        // we want to minimize the sum of all the product. 
        int res = 0, l = arr.length;
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            while (!stack.isEmpty() && num >= stack.peek()) {
                int cur = stack.pop();
                int left = stack.isEmpty() ? 20 : stack.peek();
                res += cur * Math.min(num, left);
            }
            stack.push(num);
        }
        while (stack.size() > 1) res += stack.pop() * stack.peek();
        return res;
    }
}