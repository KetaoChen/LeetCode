# [1096. Brace Expansion II (Hard)](https://leetcode.com/problems/brace-expansion-ii/)

<p>Under a grammar given below, strings can represent a set of lowercase words.&nbsp; Let's&nbsp;use <code>R(expr)</code>&nbsp;to denote the <strong>set</strong> of words the expression represents.</p>

<p>Grammar can best be understood through simple examples:</p>

<ul>
	<li>Single letters represent a singleton set containing that word.
	<ul>
		<li><code>R("a") = {"a"}</code></li>
		<li><code>R("w") = {"w"}</code></li>
	</ul>
	</li>
	<li>When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
	<ul>
		<li><code>R("{a,b,c}") = {"a","b","c"}</code></li>
		<li><code>R("{{a,b},{b,c}}") = {"a","b","c"}</code>&nbsp;(notice the final set only contains each word at most once)</li>
	</ul>
	</li>
	<li>When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
	<ul>
		<li><code>R("{a,b}{c,d}") = {"ac","ad","bc","bd"}</code></li>
		<li><code>R("a{b,c}{d,e}f{g,h}")&nbsp;= {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}</code></li>
	</ul>
	</li>
</ul>

<p>Formally, the 3 rules for our grammar:</p>

<ul>
	<li>For every lowercase letter <code>x</code>, we have <code>R(x) = {x}</code></li>
	<li>For expressions <code>e_1, e_2, ... , e_k</code>&nbsp;with <code>k &gt;= 2</code>, we have <code>R({e_1,e_2,...}) = R(e_1)&nbsp;¡È R(e_2)&nbsp;¡È ...</code></li>
	<li>For&nbsp;expressions <code>e_1</code> and <code>e_2</code>, we have <code>R(e_1 + e_2) = {a + b for (a, b) in&nbsp;R(e_1)&nbsp;¡Á R(e_2)}</code>, where + denotes concatenation, and ¡Á denotes the cartesian product.</li>
</ul>

<p>Given an <code>expression</code> representing a set of words under the given grammar, return the&nbsp;sorted list of words that the expression represents.</p>

<p>&nbsp;</p>

<div>
<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">"{a,b}{c,{d,e}}"</span>
<strong>Output: </strong><span id="example-output-1">["ac","ad","ae","bc","bd","be"]</span>
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong><span>"{{a,z},a{b,c},{ab,z}}"</span>
<strong>Output: </strong><span>["a","ab","ac","z"]</span>
<strong>Explanation: </strong>Each distinct word is written only once in the final answer.
</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ol>
	<li><code>1 &lt;= expression.length &lt;= 60</code></li>
	<li><code>expression[i]</code> consists of <code>'{'</code>, <code>'}'</code>, <code>','</code>or lowercase English letters.</li>
	<li>The given&nbsp;<code>expression</code>&nbsp;represents a set of words based on the grammar given in the description.</li>
</ol>
</div>
</div>


**Companies**:  
[Adobe](https://leetcode.com/company/adobe)

**Related Topics**:  
[String](https://leetcode.com/tag/string/)

**Similar Questions**:
* [Brace Expansion (Medium)](https://leetcode.com/problems/brace-expansion/)

## Solution 1: Stack

```java
// OJ: https://leetcode.com/problems/brace-expansion-ii/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> stack = new Stack<>();
        stack.push(new HashSet<>());
        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
                continue;
            }
            combine(stack, Arrays.asList(new String[]{sb.toString()}));
            sb = new StringBuilder();
            if (c == '{') {
                stack.push(new HashSet<>());
                stack.push(new HashSet<>());
            }
            else if (c == '}') {
                getRes(stack);
                List<String> list = new ArrayList<>(stack.pop());
                combine(stack, list);
            }
            else if (c == ',') {
                getRes(stack);
                stack.push(new HashSet<>());
            }
        }
        combine(stack, Arrays.asList(new String[]{sb.toString()}));
        getRes(stack);
        List<String> res = new ArrayList<>(stack.peek());
        Collections.sort(res);
        return res;
    }
    private void combine(Stack<Set<String>> stack, List<String> list) {
        if (list.size() == 1 && list.get(0).length() == 0) return;
        if (stack.peek().isEmpty()) {
            for (String s : list) {
                stack.peek().add(s);
            }
            return;
        }
        Set<String> combo = new HashSet<>();
        for (String s : stack.pop()) {
            for (String next : list) {
                combo.add(s + next);
            }
        }
        stack.push(combo);
    }
    private void getRes(Stack<Set<String>> stack) {
        if (stack.size() == 1) return;
        Set<String> cur = stack.pop();
        for (String s : cur) {
            stack.peek().add(s);
        }
    }
}
```

## Solution 2: Recursion

```java
// OJ: https://leetcode.com/problems/brace-expansion-ii/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    int index = 0;
    public List<String> braceExpansionII(String expression) {
        Set<String> set = new HashSet<>();
        Set<String> temp = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (index < expression.length()) {
            char c = expression.charAt(index++);
            if (Character.isLetter(c)) {
                sb.append(c);
                continue;
            }
            temp = combo(temp, Arrays.asList(new String[]{sb.toString()}));
            sb = new StringBuilder();
            if (c == '{') {
                List<String> next = braceExpansionII(expression);
                temp = combo(temp, next);
            }
            else if (c == '}') {
                return getRes(set, temp);
            }
            else if (c == ',') {
                for (String s : temp) set.add(s);
                temp = new HashSet<>();
            }
        }
        if (sb.length() > 0) {
            temp = combo(temp, Arrays.asList(new String[]{sb.toString()}));
        }
        return getRes(set, temp);   
    }
    private Set<String> combo(Set<String> temp, List<String> next) {
        if (temp.isEmpty()) {
            for (String n : next) {
                temp.add(n);
            }
            return temp;
        }
        Set<String> combo = new HashSet<>();
        for (String s : temp) {
            for (String add : next) {
                combo.add(s + add);
            }
        }
        return combo;
    }
    private List<String> getRes(Set<String> set, Set<String> temp) {
        for (String s : temp) set.add(s);
        List<String> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }
}
```