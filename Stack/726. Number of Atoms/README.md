# [726. Number of Atoms (Hard)](https://leetcode.com/problems/number-of-atoms/)

<p>Given a chemical <code>formula</code> (given as a string), return the count of each atom.
</p><p>
An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
</p><p>
1 or more digits representing the count of that element may follow if the count is greater than 1.  If the count is 1, no digits will follow.  For example, H2O and H2O2 are possible, but H1O2 is impossible.
</p><p>
Two formulas concatenated together produce another formula.  For example, H2O2He3Mg4 is also a formula.  
</p><p>
A formula placed in parentheses, and a count (optionally added) is also a formula.  For example, (H2O2) and (H2O2)3 are formulas.
</p><p>
Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.</p>

<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> 
formula = "H2O"
<b>Output:</b> "H2O"
<b>Explanation:</b> 
The count of elements are {'H': 2, 'O': 1}.
</pre>
<p></p>

<p><b>Example 2:</b><br>
</p><pre><b>Input:</b> 
formula = "Mg(OH)2"
<b>Output:</b> "H2MgO2"
<b>Explanation:</b> 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
</pre>
<p></p>

<p><b>Example 3:</b><br>
</p><pre><b>Input:</b> 
formula = "K4(ON(SO3)2)2"
<b>Output:</b> "K4N2O14S4"
<b>Explanation:</b> 
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
</pre>
<p></p>

<p><b>Note:</b>
</p><li>All atom names consist of lowercase letters, except for the first character which is uppercase.</li>
<li>The length of <code>formula</code> will be in the range <code>[1, 1000]</code>.</li>
<li><code>formula</code> will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.</li>
<p></p>

**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [ByteDance](https://leetcode.com/company/bytedance)

**Related Topics**:  
[Hash Table](https://leetcode.com/tag/hash-table/), [Stack](https://leetcode.com/tag/stack/), [Recursion](https://leetcode.com/tag/recursion/)

**Similar Questions**:
* [Decode String (Medium)](https://leetcode.com/problems/decode-string/)
* [Encode String with Shortest Length (Hard)](https://leetcode.com/problems/encode-string-with-shortest-length/)
* [Parse Lisp Expression (Hard)](https://leetcode.com/problems/parse-lisp-expression/)

## Solution 

```java
// OJ: https://leetcode.com/problems/number-of-atoms/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public String countOfAtoms(String formula) {
        // Capital letters: the start of the element
        // lowercase letters: follow the capital letters, and they are the same element.
        // numbers: after the elements or molecule.
        // parenthesis: 
        // when we deal with the multiplication? '(', ')', Capital letters, the end of parsing
        TreeMap<String, Integer> map = new TreeMap<>();
        Stack<TreeMap<String, Integer>> stack = new Stack<>();
        stack.push(map);
        map = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (char c : formula.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            else if (Character.isLowerCase(c)) {
                sb.append(c);
            }
            else {
                num = num == 0 ? 1 : num;
                if (sb.length() == 0 && map.size() > 0) {
                    for (String key : map.keySet()) {
                        stack.peek().put(key, stack.peek().getOrDefault(key, 0) + map.get(key) * num);
                    }
                    map = new TreeMap<>();
                }
                else if (sb.length() > 0) {
                    String s = sb.toString();
                    stack.peek().put(s, stack.peek().getOrDefault(s, 0) + num);
                }
                if (c == '(') {
                    stack.push(map);
                    map = new TreeMap<>();
                }
                else if (c == ')') {
                    map = stack.pop();
                }
                num = 0;
                sb = new StringBuilder();
                if (Character.isUpperCase(c)) {
                    sb.append(c);
                }
            }
        }
        num = num == 0 ? 1 : num;
        if (sb.length() == 0) {
            for (String key : map.keySet()) {
                stack.peek().put(key, stack.peek().getOrDefault(key, 0) + map.get(key) * num);
            }
        }
        else {
            String s = sb.toString();
            stack.peek().put(s, stack.peek().getOrDefault(s, 0) + num);
        }
        sb = new StringBuilder();
        map = stack.pop();
        for (String s : map.keySet()) {
            sb.append(s);
            if (map.get(s) != 1) {
                sb.append(map.get(s));
            }
        }
        return sb.toString();
    }
}
```