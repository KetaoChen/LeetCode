560. Subarray Sum Equals K
class Solution {
	//because for the first number, we need to calculate all the sum of subarrays start with the second number
    //there are repeated calculation, so we can use hashMap to record how many times a certain val has been calcuated.
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        int sum = 0;
        int res = 0;
        prefixSum.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; //this sum is prefix[0, i];
            //map store all the possible prefix from [0, 1], [0, 2] ... [0, i-1];
            if (prefixSum.containsKey(sum - k)) {  // sum - k means whether there is a sum[j, i] equals k. 
                res += prefixSum.get(sum - k);
            }
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
	
	
	//brute force, for each num, check all subarray start with num O(n2) 122ms
    // public int subarraySum(int[] nums, int k) {
    //     int res = 0;
    //     for (int i = 0; i < nums.length; i++) {
    //         int sum = 0;
    //         for (int j = i; j < nums.length; j++) {
    //             sum += nums[j];
    //             if (sum == k) {
    //                 res++;
    //             }
    //         }
    //     }
    //     return res;
    // }    
}