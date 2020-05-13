// OJ: https://leetcode.com/problems/remove-k-digits/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public String removeKdigits(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            // get cur value;
            char cur = s.charAt(i);
            // while the value we have is larger than current value, we keep removing the number we have.
            while (sb.length() > 0 && k > 0 && sb.charAt(sb.length() - 1) > cur) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            if (sb.length() == 0  && cur == '0') continue;
            sb.append(cur);
        }
        String res = sb.toString().substring(0, sb.length() - Math.min(sb.length(), k));
        return res.length() == 0 ? "0" : res;
    }
}