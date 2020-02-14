# [765. Couples Holding Hands (Hard)](https://leetcode.com/problems/couples-holding-hands/)

<p>
N couples sit in 2N seats arranged in a row and want to hold hands.  We want to know the minimum number of swaps so that every couple is sitting side by side.  A <i>swap</i> consists of choosing <b>any</b> two people, then they stand up and switch seats. 
</p><p>
The people and seats are represented by an integer from <code>0</code> to <code>2N-1</code>, the couples are numbered in order, the first couple being <code>(0, 1)</code>, the second couple being <code>(2, 3)</code>, and so on with the last couple being <code>(2N-2, 2N-1)</code>.
</p><p>
The couples' initial seating is given by <code>row[i]</code> being the value of the person who is initially sitting in the i-th seat.

</p><p><b>Example 1:</b><br></p><pre><b>Input:</b> row = [0, 2, 1, 3]
<b>Output:</b> 1
<b>Explanation:</b> We only need to swap the second (row[1]) and third (row[2]) person.
</pre><p></p>

<p><b>Example 2:</b><br></p><pre><b>Input:</b> row = [3, 2, 0, 1]
<b>Output:</b> 0
<b>Explanation:</b> All couples are already seated side by side.
</pre><p></p>

<p>
<b>Note:</b>
</p><ol> 
<li> <code>len(row)</code> is even and in the range of <code>[4, 60]</code>.</li>
<li> <code>row</code> is guaranteed to be a permutation of <code>0...len(row)-1</code>.</li>
</ol>

**Companies**:  
[Google](https://leetcode.com/company/google)

**Related Topics**:  
[Greedy](https://leetcode.com/tag/greedy/), [Union Find](https://leetcode.com/tag/union-find/), [Graph](https://leetcode.com/tag/graph/)

**Similar Questions**:
* [First Missing Positive (Hard)](https://leetcode.com/problems/first-missing-positive/)
* [Missing Number (Easy)](https://leetcode.com/problems/missing-number/)
* [K-Similar Strings (Hard)](https://leetcode.com/problems/k-similar-strings/)

## Solution 1: Math Swap

```java
// OJ: https://leetcode.com/problems/couples-holding-hands/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int minSwapsCouples(int[] row) {
        //[0, 2, 1, 3]
        //if after a switch, there are two pairs of couple, we swap their positions.
        //else : how to swap? 
        //
        int res = 0;
        int l = row.length;
        int index = 0;
        while (index < l) {
            int first = row[index];
            int second = row[index + 1];
            if (first / 2 == second / 2) {
                index += 2;
                continue;
            }
            res++;
            int target = first / 2;
            for (int i = index + 2; i < l; i++) {
                if (row[i] / 2 == target) {
                    int temp = row[index + 1];
                    row[index + 1] = row[i];
                    row[i] = temp;
                    break;
                }
            }
            index += 2;
        }
        return res;
    }
}

```

## Solution 2: Union Find

```java

class Solution {
    //union find
    ///for each union with k people, we need k - 1 swap.
    //if there are n people, with m unions, the total swap is n - m
    
    class UnionFind {
        int[] parent;
        int count;
        
        public UnionFind(int num) {
            parent = new int[num];
            for (int i = 0; i < num; i++) {
                parent[i] = i % 2 == 0 ? i : i - 1;
            }
            count = num / 2;
        }
        
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }
        
        public void union(int x, int y) {
            int fatherX = find(x);
            int fatherY = find(y);
            // System.out.println(x + " " + y);
            // System.out.println(fatherX + " " + fatherY);
            if (fatherX != fatherY) {
                System.out.println(fatherX + " " + fatherY);
                parent[fatherX] = fatherY;
                count--;
            }
        }
    }
    public int minSwapsCouples(int[] row) {
        int l = row.length;
        UnionFind uf = new UnionFind(l);
        // for (int i = 0; i < l; i++) {
        //     System.out.print(uf.parent[i] + " ");
        // }
        // System.out.println();
        for (int i = 0; i < l; i += 2) {
            uf.union(row[i], row[i + 1]);
            //System.out.println(i + " " + uf.count);
        }
        return l / 2 - uf.count;
    }
}
```