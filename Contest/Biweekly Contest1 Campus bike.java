class Solution {
    int res;
    public int assignBikes(int[][] workers, int[][] bikes) {
        //after assign a bike to a worker, recursively calculate the distance
        HashMap<String, Integer> memo = new HashMap<>();
        int[][] dist = new int[workers.length][bikes.length];
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                dist[i][j] = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
            }
        }
        boolean[] bC = new boolean[bikes.length];
        return helper(0, dist, bC, memo);
    }
    
    private int helper(int cur, int[][] dist, boolean[] bikeCheck, HashMap<String, Integer> memo) {
        String state = print(bikeCheck);
        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        if (cur == dist.length) {
            memo.put(state, 0);
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < bikeCheck.length; j++) {
            if (bikeCheck[j]) {
                continue;
            }
            bikeCheck[j] = true;
            int dis = dist[cur][j];
            res = Math.min(res, dis + helper(cur + 1, dist, bikeCheck, memo));
            bikeCheck[j] = false;
        }
        memo.put(state, res);
        return res;
    }
    
    private String print(boolean[] c2) {
        StringBuilder sb =  new StringBuilder();
        for (int i = 0; i < c2.length; i++) {
            if (!c2[i]) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}