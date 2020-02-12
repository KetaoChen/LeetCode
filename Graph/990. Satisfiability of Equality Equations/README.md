# [990. Satisfiability of Equality Equations (Medium)](https://leetcode.com/problems/satisfiability-of-equality-equations/)

<p>Given an array <font face="monospace">equations</font>&nbsp;of strings that represent relationships between variables, each string <code>equations[i]</code>&nbsp;has length <code>4</code> and takes one of two different forms: <code>"a==b"</code> or <code>"a!=b"</code>.&nbsp; Here, <code>a</code> and <code>b</code> are lowercase letters (not necessarily different) that represent one-letter variable names.</p>

<p>Return <code>true</code>&nbsp;if and only if it is possible to assign integers to variable names&nbsp;so as to satisfy all the given equations.</p>

<p>&nbsp;</p>

<ol>
</ol>

<div>
<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong><span id="example-input-1-1">["a==b","b!=a"]</span>
<strong>Output: </strong><span id="example-output-1">false</span>
<strong>Explanation: </strong>If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong><span id="example-input-2-1">["b==a","a==b"]</span>
<strong>Output: </strong><span id="example-output-2">true</span>
<strong>Explanation: </strong>We could assign a = 1 and b = 1 to satisfy both equations.
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre><strong>Input: </strong><span id="example-input-3-1">["a==b","b==c","a==c"]</span>
<strong>Output: </strong><span id="example-output-3">true</span>
</pre>

<div>
<p><strong>Example 4:</strong></p>

<pre><strong>Input: </strong><span id="example-input-4-1">["a==b","b!=c","c==a"]</span>
<strong>Output: </strong><span id="example-output-4">false</span>
</pre>

<div>
<p><strong>Example 5:</strong></p>

<pre><strong>Input: </strong><span id="example-input-5-1">["c==c","b==d","x!=z"]</span>
<strong>Output: </strong><span id="example-output-5">true</span>
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= equations.length &lt;= 500</code></li>
	<li><code>equations[i].length == 4</code></li>
	<li><code>equations[i][0]</code> and <code>equations[i][3]</code> are lowercase letters</li>
	<li><code>equations[i][1]</code> is either <code>'='</code> or <code>'!'</code></li>
	<li><code>equations[i][2]</code> is&nbsp;<code>'='</code></li>
</ol>
</div>
</div>
</div>
</div>
</div>


**Companies**:  
[Sumologic](https://leetcode.com/company/sumologic)

**Related Topics**:  
[Union Find](https://leetcode.com/tag/union-find/), [Graph](https://leetcode.com/tag/graph/)

## Solution 1: Two Pass but Concise

```java
// OJ: https://leetcode.com/problems/satisfiability-of-equality-equations/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    int[] uf = new int[26];
    public boolean equationsPossible(String[] equations) {
        for (int i = 0; i < 26; ++i) uf[i] = i;
        for (String e : equations)
            if (e.charAt(1) == '=')
                uf[find(e.charAt(0) - 'a')] = find(e.charAt(3) - 'a');
        for (String e : equations)
            if (e.charAt(1) == '!' && find(e.charAt(0) - 'a') == find(e.charAt(3) - 'a'))
                return false;
        return true;
    }
    public int find(int x) {
        if (x != uf[x]) uf[x] = find(uf[x]);
        return uf[x];
    }
}
```

## Solution 2: One Pass
```java

class Solution {
    
    int[] parents;
    Set<Integer>[] diff;
    public boolean equationsPossible(String[] equations) {
        //if they are equal, we do union, check, whether they are in the diff group
        //if not equal, we need to check, whether they are in the same group, if not, record the value in the set
        parents = new int[26];
        diff = new HashSet[26];
        for (int i = 0; i < 26; i++) {
            parents[i] = i;
            diff[i] = new HashSet<>();
        }
        
        for (String equation : equations) {
            int c1 = equation.charAt(0) - 'a';
            int c2 = equation.charAt(3) - 'a';
            int father1 = find(c1);
            int father2 = find(c2);
            
            if (equation.charAt(1) == '=') {
                if (diff[father1].contains(c2) || diff[father2].contains(c1)) {
                    return false;
                }
                if (father1 != father2) {
                    parents[father1] = father2;
                    for (int d : diff[father1]) {
                        diff[father2].add(d);
                    }
                }
            }
            else {
                if (father1 == father2) {
                    return false;
                }
                diff[father1].add(c2);
                diff[father2].add(c1);
            }
        }
        return true;
    }
    
    private int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        parents[x] = find(parents[x]);
        return parents[x];
    }
}
```