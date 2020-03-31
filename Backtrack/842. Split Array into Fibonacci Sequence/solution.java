// OJ: https://leetcode.com/problems/split-array-into-fibonacci-sequence/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < S.length(); i++) {
            long first = Long.parseLong(S.substring(0, i));
            if (first > Integer.MAX_VALUE || (S.charAt(0) == '0' && i > 1)) return res;
            res.add((int) first);
            for (int j = i + 1; j <= S.length(); j++) {
                if (Math.max(i, j - i) > S.length() - j) break;
                long second = Long.parseLong(S.substring(i, j));
                if (second > Integer.MAX_VALUE || (S.charAt(i) == '0' && j - i > 1)) break;
                res.add((int) second);
                if (helper(S, res, first, second, j)) {
                    return res;
                }
                res.remove(res.size() - 1);
            }
            res.remove(res.size() - 1);
        }
        return res;
    }
    private boolean helper(String S, List<Integer> res, long f, long s, int index) {
        if (index == S.length()) {
            return true;
        }
        for (int i = index + 1; i <= S.length(); i++) {
            if (S.charAt(index) == 0 && i > index + 1) return false;
            long l = Long.parseLong(S.substring(index, i));
            if (l > Integer.MAX_VALUE || l > f + s) return false;
            if (l == f + s) {
                res.add((int) l);
                if (helper(S, res, s, l, i)) {
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }
        return false;
    }
}