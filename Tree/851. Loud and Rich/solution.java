// OJ: https://leetcode.com/problems/loud-and-rich/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(n)
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        //n people
        //the richer relation is transisable
        //res[i] for all people richer than i, res[i] is the quietest.
        //for each person, find out all people that is richer than him
            //find out the people who is the quietest.
        //time: O(n^2)
        int n = quiet.length;
        List<Integer>[] richerThan = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            richerThan[i] = new ArrayList<>();
        }
        for (int i = 0; i < richer.length; i++) {
            richerThan[richer[i][1]].add(richer[i][0]);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int temp = quiet[i];
            res[i] = i;
            Queue<Integer> list = new LinkedList<>();
            Set<Integer> set = new HashSet<>();
            set.add(i);
            list.offer(i);
            while (!list.isEmpty()) {
                int cur = list.poll();
                if (quiet[cur] < temp) {
                    temp = quiet[cur];
                    res[i] = cur;
                }
                for (int next : richerThan[cur]) {
                    if (!set.contains(next)) {
                        list.offer(next);
                        set.add(next);
                    }
                }
            }
        }
        return res;
    }
}