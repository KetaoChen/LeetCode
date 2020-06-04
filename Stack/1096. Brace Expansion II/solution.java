// OJ: https://leetcode.com/problems/brace-expansion-ii/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> stack = new Stack<>();
        stack.push(new HashSet<>());
        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
                continue;
            }
            combine(stack, Arrays.asList(new String[]{sb.toString()}));
            sb = new StringBuilder();
            if (c == '{') {
                stack.push(new HashSet<>());
                stack.push(new HashSet<>());
            }
            else if (c == '}') {
                getRes(stack);
                List<String> list = new ArrayList<>(stack.pop());
                combine(stack, list);
            }
            else if (c == ',') {
                getRes(stack);
                stack.push(new HashSet<>());
            }
        }
        combine(stack, Arrays.asList(new String[]{sb.toString()}));
        getRes(stack);
        List<String> res = new ArrayList<>(stack.peek());
        Collections.sort(res);
        return res;
    }
    private void combine(Stack<Set<String>> stack, List<String> list) {
        if (list.size() == 1 && list.get(0).length() == 0) return;
        if (stack.peek().isEmpty()) {
            for (String s : list) {
                stack.peek().add(s);
            }
            return;
        }
        Set<String> combo = new HashSet<>();
        for (String s : stack.pop()) {
            for (String next : list) {
                combo.add(s + next);
            }
        }
        stack.push(combo);
    }
    private void getRes(Stack<Set<String>> stack) {
        if (stack.size() == 1) return;
        Set<String> cur = stack.pop();
        for (String s : cur) {
            stack.peek().add(s);
        }
    }
}

// OJ: https://leetcode.com/problems/brace-expansion-ii/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    int index = 0;
    public List<String> braceExpansionII(String expression) {
        Set<String> set = new HashSet<>();
        Set<String> temp = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (index < expression.length()) {
            char c = expression.charAt(index++);
            if (Character.isLetter(c)) {
                sb.append(c);
                continue;
            }
            temp = combo(temp, Arrays.asList(new String[]{sb.toString()}));
            sb = new StringBuilder();
            if (c == '{') {
                List<String> next = braceExpansionII(expression);
                temp = combo(temp, next);
            }
            else if (c == '}') {
                return getRes(set, temp);
            }
            else if (c == ',') {
                for (String s : temp) set.add(s);
                temp = new HashSet<>();
            }
        }
        if (sb.length() > 0) {
            temp = combo(temp, Arrays.asList(new String[]{sb.toString()}));
        }
        return getRes(set, temp);   
    }
    private Set<String> combo(Set<String> temp, List<String> next) {
        if (temp.isEmpty()) {
            for (String n : next) {
                temp.add(n);
            }
            return temp;
        }
        Set<String> combo = new HashSet<>();
        for (String s : temp) {
            for (String add : next) {
                combo.add(s + add);
            }
        }
        return combo;
    }
    private List<String> getRes(Set<String> set, Set<String> temp) {
        for (String s : temp) set.add(s);
        List<String> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }
}