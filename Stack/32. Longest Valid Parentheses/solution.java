// OJ: https://leetcode.com/problems/longest-valid-parentheses/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0, l = s.length();
        int[] d = new int[l + 1];
        stack.push(0);
        for (int i = 1; i <= l; i++) {
            d[i] = s.charAt(i - 1) == '(' ? d[i - 1] + 1 : d[i - 1] - 1;
            int end = i;
            while (!stack.isEmpty() && d[i] <= d[stack.peek()]) {
                end = stack.pop();
            }
            if (d[i] == d[end]) {
                stack.push(end);
                res = Math.max(res, i - end);
            }
            else {
                stack.push(i);
            }
            d[i] = Math.max(d[i], 0);
        }
        return res;
    }
}