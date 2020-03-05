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

**Sequence:** 139, 837, 931, 1289

**Backpack:** 377, 474, 956

**Interval:** 

**Coordinate:** 174, 221, 1277, 1301, 1349

**Partition:** 664

**DFS & Memo:**1340



### Tricks
1. Initialization. 
Sometimes, it is difficult to find. So use dfs + memo is easier to solve. e.g. 1340. Jump Game V
Sometimes, we need to think about the basic case clear. e.g. 956. Tallest Billboard & 10. Regular Expression Matching
2. Check the bound. 
Because of the transfer equation, we have to make sure the indexes are not out of bound. 
3. Update order.
We need to update the state which will not effect other result in this level first. e.g. Backpack problems. From larger capacity. 

### Tech
1. Bit Mask: 
Use bit to represent state, and O(1) time complexity to check the state.
2. Elimination variable:
When there is relationship between the varibles in the state, we can utilize the relationship to reduce dimension.