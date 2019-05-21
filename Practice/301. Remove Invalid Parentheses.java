class Solution {
	//Pair is used to record the min number of left parentheses and right parenthese
    class Pair {
        int left;
        int right;
        public Pair(int i, int j) {
            left = i;
            right = j;
        }
    }
    
    public List<String> removeInvalidParentheses(String s) {
        //memorization the String which has been 
        HashSet<String> set = new HashSet<>();
        Pair p = check(s);
        List<String> res = new ArrayList<>();
        helper(res, s, p, set);
        return res;
    }
    
    private void helper(List<String> res, String s, Pair before, HashSet<String> set) {
        //if this one has been done before, return
        if (set.contains(s)) {
            return;
        }
        Pair p = check(s);
        //if this is not the mininum remove, return
        if (p.left > before.left || p.right > before.right) {
            return;
        }
        //if this is one of the answer, add the res and return
        if (p.left == 0 && p.right == 0 && !res.contains(s)) {
            res.add(s);
            return;
        }
        //keep dfs
        if (p.left > 0 || p.right > 0) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(' && p.left > 0) {
                    helper(res, s.substring(0, i) + s.substring(i + 1), p, set);
                }
                if (s.charAt(i) == ')' && p.right > 0) {
                    helper(res, s.substring(0, i) + s.substring(i + 1), p, set);
                }
                set.add(s.substring(0, i) + s.substring(i + 1));
            }
        }
    }
    
    private Pair check(String s) {
        Stack<Character> stack = new Stack<>();
        //val is to record the removed number of '(' and ')', each '(' --> val++; ')' --> val--;
        int removedLeft = 0;
        int removedRight = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
                continue;
            }
            if (c == ')') {
                if (stack.isEmpty()) {
                    removedRight++;
                }
                else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            removedLeft++;
            stack.pop();
        }
        return new Pair(removedLeft, removedRight);
    }
}