// OJ: https://leetcode.com/problems/combinations/
// Author: https://leetcode.com/charlesna/
// Time: O(Combination of )
// Space: O(C(n, k))
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, k, 1);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int n, int k, int start) {
        if (k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i + k - 1 <= n; i++) {
            temp.add(i);
            helper(res, temp, n, k - 1, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}