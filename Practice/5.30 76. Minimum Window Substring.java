76. Minimum Window Substring
class Solution {
    public String minWindow(String s, String t) {
        int ls = s.length();
        int lt = t.length();
        if (ls < lt) {
            System.out.println("res1");
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();
        int start = 0, end = 0;
        int[] res = new int[]{-1, ls};
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
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                    if (map.get(temp) > 0) {
                        counter++;
                    }
                }
                start++;
                if (end - start < res[1] - res[0]) {
                    res[0] = start - 1;
                    res[1] = end - 1;
                }
            }
        }
        if (res[0] == -1) {
            System.out.println("res2");
            return "";
        }
        return s.substring(res[0], res[1] + 1);
    }
}