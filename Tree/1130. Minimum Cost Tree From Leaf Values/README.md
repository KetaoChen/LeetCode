# [1130. Minimum Cost Tree From Leaf Values (Medium)](https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/)

<p>Given an array <code>arr</code> of positive integers, consider all binary trees such that:</p>

<ul>
	<li>Each node has either 0 or 2 children;</li>
	<li>The values of <code>arr</code> correspond to the values of each&nbsp;<strong>leaf</strong> in an in-order traversal of the tree.&nbsp; <em>(Recall that a node is a leaf if and only if it has 0 children.)</em></li>
	<li>The value&nbsp;of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.</li>
</ul>

<p>Among all possible binary trees considered,&nbsp;return the smallest possible sum of the values of each non-leaf node.&nbsp; It is guaranteed this sum fits into a 32-bit integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> arr = [6,2,4]
<strong>Output:</strong> 32
<strong>Explanation:</strong>
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 40</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 15</code></li>
	<li>It is guaranteed that the answer fits into a 32-bit signed integer (ie.&nbsp;it is less than <code>2^31</code>).</li>
</ul>

**Companies**:  
[Mathworks](https://leetcode.com/company/mathworks), [Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/), [Stack](https://leetcode.com/tag/stack/), [Tree](https://leetcode.com/tag/tree/)

## Solution 

```java
// OJ: https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int mctFromLeafValues(int[] arr) {
        // the order of number can not change.
        // each number need to multiply with one number. 
        // we want to minimize the sum of all the product. 
        int res = 0, l = arr.length;
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            while (!stack.isEmpty() && num >= stack.peek()) {
                int cur = stack.pop();
                int left = stack.isEmpty() ? 20 : stack.peek();
                res += cur * Math.min(num, left);
            }
            stack.push(num);
        }
        while (stack.size() > 1) res += stack.pop() * stack.peek();
        return res;
    }
}
```