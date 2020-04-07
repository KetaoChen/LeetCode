# [307. Range Sum Query - Mutable (Medium)](https://leetcode.com/problems/range-sum-query-mutable/)

<p>Given an integer array <i>nums</i>, find the sum of the elements between indices <i>i</i> and <i>j</i> (<i>i</i> ¡Ü <i>j</i>), inclusive.</p>

<p>The <i>update(i, val)</i> function modifies <i>nums</i> by updating the element at index <i>i</i> to <i>val</i>.</p>

<p><b>Example:</b></p>

<pre>Given nums = [1, 3, 5]

sumRange(0, 2) -&gt; 9
update(1, 2)
sumRange(0, 2) -&gt; 8
</pre>

<p><b>Note:</b></p>

<ol>
	<li>The array is only modifiable by the <i>update</i> function.</li>
	<li>You may assume the number of calls to <i>update</i> and <i>sumRange</i> function is distributed evenly.</li>
</ol>


**Companies**:  
[Twitter](https://leetcode.com/company/twitter), [Amazon](https://leetcode.com/company/amazon), [Google](https://leetcode.com/company/google)

**Related Topics**:  
[Binary Indexed Tree](https://leetcode.com/tag/binary-indexed-tree/), [Segment Tree](https://leetcode.com/tag/segment-tree/)

**Similar Questions**:
* [Range Sum Query - Immutable (Easy)](https://leetcode.com/problems/range-sum-query-immutable/)
* [Range Sum Query 2D - Mutable (Hard)](https://leetcode.com/problems/range-sum-query-2d-mutable/)

## Solution  Binary Index Tree

```java
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
```