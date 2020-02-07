// OJ: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //for each number we find, num, we lable the number at index = num - 1, as negative
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            nums[num - 1] = Math.min(nums[num - 1], -nums[num - 1]);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}

// OJ: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //swap sort
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                //System.out.println(i + " " + nums[i] + " " + nums[nums[i] - 1]);
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }
}