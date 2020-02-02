// OJ: https://leetcode.com/problems/combination-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(2^n)
// Space: O(2^n)
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(res, new ArrayList<>(), target, candidates, 0);
        return res;
    }
	
	//since we can pick number repeatedly, so we do not need to increase index for next level.
    private void helper(List<List<Integer>> res, List<Integer> temp, int target, int[] arr, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
		
        for (int i = index; i < arr.length; i++) {
            if (arr[i] > target) {
                break;
            }
            temp.add(arr[i]);
            helper(res, temp, target - arr[i], arr, i);
            temp.remove(temp.size() - 1);
        }
    }
}