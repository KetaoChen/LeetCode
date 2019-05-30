211. Add and Search Word - Data structure design
class WordDictionary {
    
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }
    /** Initialize your data structure here. */
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }
    
    private boolean searchHelper(String word, TrieNode cur, int index) {
        for (int i = index; i < word.length(); i++) {
            //is .
            if (word.charAt(i) == '.') {
                for (int j = 0; j <= 25; j++) {
                    if (cur.children[j] != null) {
                        if (searchHelper(word, cur.children[j], i + 1)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            else {
                int k = word.charAt(i) - 'a';
                if (cur.children[k] == null) {
                    return false;
                }
                cur = cur.children[k];
            }
        }
        return cur.isWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */