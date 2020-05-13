# [862. Shortest Subarray with Sum at Least K (Hard)](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/)

<p>Return the <strong>length</strong> of the shortest, non-empty, contiguous&nbsp;subarray of <code>A</code> with sum at least <code>K</code>.</p>

<p>If there is no non-empty subarray with sum at least <code>K</code>, return <code>-1</code>.</p>

<p>&nbsp;</p>

<ol>
</ol>

<div>
<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>A = <span id="example-input-1-1">[1]</span>, K = <span id="example-input-1-2">1</span>
<strong>Output: </strong><span id="example-output-1">1</span>
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre><strong>Input: </strong>A = <span id="example-input-2-1">[1,2]</span>, K = <span id="example-input-2-2">4</span>
<strong>Output: </strong><span id="example-output-2">-1</span>
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre><strong>Input: </strong>A = <span id="example-input-3-1">[2,-1,2]</span>, K = <span id="example-input-3-2">3</span>
<strong>Output: </strong><span id="example-output-3">3</span>
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 50000</code></li>
	<li><code>-10 ^ 5&nbsp;&lt;= A[i] &lt;= 10 ^ 5</code></li>
	<li><code>1 &lt;= K &lt;= 10 ^ 9</code></li>
</ol>
</div>
</div>
</div>


**Companies**:  
[Google](https://leetcode.com/company/google), [Goldman Sachs](https://leetcode.com/company/goldman-sachs), [Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Binary Search](https://leetcode.com/tag/binary-search/), [Queue](https://leetcode.com/tag/queue/)

## Solution 

```java
// OJ: https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int l = A.length;
        int[] sum = new int[l + 1];
        for (int i = 1; i <= l; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
        }
        int res = l + 1;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i <= l; i++) {
            while (!dq.isEmpty() && sum[i] < sum[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            while (!dq.isEmpty() && sum[i] - sum[dq.peekFirst()] >= K) {
                res = Math.min(res, i - dq.peekFirst());
                dq.pollFirst();
            }
        }
        return res == l + 1 ? -1 : res;
    }
}
```