// OJ: https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public int findTheLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] v = new char[]{'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < 5; i++) {
            map.put(v[i], i);
        }
        int res = 0;
        int state = 0;
        Map<Integer, Integer> store = new HashMap<>();
        store.put(0, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                state ^= (1 << map.get(c));
            }
            if (store.containsKey(state)) {
                res = Math.max(res, i - store.get(state));
            }
            else {
                store.put(state, i);
            }
        }
        return res;
    }
}