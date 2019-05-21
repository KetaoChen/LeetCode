class Solution {
	//actually, i did not find the pattern of this problem and got two TLE.
    public int flipLights(int n, int m) {
        HashSet<String> res = new HashSet<>();
        HashMap<Integer, HashSet<String>> map = new HashMap<>();
        //according to the operation, we can find that the 1st bulb and the 7th bulb is the same situation.
        //the same, for the operation, same operation doing 2 times can be cancelled.
        int[] bulbs = n <= 6 ? new int[n + 1] : new int[7];
        //represent 
        for (int i = 1; i < bulbs.length; i++) {
            bulbs[i] = 1;
        }
        if (m <= 4) {
            helper(res, map, bulbs, m);
        }
        else {
            helper(res, map, bulbs, 4);
        }
        return res.size();
    }
    
    private void helper(HashSet<String> res, HashMap<Integer, HashSet<String>> map, int[] bulbs, int m) {
        String state = print(bulbs);
        //memo
        if (map.containsKey(m) && map.get(m).contains(state)) {
            return;
        }
        else {
            HashSet<String> set = new HashSet<>();
            set.add(state);
            map.put(m, set);
        }
        //if to the end
        if (m == 0) {
            res.add(state);
            return;
        }
        //start different operation
        int[][] temp = new int[4][bulbs.length];
        for (int i = 1; i < bulbs.length; i++) {
            temp[0][i] = 1 - bulbs[i];
            temp[1][i] = i % 2 == 0 ? temp[1][i] = 1 - bulbs[i] : bulbs[i];
            temp[2][i] = i % 2 == 1 ? temp[2][i] = 1 - bulbs[i] : bulbs[i];
            temp[3][i] = i % 3 == 1 ? temp[3][i] = 1 - bulbs[i] : bulbs[i];
        }
        for (int k = 0; k <= 3; k++) {
            helper(res, map, temp[k], m - 1);
        }
    }
    
    private String print(int[] bulbs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < bulbs.length; i++) {
            sb.append(bulbs[i]);
        }
        return sb.toString();
    }
}