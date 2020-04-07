// OJ: https://leetcode.com/problems/bitwise-and-of-numbers-range/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int d = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            d++;
        }
        return m << d;
    }
}