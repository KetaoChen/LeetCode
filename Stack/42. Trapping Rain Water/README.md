# [42. Trapping Rain Water (Hard)](https://leetcode.com/problems/trapping-rain-water/)

<p>Given <em>n</em> non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.</p>

<p><img src="https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png" style="width: 412px; height: 161px;"><br>
<small>The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. <strong>Thanks Marcos</strong> for contributing this image!</small></p>

<p><strong>Example:</strong></p>

<pre><strong>Input:</strong> [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>Output:</strong> 6</pre>


**Companies**:  
[Goldman Sachs](https://leetcode.com/company/goldman-sachs), [Amazon](https://leetcode.com/company/amazon), [Facebook](https://leetcode.com/company/facebook), [Microsoft](https://leetcode.com/company/microsoft), [Bloomberg](https://leetcode.com/company/bloomberg), [Apple](https://leetcode.com/company/apple), [ByteDance](https://leetcode.com/company/bytedance), [Google](https://leetcode.com/company/google), [Adobe](https://leetcode.com/company/adobe), [Oracle](https://leetcode.com/company/oracle), [Visa](https://leetcode.com/company/visa), [Airbnb](https://leetcode.com/company/airbnb), [Uber](https://leetcode.com/company/uber), [eBay](https://leetcode.com/company/ebay), [Walmart Labs](https://leetcode.com/company/walmart-labs), [Qualtrics](https://leetcode.com/company/qualtrics)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/), [Two Pointers](https://leetcode.com/tag/two-pointers/), [Stack](https://leetcode.com/tag/stack/)

**Similar Questions**:
* [Container With Most Water (Medium)](https://leetcode.com/problems/container-with-most-water/)
* [Product of Array Except Self (Medium)](https://leetcode.com/problems/product-of-array-except-self/)
* [Trapping Rain Water II (Hard)](https://leetcode.com/problems/trapping-rain-water-ii/)
* [Pour Water (Medium)](https://leetcode.com/problems/pour-water/)


## Solution 1: Monotonic Stack 1 Pass O(n) Space

```java
public int trap(int[] height) {
	Stack<Integer> stack = new Stack<>();
	int l = height.length;
	int res = 0;

	// we want to keep the highest from the left, so the stack should be monotonice decreasing.
	for (int i = 0; i < l; i++) {
		int cur = height[i];
		while (!stack.isEmpty() && cur > height[stack.peek()]) {
			int pop = stack.pop();
			if (!stack.isEmpty()) {
				res += (Math.min(height[stack.peek()], cur) - height[pop]) * (i - stack.peek() - 1);
			}
		}
		stack.push(i);
	}
	return res;
}
```

## Solution 2: DP 2 Pass O(n) Space

```java
public int trap(int[] height) {
	// for each position, find the highest from left, and highest from right.
	// the difference is the height of water at his position.
	int l = height.length;
	if (l <= 2) return 0;
	int[] left = new int[l];
	left[0] = height[0];
	for (int i = 1; i < l; i++) {
		left[i] = Math.max(left[i - 1], height[i]);
	}
	int[] right = new int[l];
	right[l - 1] = height[l - 1];
	for (int i = l - 2; i >= 0; i--) {
		right[i] = Math.max(right[i + 1], height[i]);
	}
	int res = 0;
	for (int i = 1; i < l - 1; i++) {
		res += Math.min(left[i], right[i]) - height[i];
	}
	return res;
}
```