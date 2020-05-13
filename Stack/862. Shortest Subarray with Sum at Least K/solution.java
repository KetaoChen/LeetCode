// OJ: https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int l = A.length;
        int[] sum = new int[l + 1];
        for (int i = 1; i <= l; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
        }
        int res = l + 1;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i <= l; i++) {
            while (!dq.isEmpty() && sum[i] < sum[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            while (!dq.isEmpty() && sum[i] - sum[dq.peekFirst()] >= K) {
                res = Math.min(res, i - dq.peekFirst());
                dq.pollFirst();
            }
        }
        return res == l + 1 ? -1 : res;
    }
}