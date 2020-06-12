# Stack & Deque

Why use stack? 

1. Simulate recursion, storing the temporary values in the stack for current level. 
2. Monotonic stack, avoid repeated calculation, get the next/ previous larger/ smaller value. 



## Recursion: (Understand when to solve the subproblem)

**Usually when the parenthesis comes, we need to start solve the subproblem in the parenthesis first.**

**Understanding the definition of each character, basically there are *add & multiplication* operation**

1. Decoding String (LC 394)
2. Mini Parser (LC 385)
3. Basic Calculators (LC 224, 227, 772)
4. Brace Expansion II (LC 1096) 



## Monotonic Stack (Find next/previous larger/ smaller)

1. Trapping Rain Water (LC 42)
2. Largest Histogram (LC 84)
3. Minimum Cost Tree From Leaf Value (LC 1130)
4. Remove K Digits (LC 402)
5. Longest Valid Parentheses (LC 32)
6. Online Stock Span(LC 901)
7. Remove Duplicate Letters (LC 316)



##### Template (Remove K Digits 402): 

Since we want to get the largest number after removing K digits, the intuition for this problem is that we need to remove the number on the left side which is larger than the right side, which means that we want to keep an **monotonic non-decreasing** values in the new number.  In order to get a non-decreasing value, we can use monotonic stack to solve this problem.

```java
public String removeKdigits(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            while (sb.length() > 0 && k > 0 && sb.charAt(sb.length() - 1) > cur) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            if (sb.length() == 0  && cur == '0') continue;
            sb.append(cur);
        }
        String res = sb.toString().substring(0, sb.length() - Math.min(sb.length(), k));
        return res.length() == 0 ? "0" : res;
    }
```





## Deque (Slide Window) (range max/min, subarray sum)

1. Constrained Subsequence Sum (Deque Dp) (LC 1425)
2. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit (LC 1438)
3. Sliding Window Maximum (LC 239)
4. Shortest Subarray with Sum at Least K (LC 862)



## Trick

1. Every time, when we call stack.peek() or other stack method, we need to check whether stack.isEmpty().
2. Remember to poll the first element from deque when the first element is out of the range.
3. When the elements have the same value, do we need to pop the last element or not. (LC 901 Online Stock Span)
4. Because of the monotonic, it can be used to find the lexicographical min/max value. (LC 316)



## Tech

1. Like **Histogram** problem, in the end the of loop, we can set the value of arr[l] = 0, to pop all the elements in the stack.
2. In **Trapping Rain Water** problem, since we want to know the height of water at every index, we pop the element only if the current element is larger than previous element. At the same time, height is also dependent on the left element, which is min(left, cur) - pop. Also Similar to LC **1130**.

