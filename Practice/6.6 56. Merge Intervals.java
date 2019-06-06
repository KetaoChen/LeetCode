56. Merge Intervals
class Solution {
    
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return intervals;
        }
        Comparator arrayComparator =  new Comparator<int[]>() {
           @Override
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] != arr2[0]) {
                    return arr1[0] - arr2[0];
                }
                return arr1[1] - arr2[1];
            }
        };
        PriorityQueue<int[]> pq = new PriorityQueue<>(10, arrayComparator);
        for (int[] interval : intervals) {
            pq.offer(interval);
        }
        List<int[]> list = new ArrayList<>();
        int[] temp = pq.poll();
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] <= temp[1]) {
                if (cur[1] > temp[1]) {
                    temp[1] = cur[1];
                }
            }
            else {
                list.add(temp);
                temp = cur;
            }
        }
        list.add(temp);
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        
        return res;
        
    }
}