// OJ: https://leetcode.com/problems/number-of-squareful-arrays/
// Author: https://leetcode.com/charlesna/
// Time: O(2^n*n^2)
// Space: O(2^n*n)
class Solution {
    public int numSquarefulPerms(int[] A) {
        int l = A.length;
        // dp[i][j]: the number of permutation when we have some numbers end with j.
        // i represent the set of numbers. j is the last number in this set.
        // because we need to know the adjacent sum, so we need to track the last number.
        int[][] dp = new int[1 << l][l];
        // init: when we have 1 number, there is one way.
        for (int j = 0; j < l; j++) {
            dp[1 << j][j] = 1;
        }
        // transfer equation: if we add a new number after the sequence, which satisfy the requirement.
        // since we add a number every time, so value of state will become larger after doing transfer. 
        // so it is safe to start from a smaller state to a larger state
        for (int i = 1; i < (1 << l); i++) {
            // loop all the values that can be added
            for (int k = 0; k < l; k++) {
                // make sure this number is not added before
                if (((i >> k) & 1) == 0) {
                    // check the last number and check the sum of these two number is a squire or not.
                    for (int j = 0; j < l; j++) {
                        if (isSquare(A[j] + A[k]) && (((i >> j) & 1) == 1)) {
                            dp[(i ^ (1 << k))][k] += dp[i][j];
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int j = 0; j < l; j++) {
            res += dp[(1 << l) - 1][j];
        }
        // since there can be duplicated numbers, we need to remove the duplicate permutations.
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            res /= per(map.get(num));
        }
        return res;
    }
    private int per(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }
    private boolean isSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}


// OJ: https://leetcode.com/problems/number-of-squareful-arrays/
// Author: https://leetcode.com/charlesna/
// Time: O(n!)
// Space: O(n)
class Solution {
    int res;
    public int numSquarefulPerms(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        res = 0;
        helper(A, map, 0, -1);
        return res;
    }
    private void helper(int[] A, Map<Integer, Integer> map, int count, int val) {
        if (count == A.length) {
            res++;
            return;
        }
        for (int num : map.keySet()) {
            if (val == -1 || map.get(num) > 0 && isS(val + num)) {
                map.put(num, map.get(num) - 1);
                helper(A, map, count + 1, num);
                map.put(num, map.get(num) + 1);
            }
        }
    }
    private boolean isS(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}