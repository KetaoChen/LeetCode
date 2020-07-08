// OJ: https://leetcode.com/problems/word-break/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(dictionary + l)
class Solution {
    class Node {
        Node[] children;
        boolean isStart;
        public Node() {
            children = new Node[26];
        }
    }
    private void add(Node root, String s) {
        Node cur = root;
        for (int i = s.length() - 1; i >= 0; i--) {
            int val = s.charAt(i) - 'a';
            if (cur.children[val] == null) {
                cur.children[val] = new Node();
            }
            cur = cur.children[val];
        }
        cur.isStart = true;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Node root = new Node();
        for (String word : wordDict) {
            add(root, word);
        }
        int l = s.length();
        boolean[] dp = new boolean[l + 1];
        dp[0] = true;
        for (int i = 1; i <= l; i++) {
            Node cur = root;
            for (int j = i - 1; j >= 0; j--) {
                int val = s.charAt(j) - 'a';
                if (cur.children[val] == null) {
                    break;
                }
                cur = cur.children[val];
                if (cur.isStart) {
                    dp[i] |= dp[j];
                }
                if (dp[i]) break;
            }
        }
        return dp[l];
    }
}

// OJ: https://leetcode.com/problems/word-break/
// Author: https://leetcode.com/charlesna/
// Time: O(n^3) (i*j*substring)
// Space: O(n)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int l = s.length();
        boolean[] dp = new boolean[l + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        for (int i = 1; i <= l; i++) {
            for (int j = i; j >= 1; j--) {
                if (set.contains(s.substring(j- 1, i))) {
                    dp[i] |= dp[j - 1];
                }
            }
        }
        return dp[l];
    }
}