// OJ: https://leetcode.com/problems/remove-duplicate-letters/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(26)
class Solution {
    public String removeDuplicateLetters(String s) {
        // record the last index of each character.
        int[] index = new int[26];
        int l = s.length();
        Arrays.fill(index, -1);
        for (int i = l - 1; i >= 0; i--) {
            char c = s.charAt(i);
            index[c - 'a'] = Math.max(index[c - 'a'], i);
        }
        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            while (!stack.isEmpty() && !visited[c - 'a'] && c < stack.peek() && index[stack.peek() - 'a'] > i) {
                visited[stack.pop() - 'a'] = false;
            }
            if (!visited[c - 'a']) {
                stack.push(c);
                visited[c - 'a'] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}