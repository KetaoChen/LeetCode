238. Product of Array Except Self


//optimize the space complexity. 
//using two scanning to get the product from the left and from the right
class Solution {
    public int[] productExceptSelf(int[] nums) {
        //for each i res[i] = left[i - 1] * right[i + 1];
        
        int left = 1;
        int[] res = new int[nums.length];
        res[0] = 1;
        //update the left part;
        for (int i = 1; i < nums.length; i++) {
            left *= nums[i - 1];
            res[i] = left;
        }
        
        int right = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right *= nums[i + 1];
            res[i] *= right;
        }
        
        return res;
    }
}

//Using the idae of prefixSum. get the prefix product and suffix product.
class Solution {
    public int[] productExceptSelf(int[] nums) {
        //for each i res[i] = left[i - 1] * right[i + 1];
        int[] left = new int[nums.length + 1];
        int[] right = new int[nums.length + 1];
        
        left[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            left[i + 1] = left[i] * nums[i];
        }
        right[nums.length] = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i];
        }
        
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = left[i] * right[i + 1];
        }
        return res;
    }
}