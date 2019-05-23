//139. Word Break
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        //after word in dict is a substring in s, then need to check whether the string left can be broken with dict.
        //use map memo whether the string has been checked before
        HashMap<String, Boolean> map = new HashMap<>();
        return helper(s, wordDict, map);
    }
    
    private boolean helper(String s, List<String> wordDict, HashMap<String, Boolean> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        if (s.length() == 0) {
            return true;
        }
        boolean res = false;
        for (String word : wordDict) {
            int index = s.indexOf(word);
            if (index != -1) {
                res |= helper(s.substring(0, index), wordDict, map) && helper(s.substring(index + word.length()), wordDict, map);
                if (res) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }
    
}