// OJ: https://leetcode.com/problems/top-k-frequent-elements/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // get freq
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // count the number of numbers by freq
        int[] count = new int[nums.length + 1];
        for (int num : map.keySet()) {
            count[map.get(num)]++;
        }
        // find the least freq to be included in the res
        int leastF = nums.length;
        int sum = 0;
        for (int i = nums.length; i >= 0; i--) {
            sum += count[i];
            if (sum == k) {
                leastF = i;
                break;
            }
        }
        // get the res
        List<Integer> res = new ArrayList<>();
        for (int num :  map.keySet()) {
            if (map.get(num) >= leastF) {
                res.add(num);
            }
        }
        return res;
    }
}