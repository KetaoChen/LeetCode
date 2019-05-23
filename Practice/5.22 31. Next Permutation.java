//31. Next Permutation
class Solution {
    public void nextPermutation(int[] nums) {
        //12534 --> 12543 --> 13245
        //1256543 --> 125 3456 --> 126 3455
        int l = nums.length;
        int i = l - 1;
        while (i > 0) {
            if (nums[i - 1] < nums[i]) {
                reverse(nums, i, l - 1);
                int index = findMinLarger(nums, i, l - 1, nums[i - 1]);
                swap(nums, i - 1, index);
                return;
            }
            i--;
        }
        reverse(nums, 0, l - 1);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    private int findMinLarger(int[] nums, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (nums[mid] > target) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (nums[start] > target) {
            return start;
        }
        return end;
    }
}