// OJ: https://leetcode.com/problems/132-pattern/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public boolean find132pattern(int[] nums) {
        // we just want to know the maximum value from right which is smaller than cur value
        int l = nums.length;
        if (l <= 2) {
            return false;
        }
        boolean havePeak = false;
        int right = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        stack.push(right);
        for (int i = l - 1; i >= 0; i--) {
            if (havePeak && nums[i] < right) {
                return true;
            }
            if (!stack.isEmpty() && nums[i] > stack.peek()) {
                havePeak = true;
            }    
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                right = Math.max(right, stack.pop());
            }
            stack.push(nums[i]);
        }
        return false;
    }
}


// OJ: https://leetcode.com/problems/132-pattern/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(1)
class Solution {
    public boolean find132pattern(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(nums[i], min);
            if (nums[i] == min) {
                continue;
            }
            for (int k = nums.length - 1; k > i; k--) {
                if (nums[k] < nums[i] && nums[k] > min) {
                    //System.out.println(min + " " + nums[i] + " " + nums[k]);
                    return true;
                }
            }
        }
        return false;
    }
}