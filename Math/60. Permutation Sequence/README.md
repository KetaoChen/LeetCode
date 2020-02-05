# [60. Permutation Sequence (Medium)](https://leetcode.com/problems/permutation-sequence/)

<p>The set <code>[1,2,3,...,<em>n</em>]</code> contains a total of <em>n</em>! unique permutations.</p>

<p>By listing and labeling all of the permutations in order, we get the following sequence for <em>n</em> = 3:</p>

<ol>
	<li><code>"123"</code></li>
	<li><code>"132"</code></li>
	<li><code>"213"</code></li>
	<li><code>"231"</code></li>
	<li><code>"312"</code></li>
	<li><code>"321"</code></li>
</ol>

<p>Given <em>n</em> and <em>k</em>, return the <em>k</em><sup>th</sup> permutation sequence.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>Given <em>n</em> will be between 1 and 9 inclusive.</li>
	<li>Given&nbsp;<em>k</em>&nbsp;will be between 1 and <em>n</em>! inclusive.</li>
</ul>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> n = 3, k = 3
<strong>Output:</strong> "213"
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> n = 4, k = 9
<strong>Output:</strong> "2314"
</pre>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Math](https://leetcode.com/tag/math/), [Backtracking](https://leetcode.com/tag/backtracking/)

**Similar Questions**:
* [Next Permutation (Medium)](https://leetcode.com/problems/next-permutation/)
* [Permutations (Medium)](https://leetcode.com/problems/permutations/)

## Solution 

```java
// OJ: https://leetcode.com/problems/permutation-sequence/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public String getPermutation(int n, int k) {
        int[] perm = new int[10];
        perm[1] = perm[0] = 1;
        for (int i = 2; i <= 9; i++) {
            perm[i] = perm[i - 1] * i;
        }
        boolean[] used = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int next = 1;
            while (used[next]) {
                next++;
            }
            while (k > perm[n - 1]) {
                //System.out.println(k + " " + perm[n - 1] + " " + n);
                next++;
                while (used[next]) {
                    next++;
                }
                k -= perm[n - 1];
            }
            sb.append(next);
            //System.out.println(sb.toString());
            used[next] = true;
            n--;
        }
        return sb.toString();
    }
}
```