---
tags: ["leetcode"]
created: "2019/6/23 下午9:39:01"
difficulty: "hard"
---

# [1096-brace-expansion-ii](https://leetcode.com/problems/brace-expansion-ii/)

## Problem
<div><p>Under a grammar given below, strings can represent a set of lowercase words.&nbsp; Let's&nbsp;use <code>R(expr)</code>&nbsp;to denote the <strong>set</strong> of words the expression represents.</p><br><br><p>Grammar can best be understood through simple examples:</p><br><br><ul><br>	<li>Single letters represent a singleton set containing that word.<br>	<ul><br>		<li><code>R("a") = {"a"}</code></li><br>		<li><code>R("w") = {"w"}</code></li><br>	</ul><br>	</li><br>	<li>When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.<br>	<ul><br>		<li><code>R("{a,b,c}") = {"a","b","c"}</code></li><br>		<li><code>R("{{a,b},{b,c}}") = {"a","b","c"}</code>&nbsp;(notice the final set only contains each word at most once)</li><br>	</ul><br>	</li><br>	<li>When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.<br>	<ul><br>		<li><code>R("{a,b}{c,d}") = {"ac","ad","bc","bd"}</code></li><br>		<li><code>R("{a{b,c}}{{d,e},f{g,h}}") = R("{ab,ac}{dfg,dfh,efg,efh}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}</code></li><br>	</ul><br>	</li><br></ul><br><br><p>Formally, the 3 rules for our grammar:</p><br><br><ul><br>	<li>For every lowercase letter <code>x</code>, we have <code>R(x) = {x}</code></li><br>	<li>For expressions <code>e_1, e_2, ... , e_k</code>&nbsp;with <code>k &gt;= 2</code>, we have <code>R({e_1,e_2,...}) = R(e_1)&nbsp;∪ R(e_2)&nbsp;∪ ...</code></li><br>	<li>For&nbsp;expressions <code>e_1</code> and <code>e_2</code>, we have <code>R(e_1 + e_2) = {a + b for (a, b) in&nbsp;R(e_1)&nbsp;× R(e_2)}</code>, where + denotes concatenation, and × denotes the cartesian product.</li><br></ul><br><br><p>Given an <code>expression</code> representing a set of words under the given grammar, return the&nbsp;sorted list of words that the expression represents.</p><br><br><p>&nbsp;</p><br><br><div><br><p><strong>Example 1:</strong></p><br><br><pre><strong>Input: </strong><span id="example-input-1-1">"{a,b}{c{d,e}}"</span><br><strong>Output: </strong><span id="example-output-1">["acd","ace","bcd","bce"]</span><br></pre><br><br><div><br><p><strong>Example 2:</strong></p><br><br><pre><strong>Input: </strong><span>"{{a,z},a{b,c},{ab,z}}"</span><br><strong>Output: </strong><span>["a","ab","ac","z"]</span><br><strong>Explanation: </strong>Each distinct word is written only once in the final answer.<br></pre><br><br><p>&nbsp;</p><br><br><p><strong>Constraints:</strong></p><br><br><ol><br>	<li><code>1 &lt;= expression.length &lt;= 50</code></li><br>	<li><code>expression[i]</code> consists of <code>'{'</code>, <code>'}'</code>, <code>','</code>or lowercase English letters.</li><br>	<li>The given&nbsp;<code>expression</code>&nbsp;represents a set of words based on the grammar given in the description.</li><br></ol><br></div><br></div><br></div>

## Solution

java
```java
class Solution {
    
    //three operations
    //0. 'c': add the char to the stringbuilder
    //1. ',': add the sb.toString to the set
    //2. '{}' union
        // '{' : start a new function
                //
        // '}' : return the list. check whether they need to multiply with the list before it.
    //3. '{}{}' product
    
    int index = 0;
    public List<String> braceExpansionII(String expression) {
        List<String> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        Set<String> set = new  HashSet<>();
        StringBuilder sb = new StringBuilder();
        
        while (index < expression.length()) {
            char c = expression.charAt(index);
            index++;
            if (c == ',') {
                temp = getCombo(temp, new ArrayList<String>(Arrays.asList(sb.toString())));
                for (String s : temp) {
                    set.add(s);
                }
                temp = new ArrayList<>();
                sb.delete(0, sb.length());
            }
            if (c == '{') {
                temp = getCombo(temp, new ArrayList<String>(Arrays.asList(sb.toString())));
                sb.delete(0, sb.length());
                temp = getCombo(temp, braceExpansionII(expression));               
            }
            if (c == '}') {
                temp = getCombo(temp, new ArrayList<String>(Arrays.asList(sb.toString())));
                for (String s : temp) {
                    set.add(s);
                }
                for (String s : set) {
                    res.add(s);
                }
                Collections.sort(res);
                return res;
            }
            if (Character.isLetter(c)) {
                sb.append(c);
            }
        }
        temp = getCombo(temp, new ArrayList<String>(Arrays.asList(sb.toString())));
        for (String s : temp) {
            set.add(s);
        }
        for (String s : set) {
            res.add(s);
        }
        Collections.sort(res);
        return res;
    }
    
    private List<String> getCombo(List<String> temp, List<String> next) {
        List<String> res = new ArrayList<>();
        if (next.get(0).equals("")) {
            return temp;
        }
        if (temp.isEmpty()) {
            return next;
        }
        
        
        for (String s : temp) {
            for (String str : next) {
                res.add(s + str);
            }
        }
        return res;
    }
    
    
}
​
```
