// OJ: https://leetcode.com/problems/combination-sum-iii/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[10];
        helper(res, new ArrayList<>(), 1, k, n, used);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int start, int k, int target, boolean[] used) {
        if (target == 0 && k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (k == 0 || start > 9) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (used[i]) {
                continue;
            }
            if (i > target) {
                break;
            }
            used[i] = true;
            temp.add(i);
            helper(res, temp, i + 1, k - 1, target - i, used);
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }
}