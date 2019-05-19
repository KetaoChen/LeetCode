class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, Collections.reverseOrder());
        for (int i : stones) {
            pq.offer(i);
        }
        
        while (!pq.isEmpty()) {
            int first = pq.poll();
            if (pq.isEmpty()) {
                return first;
            }
            int second = pq.poll();
            if (first - second ！= 0) {
                pq.offer(first - second);
            }
        }
        return 0;
    }
}
