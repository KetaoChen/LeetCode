# [212. Word Search II (Hard)](https://leetcode.com/problems/word-search-ii/)

**Trick**:
1. Build the trie tree using the word list.
2. Avoid count the result repeatedly by labeling the isWord to false after we add the valid word each time. e.g. board = [a, a], list = [a]. 

<p>Given a 2D board and a list of words from the dictionary, find all words in the board.</p>

<p>Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.</p>

<p>&nbsp;</p>

<p><strong>Example:</strong></p>

<pre><strong>Input:</strong> 
<b>board </b>= [
  ['<span style="color:#d70">o</span>','<span style="color:#d70">a</span>','a','n'],
  ['e','<span style="color:#d30">t</span>','<span style="color:#d00">a</span>','<span style="color:#d00">e</span>'],
  ['i','<span style="color:#d70">h</span>','k','r'],
  ['i','f','l','v']
]
<b>words</b> = <code>["oath","pea","eat","rain"]</code>

<strong>Output:&nbsp;</strong><code>["eat","oath"]</code>
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li>All inputs are consist of lowercase letters <code>a-z</code>.</li>
	<li>The values of&nbsp;<code>words</code> are distinct.</li>
</ol>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Microsoft](https://leetcode.com/company/microsoft), [Apple](https://leetcode.com/company/apple), [Snapchat](https://leetcode.com/company/snapchat), [Google](https://leetcode.com/company/google), [Facebook](https://leetcode.com/company/facebook), [Salesforce](https://leetcode.com/company/salesforce)

**Related Topics**:  
[Backtracking](https://leetcode.com/tag/backtracking/), [Trie](https://leetcode.com/tag/trie/)

**Similar Questions**:
* [Word Search (Medium)](https://leetcode.com/problems/word-search/)
* [Unique Paths III (Hard)](https://leetcode.com/problems/unique-paths-iii/)

## Solution 

```java
// OJ: https://leetcode.com/problems/word-search-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(list.size() * len(word))
// Space: O(TotalLen(words))
class Solution {
    class Node {
        Node[] children;
        boolean isWord;
        public Node() {
            children = new Node[26];
            isWord = false;
        }
    }
    public void add(Node cur, String s) {
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            if (cur.children[val] == null) {
                cur.children[val] = new Node();
            }
            cur = cur.children[val];
        }
        cur.isWord = true;
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board.length == 0 || board[0].length == 0) return res;
        int row= board.length, col = board[0].length;
        Node root = new Node();
        for (String word : words) {
            add(root, word);
        }
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs(res, board, root, i, j, visited, new StringBuilder());
            }
        }
        return res;
    }
    private void dfs(List<String> res, char[][] board, Node cur, int i, int j, boolean[][] visited, StringBuilder sb) {
        int row= board.length, col = board[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j]) {
            return;
        }
        int val = board[i][j] - 'a';
        if (cur.children[val] == null) {
            return;
        }
        visited[i][j] = true;
        sb.append(board[i][j]);
        if (cur.children[val].isWord) {
            res.add(sb.toString());
            cur.children[val].isWord = false;
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int d = 0; d < 4; d++) {
            dfs(res, board, cur.children[val], i + dx[d], j + dy[d], visited, sb);    
        }
        visited[i][j] = false;
        sb.deleteCharAt(sb.length() - 1);
    }
}
```