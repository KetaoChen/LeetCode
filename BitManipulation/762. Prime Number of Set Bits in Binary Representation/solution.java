// OJ: https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int countPrimeSetBits(int L, int R) {
        int count = 0;
        while (L <= R)
            count += 665772 >> Integer.bitCount(L++) & 1;
        return count;
    }

    public int countPrimeSetBits(int L, int R) {
        int[] prime = {2,3,5,7,11,13,17,19};
        Set<Integer> set = new HashSet<>();
        for (int p : prime) {
            set.add(p);
        }
        int res = 0;
        for (int i = L; i <= R; i++) {
            int count = 0;
            for (int k = i; k > 0; k >>= 1) {
                count += (k & 1);
            }
            if (set.contains(count)) {
                res++;
            }
        }
        return res;
    }
}


