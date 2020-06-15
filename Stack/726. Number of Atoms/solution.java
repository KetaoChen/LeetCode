// OJ: https://leetcode.com/problems/number-of-atoms/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public String countOfAtoms(String formula) {
        // Capital letters: the start of the element
        // lowercase letters: follow the capital letters, and they are the same element.
        // numbers: after the elements or molecule.
        // parenthesis: 
        // when we deal with the multiplication? '(', ')', Capital letters, the end of parsing
        TreeMap<String, Integer> map = new TreeMap<>();
        Stack<TreeMap<String, Integer>> stack = new Stack<>();
        stack.push(map);
        map = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (char c : formula.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            else if (Character.isLowerCase(c)) {
                sb.append(c);
            }
            else {
                num = num == 0 ? 1 : num;
                if (sb.length() == 0 && map.size() > 0) {
                    for (String key : map.keySet()) {
                        stack.peek().put(key, stack.peek().getOrDefault(key, 0) + map.get(key) * num);
                    }
                    map = new TreeMap<>();
                }
                else if (sb.length() > 0) {
                    String s = sb.toString();
                    stack.peek().put(s, stack.peek().getOrDefault(s, 0) + num);
                }
                if (c == '(') {
                    stack.push(map);
                    map = new TreeMap<>();
                }
                else if (c == ')') {
                    map = stack.pop();
                }
                num = 0;
                sb = new StringBuilder();
                if (Character.isUpperCase(c)) {
                    sb.append(c);
                }
            }
        }
        num = num == 0 ? 1 : num;
        if (sb.length() == 0) {
            for (String key : map.keySet()) {
                stack.peek().put(key, stack.peek().getOrDefault(key, 0) + map.get(key) * num);
            }
        }
        else {
            String s = sb.toString();
            stack.peek().put(s, stack.peek().getOrDefault(s, 0) + num);
        }
        sb = new StringBuilder();
        map = stack.pop();
        for (String s : map.keySet()) {
            sb.append(s);
            if (map.get(s) != 1) {
                sb.append(map.get(s));
            }
        }
        return sb.toString();
    }
}