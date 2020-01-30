---
tags:
  - leetcode
created: '2019/6/23 下午9:39:01'
difficulty: hard
---

# 1096-brace-expansion-ii

## Problem

Under a grammar given below, strings can represent a set of lowercase words.  Let's use `R(expr)` to denote the **set** of words the expression represents.  
  


Grammar can best be understood through simple examples:  
  


* * Single letters represent a singleton set containing that word. 
  * * `R("a") = {"a"}`
  * * `R("w") = {"w"}`
  * 
* * When we take a comma delimited list of 2 or more expressions, we take the union of possibilities. 
  * * `R("{a,b,c}") = {"a","b","c"}`
  * * `R("{{a,b},{b,c}}") = {"a","b","c"}` \(notice the final set only contains each word at most once\)
  * 
* * When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression. 
  * * `R("{a,b}{c,d}") = {"ac","ad","bc","bd"}`
  * * `R("{a{b,c}}{{d,e},f{g,h}}") = R("{ab,ac}{dfg,dfh,efg,efh}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}`
  * 
* 
Formally, the 3 rules for our grammar:  
  


* * For every lowercase letter `x`, we have `R(x) = {x}`
* * For expressions `e_1, e_2, ... , e_k` with `k >= 2`, we have `R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...`
* * For expressions `e_1` and `e_2`, we have `R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}`, where + denotes concatenation, and × denotes the cartesian product.
* 
Given an `expression` representing a set of words under the given grammar, return the sorted list of words that the expression represents.  
  


**Example 1:**  
  


```text
Input: "{a,b}{c{d,e}}"
Output: ["acd","ace","bcd","bce"]
```

**Example 2:**  
  


```text
Input: "{{a,z},a{b,c},{ab,z}}"
Output: ["a","ab","ac","z"]
Explanation: Each distinct word is written only once in the final answer.
```

**Constraints:**  
  


1. 2. `1 <= expression.length <= 50`
3. 4. `expression[i]` consists of `'{'`, `'}'`, `','`or lowercase English letters.
5. 6. The given `expression` represents a set of words based on the grammar given in the description.
7. 
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

