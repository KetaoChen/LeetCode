// OJ: https://leetcode.com/problems/24-game/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    final double delta = 0.0001;
    boolean res;
    int count;
    public boolean judgePoint24(int[] nums) {
        count = 0;
        res = false;
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        helper(list);
        return res;
    }    
    private void helper(List<Double> list) {
        if (res) return;
        if (list.size() == 1) {
            if (Math.abs(list.get(0) - 24) < delta) {
                res = true;
            }
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                    if (k == i || k == j) continue;
                    next.add(list.get(k));
                }
                double a = list.get(i), b = list.get(j);
                List<Double> add = new ArrayList<>();
                add.addAll(Arrays.asList(new Double[]{a + b, a - b, b - a, a * b}));
                if (b > delta) add.add(a / b);
                if (a > delta) add.add(b / a);
                for (double num : add) {
                    next.add(num);
                    helper(next);
                    next.remove(next.size() - 1);
                }
            }
        }
    }
}