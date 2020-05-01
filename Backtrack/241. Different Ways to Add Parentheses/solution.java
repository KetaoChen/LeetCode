// OJ: https://leetcode.com/problems/different-ways-to-add-parentheses/
// Author: https://leetcode.com/charlesna/
// Time: O(n2)
// Space: O(n2)
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        return helper(input, 0, input.length() - 1);
    }
    private List<Integer> helper(String s, int start, int end) {
        // System.out.println(start + " " + end);
        List<Integer> res = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> p1 = helper(s, start, i - 1);
                List<Integer> p2 = helper(s, i + 1, end);
                for (int first : p1) {
                    for (int second : p2) {
                        switch (c) {
                            case '+': 
                                res.add(first + second);
                                break;
                            case '-': 
                                res.add(first - second);
                                break;
                            case '*': 
                                res.add(first * second);
                                break;
                        }
                    }
                }
            }
        }
        return res.size() > 0 ? res : Arrays.asList(new Integer[]{Integer.parseInt(s.substring(start, end + 1))});
    }
}