// OJ: https://leetcode.com/problems/combination-sum-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(2^n)
// Space: O(2^n)
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int[] arr, int target, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index == arr.length) {
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (arr[i] > target) {
                break;
            }
            if (i != index && arr[i - 1] == arr[i]) {
                continue;
            }
            temp.add(arr[i]);
            helper(res, temp, arr, target - arr[i], i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}