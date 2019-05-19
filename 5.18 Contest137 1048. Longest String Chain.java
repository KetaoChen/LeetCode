class Solution {
    public int longestStrChain(String[] words) {
		//similar to longest increasing subsequence
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        
        int l = words.length;
        
		//dp[i] represent the longest string when the last string is words[i]
        int[] dp = new int[l];
        dp[0] = 1;
        for (int i = 1; i < l; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (isPre(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
		
		//!!because of the definition of dp, we need to find out which string has max number when it is the last string.
        int res = 1;
        for (int i = 0; i < l; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    
    private boolean isPre(String s1, String s2) {
        if (s2.length() - s1.length() != 1) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            else {
                int index = s2.indexOf(s1.substring(i, s1.length()), i);
                if (index == -1) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }
}