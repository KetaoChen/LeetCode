// OJ: https://leetcode.com/problems/number-complement/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int findComplement(int num) {
        int res = 0, digit = 0;
        while (num > 0) {
            res += (1 - (num & 1)) << digit;
            num >>= 1;
            digit++;
        }
        return res;
    }
}