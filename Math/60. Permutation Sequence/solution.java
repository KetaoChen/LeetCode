// OJ: https://leetcode.com/problems/permutation-sequence/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public String getPermutation(int n, int k) {
        int[] perm = new int[10];
        perm[1] = perm[0] = 1;
        for (int i = 2; i <= 9; i++) {
            perm[i] = perm[i - 1] * i;
        }
        boolean[] used = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int next = 1;
            while (used[next]) {
                next++;
            }
            while (k > perm[n - 1]) {
                //System.out.println(k + " " + perm[n - 1] + " " + n);
                next++;
                while (used[next]) {
                    next++;
                }
                k -= perm[n - 1];
            }
            sb.append(next);
            //System.out.println(sb.toString());
            used[next] = true;
            n--;
        }
        return sb.toString();
    }
}