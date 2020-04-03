// OJ: https://leetcode.com/problems/digit-count-in-range/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int digitsCount(int d, int low, int high) {
        return count(high, d) -  count(low - 1, d);
    }
    private int count(int num, int d) {
        if (num == 0 && d == 0) {
            return 1;
        }
        if (num <= 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 10);
            num /= 10;
        }
        int res = 0, count = 0, l = list.size();
        int[] f = new int[l + 1];
        f[1] = 1;
        for (int i = 2; i <= l; i++) {
            f[i] = i * (int)Math.pow(10, i - 1);
        }
        for (int i = l - 1; i >= 0; i--) {
            int val = list.get(i);
            for (int k = 0; k < val; k++) {
                res += f[i];
                res += count * Math.pow(10, i);
                if (k == d) {
                    res += Math.pow(10, i);
                }
            }
            if (val == d) count++;
        }
        // System.out.println(res);
        if (d == 0) {
            for (int i = 1; i < l; i++) {
                res -= Math.pow(10, i);
            }
        }
        // System.out.println(res);
        return res + count;
    }
}