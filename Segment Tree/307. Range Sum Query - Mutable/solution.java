// OJ: https://leetcode.com/problems/range-sum-query-mutable/
// Author: https://leetcode.com/charlesna/
// Time: Build Tree: O(nlogn) Query: O(logn) Update: O(logn)
// Space: O(n)

class NumArray {
    private int lowbit(int i) {
        return i & (-i);
    }
    private void add(int index, int val) {
        for (int i = index; i < sum.length; i += lowbit(i)) {
            sum[i] += val;
        }
    }
    private int get(int index) {
        int res = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            res += sum[i];
        }
        return res;
    }
    int[] sum;
    int[] arr;
    public NumArray(int[] nums) {
        int l = nums.length;
        sum = new int[l + 1];
        arr = new int[l + 1];
        for (int i = 0; i < l; i++) {
            update(i, nums[i]);
            arr[i + 1] = nums[i];
        }
    }
    public void update(int index, int val) {
        index++;
        add(index, val - arr[index]);
        arr[index] = val;
    }
    public int sumRange(int i, int j) {
        return get(j + 1) - get(i);
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */