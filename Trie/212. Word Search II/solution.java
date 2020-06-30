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