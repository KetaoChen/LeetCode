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


## Solution 1: Concise Write & Two Pass

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
```

## Solution 2: Two Pass O(1) Space

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
```

## Solution 3: Use two arrays to record the min and max. 

```java
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
```

## Solution 4 : Monotonic Stack

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


## Solution 5: Sort

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


## Solution 6: Brute Force to Check the Left End and Right End

```java
// OJ: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(1)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        // brute force to check the left end and right end
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
```