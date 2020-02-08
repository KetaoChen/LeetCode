// OJ: https://leetcode.com/problems/iterator-for-combination/
// Author: https://leetcode.com/charlesna/
// Time: O(C(n, l))
// Space: O(l)
class CombinationIterator {
    int l; // record the target length
    char[] ch;  // record the characters in the sorted string
    int[] index; // map the index of each char
    StringBuilder sb; // current combo
    String end; // endpoint
    boolean isFirst; // start point
    public CombinationIterator(String characters, int combinationLength) {
        l = combinationLength;
        ch = characters.toCharArray(); 
        index = new int[26];
        for (int i = 0; i < ch.length; i++) {
            index[ch[i] - 'a'] = i;
        }
        sb = new StringBuilder(characters.substring(0, l));
        end = characters.substring(characters.length() - l);
        isFirst = true;
    }
    public String next() {
        if (isFirst) {
            isFirst = false;
            return sb.toString();
        }
        helper(sb, ch, index);
        return sb.toString();
    }
    private void helper(StringBuilder sb, char[] ch, int[] index) {
        //starting from the last index, find the one that we can add one.
        int pointer = sb.length() - 1;
        while (ch[ch.length - (l - pointer)] == sb.charAt(pointer)) {
            sb.deleteCharAt(pointer--);
        }
        //add that char to next char
        sb.setCharAt(pointer, ch[index[sb.charAt(pointer) - 'a'] + 1]);
        //fill the rest of string only one char larger than before
        while (sb.length() < l) {
            sb.append(ch[index[sb.charAt(pointer++) - 'a'] + 1]);
        }
    }
    public boolean hasNext() {
        return !sb.toString().equals(end);
    }
}
/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */