# [835. Image Overlap (Medium)](https://leetcode.com/problems/image-overlap/)

<p>Two images <code>A</code> and <code>B</code> are given, represented as&nbsp;binary, square matrices of the same size.&nbsp; (A binary matrix has only 0s and 1s as values.)</p>

<p>We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.&nbsp; After, the <em>overlap</em> of this translation is the number of positions that have a 1 in both images.</p>

<p>(Note also that a translation does <strong>not</strong> include any kind of rotation.)</p>

<p>What is the largest possible overlap?</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input: </strong>A = [[1,1,0],
            [0,1,0],
&nbsp;           [0,1,0]]
&nbsp;      B = [[0,0,0],
&nbsp;           [0,1,1],
&nbsp;           [0,0,1]]
<strong>Output: </strong>3
<strong>Explanation:</strong> We slide A to right by 1 unit and down by 1 unit.</pre>

<p><strong>Notes:</strong>&nbsp;</p>

<ol>
	<li><code>1 &lt;= A.length = A[0].length = B.length = B[0].length &lt;= 30</code></li>
	<li><code>0 &lt;=&nbsp;A[i][j], B[i][j] &lt;= 1</code></li>
</ol>


**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/)

## Solution 

```java
// OJ: https://leetcode.com/problems/image-overlap/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3)
// Space: O(n)
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int row = A.length, col = A[0].length;
        int[] a = new int[row], b = new int[row];
        for (int i = 0; i < row; i++) {
            int sa = 0, sb = 0;
            for (int j = 0; j < col; j++) {
                sa = sa << 1 | A[i][j];
                sb = sb << 1 | B[i][j];
            }
            a[i] = sa;
            b[i] = sb;
            // System.out.println(i + " " + a[i] + " "+ b[i]);
        }
        int res = 0;
        // iterate the starting point of A.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int tempA = 0, tempB = 0;
                // calculate the overlap of each row
                for (int k = 0; k + i < row; k++) {
                    tempA += Integer.bitCount(a[k] >> j & b[k + i]);
                    tempB += Integer.bitCount(b[k] >> j & a[k + i]);
                    // System.out.println(i + " " + j + " " + temp);
                }
                res = Math.max(res, Math.max(tempA, tempB));
            }
        }
        return res;
    }
}
```