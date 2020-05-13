# Stack & Deque



## Recursion:

1. Decoding String (LC 394)



## Monotonic Stack

1. Find next larger / smaller number
2. Trapping Rain Water (LC 42)
3. Largest Histogram (LC 84)
4. Minimum Cost Tree From Leaf Value (LC 1130)



## Deque (Slide Window) (range max/min, subarray sum)

1. Constrained Subsequence Sum (Deque Dp) (LC 1425)
2. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit (LC 1438)
3. Sliding Window Maximum (LC 239)
4. Shortest Subarray with Sum at Least K (LC 862)



## Trick

1. Every time, when we call stack.peek() or other stack method, we need to check whether stack.isEmpty().
2. Remember to pollFirst element from deque when the first element is out of the range.



## Tech

1. Like **Histogram** problem, in the end the of loop, we can set the value of arr[l] = 0, to pop all the elements in the stack.
2. In **Trapping Rain Water** problem, since we want to know the height of water at every index, we pop the element only if the current element is larger than previous element. At the same time, height is also dependent on the left element, which is min(left, cur) - pop. Also Similar to LC **1130**.

