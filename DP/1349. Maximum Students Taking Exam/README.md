# [1349. Maximum Students Taking Exam (Hard)](https://leetcode.com/problems/maximum-students-taking-exam/)

<p>Given a <code>m&nbsp;* n</code>&nbsp;matrix <code>seats</code>&nbsp;&nbsp;that represent seats distributions&nbsp;in a classroom.&nbsp;If a seat&nbsp;is&nbsp;broken, it is denoted by <code>'#'</code> character otherwise it is denoted by a <code>'.'</code> character.</p>

<p>Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting&nbsp;directly in front or behind him. Return the <strong>maximum </strong>number of students that can take the exam together&nbsp;without any cheating being possible..</p>

<p>Students must be placed in seats in good condition.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img height="200" src="https://assets.leetcode.com/uploads/2020/01/29/image.png" width="339">
<pre><strong>Input:</strong> seats = [["#",".","#","#",".","#"],
&nbsp;               [".","#","#","#","#","."],
&nbsp;               ["#",".","#","#",".","#"]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Teacher can place 4 students in available seats so they don't cheat on the exam. 
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> seats = [[".","#"],
&nbsp;               ["#","#"],
&nbsp;               ["#","."],
&nbsp;               ["#","#"],
&nbsp;               [".","#"]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Place all students in available seats. 

</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> seats = [["#",".","<strong>.</strong>",".","#"],
&nbsp;               ["<strong>.</strong>","#","<strong>.</strong>","#","<strong>.</strong>"],
&nbsp;               ["<strong>.</strong>",".","#",".","<strong>.</strong>"],
&nbsp;               ["<strong>.</strong>","#","<strong>.</strong>","#","<strong>.</strong>"],
&nbsp;               ["#",".","<strong>.</strong>",".","#"]]
<strong>Output:</strong> 10
<strong>Explanation:</strong> Place students in available seats in column 1, 3 and 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>seats</code>&nbsp;contains only characters&nbsp;<code>'.'<font face="sans-serif, Arial, Verdana, Trebuchet MS">&nbsp;and</font></code><code>'#'.</code></li>
	<li><code>m ==&nbsp;seats.length</code></li>
	<li><code>n ==&nbsp;seats[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 8</code></li>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>


**Companies**:  
[SAP](https://leetcode.com/company/sap)

**Related Topics**:  
[Dynamic Programming](https://leetcode.com/tag/dynamic-programming/)


## Solution 1:


```java

```


## Solution 2: DFS Memo: use string to represent classroom instead of char[][]


```java
// OJ: https://leetcode.com/problems/maximum-students-taking-exam/
// Author: https://leetcode.com/charlesna/
// Time: O(2^(m*n)
// Space: O(m*n*2^(m*n)
class Solution {
    public int maxStudents(char[][] seats) {
        int row = seats.length;
        int col = seats[0].length;
        Map<String, Integer> map = new HashMap<>();
        String s = print(seats);
        return helper(s, map, row, col);
    }
    
    private int helper(String seats, Map<String, Integer> map, int row, int col) {
        if (map.containsKey(seats)) {
            return map.get(seats);
        }
        
        char[] c = seats.toCharArray();
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int val = i * col + j;
                if (c[val] == '.') {
                    
                    c[val] = '#';
                    
                    //1. choose not to put student
                    res = Math.max(res, helper(new String(c), map, row, col));
                    
                    //2. put a student here and change its right, left back and right right
                    if (j - 1 >= 0 && i + 1 < row) {
                        c[(i + 1) * col + j - 1] = '#';
                    }
                    if (j + 1 < col) {
                        c[i * col + j + 1] = '#';
                        if (i + 1 < row) {
                            c[(i + 1) * col + j + 1] = '#';
                        }
                    }
                    
                    res = Math.max(res, helper(new String(c), map, row, col) + 1);
                }
            }
        }
        
        map.put(seats, res);
        return res;
    }
    
    private String print(char[][] seats) {
        StringBuilder sb = new StringBuilder();
        for (char[] nums : seats) {
            for (char num : nums)
                sb.append(num);
        }
        return sb.toString();
    }
}
```

## Solution 3: Brute Force Backtrack + Prune: TLE

```java



// OJ: https://leetcode.com/problems/maximum-students-taking-exam/
// Author: https://leetcode.com/charlesna/
// Time: O(2^(m*n)) : TLE
// Space: O(m*n*2^(m*n))
class Solution {
    
    int res;
    public int maxStudents(char[][] seats) {
        //start from the fisrt seat in the first row, because the latter row will not influence front row
        int row = seats.length;
        int col = seats[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (seats[i][j] == '.') {
                    count++;
                }
            }
        }
        res = 0;
        helper(seats, 0, 0, 0, count);
        return res;
    }
    
    private void helper(char[][] seats, int x, int y, int c, int left) {
        //System.out.println(x + " " + y + " " + c);
        int row = seats.length;
        int col = seats[0].length;
        
        //prune
        if (left + c <= res) {
            //System.out.println(x + " " + y + " " + " " + c + " " + left + " " + res);
            return;
        }
        
        if (x >= row) {
            res = Math.max(res, c);
            return;
        }
        
        for (int j = y; j < col; j++) {
            if (seats[x][j] == '.') {
                left--;
                if (isAvailable(seats, x, j)) {
                    seats[x][j] = 's';
                    helper(seats, x, j + 1, c + 1, left);
                    seats[x][j] = '.'; 
                }
            }
        }
        
        helper(seats, x + 1, 0, c, left);
    }
    
    private boolean isAvailable(char[][] seats, int x, int j) {
        if (x == 0) {
            if (j - 1 >= 0 && seats[x][j - 1] == 's')  return false;
            return true;
        }
        
        if (j - 1 >= 0 && (seats[x][j - 1] == 's' || seats[x - 1][j - 1] == 's')) {
            return false;
        } 
        
        if (j + 1 < seats[0].length && seats[x - 1][j + 1] == 's') {
            return false;
        }
        return true;
    }
}
```