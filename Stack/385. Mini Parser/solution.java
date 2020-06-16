// OJ: https://leetcode.com/problems/mini-parser/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public NestedInteger deserialize(String s) {
        if (s == null || s.length() == 0) return new NestedInteger();
        if (s.charAt(0) != '[') return new NestedInteger(Integer.parseInt(s));
        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger temp;
        int num = 0;
        boolean neg = false, haveNum = false;;
        for (char c : s.toCharArray()) {
            if (c == '[') stack.push(new NestedInteger());
            else if (c == '-') neg = true;
            else if (Character.isDigit(c)) {
                haveNum = true;
                num = num * 10 + c - '0';
            }
            else if (c == ',' || c == ']') {
                if (haveNum) {
                    haveNum = false;
                    int val = neg ? -num : num;
                    num = 0;
                    neg = false;
                    stack.peek().add(new NestedInteger(val));
                }
                if (c == ']') {
                    if (stack.size() == 1) return stack.pop();
                    temp = stack.pop();
                    stack.peek().add(temp);
                }
            }
        }
        return null;
    }
}