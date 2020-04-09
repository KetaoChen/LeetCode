// OJ: https://leetcode.com/problems/rotated-digits/
// Author: https://leetcode.com/charlesna/
// Time: O(logN)
// Space: O(1)
class Solution {
    public int rotatedDigits(int N) {
        int[] valid = {0, 1, 8};
        int[] rotate = {2, 5, 6, 9};
        Set<Integer> set = new HashSet<>();
        Set<Integer> good = new HashSet<>();
        for (int v : valid) set.add(v);
        for (int r : rotate) good.add(r);
        // per[i] is the number permutations with length i, if we can choose any number from valid and rotate.
        int[] per = new int[6];
        per[0] = 1;
        for (int i = 1; i < 6; i++) per[i] = per[i - 1] * 7;
        // dp[i] is the number of permutations with length i, if we have to choose with at least 1 good number. 
        int[] dp = new int[6]; 
        for (int i = 1; i < 6; i++) {
            for (int pickGood = 1; pickGood <= i; pickGood++) {
                dp[i] += combo(i, pickGood) * Math.pow(4, pickGood) * Math.pow(3, (i - pickGood));
            }
        }        
        // get all the digits
        List<Integer> list = new ArrayList<>();
        while (N > 0) {
            list.add(N % 10);
            N /= 10;
        }
        int res = 0, l = list.size();
        boolean hasGood = false;
        // start from the first digit
        for (int i = l - 1; i >= 0; i--) {
            int val = list.get(i);
            for (int k = 0; k < val; k++) {
                if (!set.contains(k) && !good.contains(k)) continue;
                if (hasGood) {
                    res += per[i];
                    continue;
                }
                if (good.contains(k)) res += per[i];
                else res += dp[i];
            }
            if (!set.contains(val) && !good.contains(val)) return res;
            if (good.contains(val)) hasGood = true;
        }
        if (hasGood) res++;
        return res;
    }
    private int combo(int i, int j) {
        int res = 1;
        for (int k = 0; k < j; k++) {
            res *= (i - k);
            res /= (k + 1);
        }
        return res;
    }
}