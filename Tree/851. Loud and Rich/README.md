# [851. Loud and Rich (Medium)](https://leetcode.com/problems/loud-and-rich/)

<p>In a group of N people (labelled <code>0, 1, 2, ..., N-1</code>), each person has different amounts of money, and different levels of quietness.</p>

<p>For convenience, we'll call the person with label <code>x</code>, simply "person <code>x</code>".</p>

<p>We'll say that <code>richer[i] = [x, y]</code> if person <code>x</code>&nbsp;definitely has more money than person&nbsp;<code>y</code>.&nbsp; Note that <code>richer</code>&nbsp;may only be a subset of valid observations.</p>

<p>Also, we'll say <code>quiet[x] = q</code> if person <font face="monospace">x</font>&nbsp;has quietness <code>q</code>.</p>

<p>Now, return <code>answer</code>, where <code>answer[x] = y</code> if <code>y</code> is the least quiet person (that is, the person <code>y</code> with the smallest value of <code>quiet[y]</code>), among all people&nbsp;who definitely have&nbsp;equal to or more money than person <code>x</code>.</p>

<p>&nbsp;</p>

<div>
<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>richer = <span id="example-input-1-1">[[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]]</span>, quiet = <span id="example-input-1-2">[3,2,5,4,6,1,7,0]</span>
<strong>Output: </strong><span id="example-output-1">[5,5,2,5,4,5,6,7]</span>
<strong>Explanation: </strong>
answer[0] = 5.
Person 5 has more money than 3, which has more money than 1, which has more money than 0.
The only person who is quieter (has lower quiet[x]) is person 7, but
it isn't clear if they have more money than person 0.

answer[7] = 7.
Among all people that definitely have equal to or more money than person 7
(which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x])
is person 7.

The other answers can be filled out with similar reasoning.
</pre>
</div>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= quiet.length = N &lt;= 500</code></li>
	<li><code>0 &lt;= quiet[i] &lt; N</code>, all <code>quiet[i]</code> are different.</li>
	<li><code>0 &lt;= richer.length &lt;= N * (N-1) / 2</code></li>
	<li><code>0 &lt;= richer[i][j] &lt; N</code></li>
	<li><code>richer[i][0] != richer[i][1]</code></li>
	<li><code>richer[i]</code>'s are all different.</li>
	<li>The&nbsp;observations in <code>richer</code> are all logically consistent.</li>
</ol>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon)

**Related Topics**:  
[Depth-first Search](https://leetcode.com/tag/depth-first-search/)

## Solution : Brute Force

```java
// OJ: https://leetcode.com/problems/loud-and-rich/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n)
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        //n people
        //the richer relation is transisable
        //res[i] for all people richer than i, res[i] is the quietest.
        //for each person, find out all people that is richer than him
            //find out the people who is the quietest.
        //time: O(n^2)
        int n = quiet.length;
        List<Integer>[] richerThan = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            richerThan[i] = new ArrayList<>();
        }
        for (int i = 0; i < richer.length; i++) {
            richerThan[richer[i][1]].add(richer[i][0]);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int temp = quiet[i];
            res[i] = i;
            Queue<Integer> list = new LinkedList<>();
            Set<Integer> set = new HashSet<>();
            set.add(i);
            list.offer(i);
            while (!list.isEmpty()) {
                int cur = list.poll();
                if (quiet[cur] < temp) {
                    temp = quiet[cur];
                    res[i] = cur;
                }
                for (int next : richerThan[cur]) {
                    if (!set.contains(next)) {
                        list.offer(next);
                        set.add(next);
                    }
                }
            }
        }
        return res;
    }
}
```