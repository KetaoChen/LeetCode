class Solution {
    int min;
    public int lastStoneWeightII(int[] stones) {
        min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }

        Arrays.sort(stones);
        HashSet<Integer>[] sets = new HashSet[stones.length];
        for (int i = 0; i < stones.length; i++) {
            sets[i] = new HashSet<Integer>();
        }
        helper(stones, 0, 0, sets, sum);
        return min;
    }
    
	//find all the combo of numbers
    private void helper(int[] nums, int temp, int index, HashSet[] sets, int sum) {
		//check the index 
        if (index >= nums.length) {
            return;
        }
		//check whether this combo can make the answer smaller.
        if (Math.abs(sum - 2 * temp) < min) {
            min = Math.abs(sum - 2 * temp);
        }
		//check whether this situation met before
        if (sets[index].contains(temp)) {
            return;
        }
        sets[index].add(temp);
		//it is a sorted array, if it is smaller than 0, no need to add more number to this.
        if (sum - 2 * temp < 0) {
            return;
        }
		//start next iteration.
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            helper(nums, temp + nums[i], i + 1, sets, sum);
        }
    }
}