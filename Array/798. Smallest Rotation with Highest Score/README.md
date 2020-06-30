# [798. Smallest Rotation with Highest Score (Hard)](https://leetcode.com/problems/smallest-rotation-with-highest-score/)

<p>&nbsp;Given an array <code>A</code>, we may rotate it by a non-negative integer <code>K</code> so that the array becomes <code>A[K], A[K+1], A{K+2], ... A[A.length - 1], A[0], A[1], ..., A[K-1]</code>.&nbsp; Afterward, any entries that are less than or equal to their index are worth 1 point.&nbsp;</p>

<p>For example, if we have <code>[2, 4, 1, 3, 0]</code>, and we rotate by <code>K = 2</code>, it becomes <code>[1, 3, 0, 2, 4]</code>.&nbsp; This is worth 3 points because 1 &gt; 0 [no points], 3 &gt; 1 [no points], 0 &lt;= 2 [one point], 2 &lt;= 3 [one point], 4 &lt;= 4 [one point].</p>

<p>Over all possible rotations, return the rotation index K that corresponds to the highest score we could receive.&nbsp; If there are multiple answers, return the smallest such index K.</p>

<pre><strong>Example 1:</strong>
<strong>Input:</strong> [2, 3, 1, 4, 0]
<strong>Output:</strong> 3
<strong>Explanation: </strong> 
Scores for each K are listed below: 
K = 0,  A = [2,3,1,4,0],    score 2
K = 1,  A = [3,1,4,0,2],    score 3
K = 2,  A = [1,4,0,2,3],    score 3
K = 3,  A = [4,0,2,3,1],    score 4
K = 4,  A = [0,2,3,1,4],    score 3
</pre>

<p>So we should choose K = 3, which has the highest score.</p>

<p>&nbsp;</p>

<pre><strong>Example 2:</strong>
<strong>Input:</strong> [1, 3, 0, 2, 4]
<strong>Output:</strong> 0
<strong>Explanation: </strong> A will always have 3 points no matter how it shifts.
So we will choose the smallest K, which is 0.
</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>A</code>&nbsp;will have&nbsp;length at most <code>20000</code>.</li>
	<li><code>A[i]</code> will be in the range <code>[0, A.length]</code>.</li>
</ul>


## Solution 

```java
// OJ: https://leetcode.com/problems/smallest-rotation-with-highest-score/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int bestRotation(int[] A) {
        // 0. because the good/bad index are continuous.
        // 1. for each number, find the start index to make it (un)count.
        // 2. iterate the array, find the max prefixSum index
        int l = A.length;
        int[] index = new int[l + 1];
        for (int i = 0; i < l; i++) {
            if (A[i] == 0) continue;
            // turning to good. go to the end of the index.
            index[i + 1]++;
            // turning to bad. target = A[i] - 1.
            int turn = (i - (A[i] - 1) + l) % l;
            if (turn == 0) turn = l;
            index[turn]--;
        }
        int res = 0, sum = 0, max = 0;
        for (int i = 0; i <= l; i++) {
            // System.out.print(index[i] + " ");
            sum += index[i];
            if (sum > max) {
                max = sum;
                res = i;
            }
        }
        return res;
    }
}
```