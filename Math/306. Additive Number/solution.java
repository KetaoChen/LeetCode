// OJ: https://leetcode.com/problems/additive-number/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public boolean isAdditiveNumber(String num) {
        int l = num.length();
        for (int i = 1; i < l; i++) {
            for (int j = i + 1; j <= l; j++) {
                if (Math.max(i, j - i) > l - j) {
                    break;
                }
                if (valid(num, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean valid(String s, int start, int i, int j) {
        if (j == s.length()) {
            return true;
        }
        int index = j + 1;
        while (index <= s.length()) {
            if (index - j - 1 > Math.max(i - start, j - i)) {
                return false;
            }
            if ((s.charAt(start) == '0' && (i - start) > 1) 
                || (s.charAt(i) == '0' && (j - i) > 1 )
                || (s.charAt(j) == '0' && index - j > 1)) {
                return false;
            }
            if (add(s.substring(start, i), s.substring(i, j)).equals(s.substring(j, index))) {
                return valid(s, i, j, index);
            }
            index++;
        }
        return false;
    }
    private String add(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length(), i = l1 - 1, j = l2 - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            int f = i >= 0 ? s1.charAt(i--) - '0' : 0;
            int s = j >= 0 ? s2.charAt(j--) - '0' : 0;
            int cur = f + s + carry;
            carry = cur / 10;
            cur = cur % 10;
            sb.append(cur);
        }
        return sb.reverse().toString();
    }
}