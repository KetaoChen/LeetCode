# [581. Shortest Unsorted Continuous Subarray (Easy)](https://leetcode.com/problems/shortest-unsorted-continuous-subarray/)

<p>Given an integer array, you need to find one <b>continuous subarray</b> that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too. </p> 

<p>You need to find the <b>shortest</b> such subarray and output its length.</p>

<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> [2, 6, 4, 8, 10, 9, 15]
<b>Output:</b> 5
<b>Explanation:</b> You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
</pre>
<p></p>

<p><b>Note:</b><br>
</p><ol>
<li>Then length of the input array is in range [1, 10,000].</li>
<li>The input array may contain duplicates, so ascending order here means <b>&lt;=</b>. </li>
</ol>
<p></p>

**Companies**:  
[Uber](https://leetcode.com/company/uber)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/)

## Solution 1 : Math 1 Pass

```java
// OJ: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int findUnsortedSubarray(int[] A) {
        int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
        for (int i=1;i<n;i++) {
          max = Math.max(max, A[i]);
          min = Math.min(min, A[n-1-i]);
          if (A[i] < max) end = i;
          if (A[n-1-i] > min) beg = n-1-i; 
        }
        return end - beg + 1;
    }
}
```

## Solution 2 : Math 2 Pass

```java
// OJ: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = nums.length - 1;
        int min = nums[nums.length - 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                left = i;
            }
        }
        int right = 0, max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                right = i;
            }
        }
        return right <= left ? 0 : right - left + 1;
    }
}
```

## Solution 3 : Monotonic Stack

```java
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
```

## Solution 4: Sort

```java
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
```