# [254. Factor Combinations (Medium)](https://leetcode.com/problems/factor-combinations/)

<p>Numbers can be regarded as product of its factors. For example,</p>

<pre>8 = 2 x 2 x 2;
  = 2 x 4.
</pre>

<p>Write a function that takes an integer <i>n</i> and return all possible combinations of its factors.</p>

<p><b>Note:</b></p>

<ol>
	<li>You may assume that <i>n</i> is always positive.</li>
	<li>Factors should be greater than 1 and less than <i>n</i>.</li>
</ol>

<p><b>Example&nbsp;1: </b></p>

<pre><strong>Input:</strong> <code>1</code>
<strong>Output:</strong> []
</pre>

<p><b>Example&nbsp;2: </b></p>

<pre><strong>Input:</strong> <code>37</code>
<strong>Output:</strong>[]</pre>

<p><b>Example&nbsp;3: </b></p>

<pre><strong>Input:</strong> <code>12</code>
<strong>Output:</strong>
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]</pre>

<p><b>Example&nbsp;4: </b></p>

<pre><strong>Input:</strong> <code>32</code>
<strong>Output:</strong>
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
</pre>


**Companies**:  
[LinkedIn](https://leetcode.com/company/linkedin)

**Related Topics**:  
[Backtracking](https://leetcode.com/tag/backtracking/)

**Similar Questions**:
* [Combination Sum (Medium)](https://leetcode.com/problems/combination-sum/)

## Solution 

```java
// OJ: https://leetcode.com/problems/factor-combinations/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<Integer>(), n, 2);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int n, int start) {
        for (int i = start; i * i <= n; i++) {
                if (n % i == 0 && n / i >= i) {
                temp.add(i);
                temp.add(n / i);
                res.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
                helper(res, temp, n / i, i);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
```

```java
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, 2);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> temp, int n, int start) {
        if (n == 1) {
            if (temp.size() > 1) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        
        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                temp.add(i);
                helper(res, temp, n / i, i);
                temp.remove(temp.size() - 1);
            }
        }
        
        temp.add(n);
        helper(res, temp, 1, n);
        temp.remove(temp.size() - 1);
    }
}
```
