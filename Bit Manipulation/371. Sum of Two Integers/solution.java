// OJ: https://leetcode.com/problems/sum-of-two-integers/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return getSum(sum, carry);
    }
}