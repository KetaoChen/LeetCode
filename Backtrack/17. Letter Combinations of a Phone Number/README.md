# [17. Letter Combinations of a Phone Number (Medium)](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)

<p>Given a string containing digits from <code>2-9</code> inclusive, return all possible letter combinations that the number could represent.</p>

<p>A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.</p>

<p><img src="http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png"></p>

<p><strong>Example:</strong></p>

<pre><strong>Input: </strong>"23"
<strong>Output:</strong> ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
</pre>

<p><strong>Note:</strong></p>

<p>Although the above answer is in lexicographical order, your answer could be in any order you want.</p>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Google](https://leetcode.com/company/google), [Microsoft](https://leetcode.com/company/microsoft), [Apple](https://leetcode.com/company/apple), [Facebook](https://leetcode.com/company/facebook), [Salesforce](https://leetcode.com/company/salesforce), [Atlassian](https://leetcode.com/company/atlassian), [Oracle](https://leetcode.com/company/oracle), [Nutanix](https://leetcode.com/company/nutanix), [Uber](https://leetcode.com/company/uber), [Quip (Salesforce)](https://leetcode.com/company/quip)

**Related Topics**:  
[String](https://leetcode.com/tag/string/), [Backtracking](https://leetcode.com/tag/backtracking/)

**Similar Questions**:
* [Generate Parentheses (Medium)](https://leetcode.com/problems/generate-parentheses/)
* [Combination Sum (Medium)](https://leetcode.com/problems/combination-sum/)
* [Binary Watch (Easy)](https://leetcode.com/problems/binary-watch/)

## Solution 1: Backtrack 

```java
// OJ: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// Author: https://leetcode.com/charlesna/
// Time: O(4^n) number of all the combinations
// Space: O(4^n)
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        helper(digits, phone, 0, res, new StringBuilder());
        return res;
    }
    private void helper(String digits, String[] phone, int index, List<String> res, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (char c : phone[digits.charAt(index) - '0'].toCharArray()) {
            sb.append(c);
            helper(digits, phone, index + 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```

## Solution 2: BFS
```java
// OJ: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// Author: https://leetcode.com/charlesna/
// Time: O(4^n) number of all the combinations
// Space: O(4^n)
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Queue<String> q = new LinkedList<>();
        q.offer("");
        for (char num : digits.toCharArray()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                for (char c : phone[num - '0'].toCharArray()) {
                    String next = cur + c;
                    q.offer(next);
                }
            }
        }
        return new ArrayList<>(q);
    }
}
```
