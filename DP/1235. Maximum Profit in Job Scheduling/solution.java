// OJ: https://leetcode.com/problems/maximum-profit-in-job-scheduling/
// Author: https://leetcode.com/charlesna/
// Time: O(nlogn)
// Space: O(n)
class Solution {
    class Job {
        int start, end, profit;
        public Job(int i, int j, int k) {
            start = i;
            end = j;
            profit = k;
        }
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int l = profit.length;
        if (l == 0) return 0;
        Job[] jobs = new Job[l];
        for (int i = 0; i < l; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> a.end == b.end ? a.start - b.start : a.end - b.end);
        int[] dp = new int[l + 1];
        for (int i = 1; i <= l; i++) {
            int index = find(jobs, i);
            dp[i] = Math.max(dp[i - 1], dp[index] + jobs[i - 1].profit);
        }
        return dp[l];
    }
    // find the last job which ends before the current start.
    private int find(Job[] jobs, int i) {
        int start = jobs[i - 1].start;
        int left = 1, right = i - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (jobs[mid - 1].end <= start) {
                left = mid;
            }
            else {
                right = mid - 1;
            }
        }
        return jobs[left - 1].end <= start ? left : left - 1;
    }
}