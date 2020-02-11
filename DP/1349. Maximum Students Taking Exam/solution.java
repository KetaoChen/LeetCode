// OJ: https://leetcode.com/problems/maximum-students-taking-exam/
// Author: https://leetcode.com/charlesna/
// Time: O(m*4^n)
// Space: O(m*(2^n)
class Solution {
    public int maxStudents(char[][] seats) {
        int row = seats.length;
        int col = seats[0].length;
        //this mask represent if all the seat are student.
        //the answer must be a subset of the mask
        int[] mask = new int[row + 1];
        for (int i = 0; i < row; i++) {
            int val = 0;
            for (char c : seats[i]) {
                val = c == '.' ? val * 2 + 1 : val * 2;
            }
            mask[i + 1] = val;
        }
        //dp[i][j] represent when the state of ith row is j, the max students for first ith row.
        //dp[i][j] = max(dp[i - 1][k]) where k is valid and no conflict between j and k
        int[][] dp = new int[row + 1][(1 << col)];
        for (int m = 1; m <= row; m++) {
            Arrays.fill(dp[m], -1);
            for (int j = 0; j < (1 << col); j++) {
                //this state should be a subset of mask
                if ((j | mask[m]) != mask[m]) {
                    continue;
                }
                //there should be no adjacent student
                int count = countBit(j);
                if (count == -1) {
                    continue;
                }
                for (int k = 0; k < (1 << col); k++) {
                    if (dp[m - 1][k] != -1 && ((j << 1) & k) == 0 && (j & (k << 1)) == 0) {
                        dp[m][j] = Math.max(dp[m][j], dp[m - 1][k] + count);
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < (1 << col); i++) {
            res = Math.max(res, dp[row][i]);
        }
        return res;
    }
    private int countBit(int i) {
        if ((i & (i << 1)) != 0) {
            return -1;
        }
        int res = 0;
        while (i > 0) {
            res += i % 2;
            i /= 2;
        }
        return res;
    }
}


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