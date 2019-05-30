438. Find All Anagrams in a String
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //how to match the s and p, slide windows. 
        //brute force is check each substring in s and p, match or not. 
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int ls = s.length();
        int lp = p.length();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();
        int start = 0, end = 0;
        
        while (end < ls) {
            char cur = s.charAt(end);
            if (map.containsKey(cur)) {
                map.put(cur, map.get(cur) - 1);
                if (map.get(cur) == 0) {
                    counter--;
                }
            }
            end++;
            while (counter == 0) {
                char temp = s.charAt(start);
                if (end - start == lp) {
                    res.add(start);
                }
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                    if (map.get(temp) > 0) {
                        counter++;
                    }
                }
                start++;
            }
        }
        return res;
    }
}
// This is my first attempt, which use hashmap to keep track of every move of the pointer, the logic is clear, but also need improvement.
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //how to match the s and p, slide windows. 
        //brute force is check each substring in s and p, match or not. 
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int ls = s.length();
        int lp = p.length();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < lp; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) - 1);
            if (map.get(chars[i]) == 0) {
                map.remove(chars[i]);
            }
        }
        if (map.isEmpty()) {
            res.add(0);
        }
        int i = 1, j = lp;
        
        while (j < ls) {
            map.put(chars[i - 1], map.getOrDefault(chars[i - 1], 0) + 1);
            map.put(chars[j], map.getOrDefault(chars[j], 0) - 1);
            if (map.getOrDefault(chars[j], 0) == 0) {
                map.remove(chars[j]);
            }
            if (map.getOrDefault(chars[i - 1], 0) == 0) {
                map.remove(chars[i - 1]);
            }
            if (map.isEmpty()) {
                res.add(i);
            }
            i++;
            j++;
        }
        return res;
    }
}