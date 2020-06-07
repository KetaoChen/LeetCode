// OJ: https://leetcode.com/problems/word-ladder-ii/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        int d = getShortest(beginWord, endWord, map, wordList);
        // System.out.println(map.size());
        if (d == -1) return res;     
        dfs(res, beginWord, endWord, map, new ArrayList<>());
        return res;
    }
    private void dfs(List<List<String>> res, String beginWord, String endWord, Map<String, List<String>> map, List<String> temp) {
        temp.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);
            return;
        }
        for (String cur : map.getOrDefault(beginWord, new ArrayList<>())) {
            dfs(res, cur, endWord, map, temp);            
        }
        temp.remove(temp.size() - 1);
    }
    private int getShortest(String beginWord, String endWord, Map<String, List<String>> map, List<String> wordList) {
        Set<String> all = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        q.offer(beginWord);
        set.add(beginWord);
        int d = 0;
        while (!q.isEmpty()) {
            int s = q.size();
            boolean end = false;
            Set<String> n = new HashSet<>();
            for (int i = 0; i < s; i++) {
                String cur = q.poll();
                map.put(cur, new ArrayList<>());
                List<String> list = map.get(cur);
                end |= cur.equals(endWord);
                for (int k = 0; k < cur.length(); k++) {
                    int old = cur.charAt(k);
                    StringBuilder sb = new StringBuilder(cur);
                    for (int j = 0; j < 26; j++) {
                        if (j == old) continue;
                        sb.setCharAt(k, (char) ('a' + j));
                        String add = sb.toString();
                        if (all.contains(add) && !set.contains(add)) {
                            if (!n.contains(add)) {
                                n.add(add);
                                q.offer(add);
                            }
                            list.add(add);
                        }
                    }
                }
            }
            for (String str : n) set.add(str);
            if (end) return d;
            d++;
        }
        return -1;
    }
    private boolean check(String s, String t) {
        boolean diff = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (diff) return false;
                diff = true;
            }
        }
        return true;
    }
}