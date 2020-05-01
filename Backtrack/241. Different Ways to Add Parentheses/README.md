# [241. Different Ways to Add Parentheses (Medium)](https://leetcode.com/problems/different-ways-to-add-parentheses/)

<p>Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are <code>+</code>, <code>-</code> and <code>*</code>.</p>

<p><b>Example 1:</b></p>

<pre><b>Input:</b> <code>"2-1-1"</code>
<b>Output:</b> <code>[0, 2]</code>
<strong>Explanation: </strong>
((2-1)-1) = 0 
(2-(1-1)) = 2</pre>

<p><b>Example 2:</b></p>

<pre><b>Input: </b><code>"2*3-4*5"</code>
<b>Output:</b> <code>[-34, -14, -10, -10, 10]</code>
<strong>Explanation: 
</strong>(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10<strong>
</strong></pre>

**Companies**:  
[Citadel](https://leetcode.com/company/citadel)

**Related Topics**:  
[Divide and Conquer](https://leetcode.com/tag/divide-and-conquer/)

**Similar Questions**:
* [Unique Binary Search Trees II (Medium)](https://leetcode.com/problems/unique-binary-search-trees-ii/)
* [Basic Calculator (Hard)](https://leetcode.com/problems/basic-calculator/)
* [Expression Add Operators (Hard)](https://leetcode.com/problems/expression-add-operators/)

## Solution 

```java
// OJ: https://leetcode.com/problems/different-ways-to-add-parentheses/
// Author: https://leetcode.com/charlesna/
// Time: O(n2)
// Space: O(n2)
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        return helper(input, 0, input.length() - 1);
    }
    private List<Integer> helper(String s, int start, int end) {
        // System.out.println(start + " " + end);
        List<Integer> res = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> p1 = helper(s, start, i - 1);
                List<Integer> p2 = helper(s, i + 1, end);
                for (int first : p1) {
                    for (int second : p2) {
                        switch (c) {
                            case '+': 
                                res.add(first + second);
                                break;
                            case '-': 
                                res.add(first - second);
                                break;
                            case '*': 
                                res.add(first * second);
                                break;
                        }
                    }
                }
            }
        }
        return res.size() > 0 ? res : Arrays.asList(new Integer[]{Integer.parseInt(s.substring(start, end + 1))});
    }
}
```