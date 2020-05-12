// OJ: https://leetcode.com/problems/partition-labels/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) {
            int c = S.charAt(i) - 'a';
            last[c] = i;
        }
        List<Integer> res = new ArrayList<>();
        int end = 0, start = -1;
        for (int i = 0; i < S.length(); i++) {
            int c = S.charAt(i) - 'a';
            end = Math.max(end, last[c]);
            if (i == end) {
                res.add(i - start);
                start = i;
            }
        }
        return res;
    }
}

// OJ: https://leetcode.com/problems/partition-labels/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public List<Integer> partitionLabels(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        int last = -1;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i); 
            set.add(c);
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) set.remove(c);
            if (set.size() == 0) {
                res.add(i - last);
                last = i;
            }
        }
        return res;
    }
}