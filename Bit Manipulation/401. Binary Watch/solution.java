// OJ: https://leetcode.com/problems/binary-watch/
// Author: https://leetcode.com/charlesna/
// Time: O(12*60)
// Space: O(12*60*5)
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == num) {
                    String add = m < 10 ? h + ":0" + m : h + ":" + m;
                    res.add(add);
                }
            }
        }
        return res;
    }
}