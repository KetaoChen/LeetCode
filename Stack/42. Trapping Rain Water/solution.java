// OJ: https://leetcode.com/problems/trapping-rain-water/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)

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


// OJ: https://leetcode.com/problems/trapping-rain-water/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)

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
