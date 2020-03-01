// OJ: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }    
        int l = nums.length;
        int left = l, right = -1;
        int min = nums[l - 1], max = nums[0];
        for (int i = 1; i < l; i++) {
            min = Math.min(nums[l - i - 1], min);
            max = Math.max(nums[i], max);
            if (nums[l - i - 1] > min) left = l - i - 1;
            if (nums[i] < max) right = i;
        }
        return Math.max(0, right - left + 1);
    }
}

// OJ: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }    
        int l = nums.length;
        int left = l;
        int min = nums[l - 1];
        for (int i = l - 2; i >= 0; i--) {
            min = Math.min(nums[i], min);
            if (nums[i] > min) {
                left = i;
            }
        }
        int right = -1;
        int max = nums[0];
        for (int i = 1; i < l; i++) {
            max = Math.max(nums[i], max);
            if (nums[i] < max) {
                right = i;
            }
        }
        return Math.max(0, right - left + 1);
    }
}

// OJ: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // use an array to record the min from right         
        int l = nums.length;
        int[] minFromRight = new int[l];
        minFromRight[l - 1] = nums[l - 1];
        for (int i = l - 2; i >= 0; i--) {
            minFromRight[i] = Math.min(nums[i], minFromRight[i + 1]);
        }
        // use an array to record the max from left
        int[] maxFromLeft = new int[l];
        maxFromLeft[0] = nums[0];
        for (int i = 1; i < l; i++) {
            maxFromLeft[i] = Math.max(nums[i], maxFromLeft[i - 1]);
        }
        int left = 0;
        while (left < l && nums[left] <= minFromRight[left]) {
            left++;
        }
        int right = nums.length - 1;
        while (right >= left && nums[right] >= maxFromLeft[right]) {
            right--;
        }
        return right - left + 1;
    }
}

// OJ: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int left = nums.length;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[i] >= nums[stack.peek()]) {
                if (!stack.isEmpty() && nums[i] == nums[stack.peek()]) {
                    continue;
                }
                stack.push(i);
                continue;
            }
            right = i;
            left = Math.min(left, stack.peek());
            int max = stack.pop();
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(max);
        }    
        return right == 0 ? 0 : right - left + 1;
    }
}


// OJ: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(nlogn)
// Space: O(n)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        int left = 0;
        int right = nums.length - 1;
        while (left < nums.length && nums[left] == copy[left]) {
            left++;
        }
        while (right > left && nums[right] == copy[right]) {
            right--;
        }
        return right - left + 1;
    }
}

// OJ: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(1)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        // brute force to check the left end and right end
        // 
        int left = 0;
        while (checkLeft(left, nums) && left < nums.length) {
            left++;
        }
        int right = nums.length - 1;
        while (checkRight(right, nums) && right >= left) {
            right--;
        }
        return right - left + 1;
    }
    private boolean checkLeft(int index, int[] nums) {
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] < nums[index]) {
                return false;
            }
        }
        return true;
    }
    private boolean checkRight(int index, int[] nums) {
        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] > nums[index]) {
                return false;
            }
        }
        return true;
    }
}