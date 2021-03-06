// OJ: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// Author: https://leetcode.com/charlesna/
// Time: O(4^n) number of all the combinations
// Space: O(4^n)
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        helper(digits, phone, 0, res, new StringBuilder());
        return res;
    }
    private void helper(String digits, String[] phone, int index, List<String> res, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (char c : phone[digits.charAt(index) - '0'].toCharArray()) {
            sb.append(c);
            helper(digits, phone, index + 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

 
// OJ: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// Author: https://leetcode.com/charlesna/
// Time: O(4^n) number of all the combinations
// Space: O(4^n)
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Queue<String> q = new LinkedList<>();
        q.offer("");
        for (char num : digits.toCharArray()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                for (char c : phone[num - '0'].toCharArray()) {
                    String next = cur + c;
                    q.offer(next);
                }
            }
        }
        return new ArrayList<>(q);
    }
}