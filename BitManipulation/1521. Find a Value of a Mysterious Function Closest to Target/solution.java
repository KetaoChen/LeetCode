// OJ: https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target/
// Author: https://leetcode.com/charlesna/
// Time: O(20n)
// Space: O(20)
class Solution {
    public int closestToTarget(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            Set<Integer> temp = new HashSet<>();
            temp.add(arr[i]);
            res = Math.min(res, Math.abs(target - arr[i]));
            for (int num : set) {
                int add = num & arr[i];
                temp.add(add);
                res = Math.min(res, Math.abs(target - add));
            }
            set = temp;
        }
        return res;
    }
}