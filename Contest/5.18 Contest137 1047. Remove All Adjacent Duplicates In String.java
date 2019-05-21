class Solution {
    public String removeDuplicates(String S) {
		//because guaranteed the unique answer, we can use greedy to solve this problem!
        boolean canRemove = true;
        while (canRemove) {
            StringBuilder sb = new StringBuilder();
            canRemove =false;
            for (int i = 0; i < S.length(); i++) {
                if (i + 1 < S.length() && S.charAt(i) == S.charAt(i + 1)) {
                    i++;
                    canRemove = true;
                }
                else {
                    sb.append(S.charAt(i));
                }
            }
            S = sb.toString();
        }
        return S;
    }
}