class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        //if a <= b <= c  a + b == -c   --> sort the array O(nlogn)
        //enumerate c from the last number to the third O(n)
        //in each iterate, use two pointer to find out all the combo to have a sum of -c: O(n);
        //total time complexity O(n2)
        List<List<Integer>> res = new ArrayList<>();
        int l = nums.length;
        if (l < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int k = l - 1; k >= 2; k--) {
            //no duplicate, so the target value can not be same. 
            if (k < l - 1 && nums[k] == nums[k + 1]) {
                continue;
            }
            int i = 0;
            int j = k - 1;
            int target = -nums[k];
            while (i < j) {
                //prune, this target can not be achieved
                if (2 * nums[i] > target || 2 * nums[j] < target) {
                    break;
                }
                if (nums[i] + nums[j] < target) {
                    i++;
                }
                else if (nums[i] + nums[j] > target) {
                    j--;
                }
                //when we have the answer, we need to make sure this identical answer will not occur
                else if (nums[i] + nums[j] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    while (i < j && nums[j] == nums[j - 1]) {
                        j--;
                    }
                    i++;
                    j--;
                }
            }
        }
        return res;
    }
}