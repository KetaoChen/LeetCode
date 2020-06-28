# Dynamic Programming

### Answer Template 139: Word Break

**1. Restate the problem:**
This question is asking whether we can use the words in dictionary to form the string.

**2. Come up an idea and briefly state the reason:** 
We can solve this problem using dfs or dynamic programming, since we can easily find out its subproblems.
For example: If the string can be formed using the words in the dictionary, we can find a word in the dictionary that matches last k characters. Then, if we just need to check whether the rest of string can be form by the words in the dictionary. 

**3. Show one example:**
For example: we have a string s, and a list of words. We can start from the last character to check, whether we can have a word to match last k character. 
String s: *******************word
If we can find a word that matches. Then what we need to do is to check whether the rest of the string can be formed by the words in the dictionary, which is a subproblem.

**4. State the subproblem and write the transfer equation.**
For dp, the most important part is the definition of state and transfer equation. 
In this problem, we can use dp[i] to represent whether we can form the s[0-i] using the dictionary.
dp[i] |= dp[j] when s[j-i] is a word for j=[1, i].


### Category

**Sequence:** 

    Painting House: I, II, Falling Path I, II, Longest Turbulent Subarray, Minimum Swaps To Make Sequences Increasing
    House Robber: I, II, III, Delete and Earn, Pizza with 3n Slices, Plant Tree(luogu 1484)
    Match: Word Break,
    LIS(Sort): LIS, Russion Enve, Largest Divisible Subset
    Other: New 21 Game, Build Array Where You Can Find The Maximum Exactly K Comparisons
    
**Bi-Sequence:** 
    
    Match: Regex, Edit Distance
    LCS: Delete Operation for Two Strings, Interleaving String, Shortest Common Supersequence
    

**Backpack:** 

    01: One and Zero
    Complete: Combination Sum
    Other: Tallest Billboard

**Interval:** If it is explicitly said divide into k part. e.g. Palin Partition III.  {XXXXXXX}[k....i]  dp[i][j] = min|max(dp[i-1][k-1], max[k][i])
    
    Eliminate: Burst Balloon, Remove Boxes, Strange Printer
    K Parts: Largest Sum of Averages, Minimum Difficulty of a Job Schedule, Partition Array for Maximum Sum, Painting House III
    Palindrone: Partition I, II, III, Remove
    Binary Search (minimize the max| maximize the min): Split Array Largest Sum

**Coordinate:** 
    
    Path: Dungeon Game, Number of Path with Max Score
    Match: Maximal Square, Count Submatrix
    Bit: Maximum Students Taking Exam

**DFS & Memo:** 

    Jump Game IV

**BitMask:** 

    Beautiful Arrangement, TSP, SuperString, Number of Squareful Arrays, Parallel Courses II

**Digit:** Use tree analysis, divide the cur number into two part. On the upper bound or not.

    Find All Good Strings, Number of Digit One, Digit Count in Range, Find All Good Strings, Rotated Digits

### Tricks
1. Initialization. 
Sometimes, it is difficult to find. So use dfs + memo is easier to solve. e.g. 1340. Jump Game V
Sometimes, we need to think about the basic case clear. e.g. 956. Tallest Billboard & 10. Regular Expression Matching
Invalid state initialization: 1473. Painting House III
2. Check the bound. 
Because of the transfer equation, we have to make sure the indexes are not out of bound. 
3. Update order.
We need to update the state which will not effect other result in this level first. e.g. Backpack problems. From larger capacity. 
4. Circle can be translate to sequence. 213. House Robber II
5. Bit Manipulation to check the availability of each digit. Using (i>>k) & 1 == 0 or (i >>k) & 1 != 0
6. Subset. for (int subset = mast; subset > 0; subset = (subset - 1) & mask)

### Tech
1. Bit Mask: 
Use bit to represent state, and O(1) time complexity to check the state.
2. Elimination variable:
When there is relationship between the varibles in the state, we can utilize the relationship to reduce dimension. (Avg, Diff, Sum)
3. Use path array to backtrack the path and print it out. (TSP 943, SuperSequence 1092, Largest Divisible Subset 368)
4. Prefix Sum: The current dp[i][j] is related to the prefix sum of dp[i-1][0:k] (903 DI Seq)
5. Utilize all the information from the problem. The range of values. (Value backpack. ABC_DP_E)
6. String & Digit: Using KMP (Find All Good Strings 1397)
7. Check whether the last element can make some contribution to the res. (1420)
8. Bit Mask with dependency and the usage of subset. (1494)
