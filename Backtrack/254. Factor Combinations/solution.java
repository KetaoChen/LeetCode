// OJ: https://leetcode.com/problems/factor-combinations/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<Integer>(), n, 2);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int n, int start) {
        for (int i = start; i * i <= n; i++) {
                if (n % i == 0 && n / i >= i) {
                temp.add(i);
                temp.add(n / i);
                res.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
                helper(res, temp, n / i, i);
                temp.remove(temp.size() - 1);
            }
        }
    }
}