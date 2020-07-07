// OJ: https://leetcode.com/problems/minimum-possible-integer-after-at-most-k-adjacent-swaps-on-digits/
// Author: https://leetcode.com/charlesna/
// Time: O(nlogn)
// Space: O(n)
class Solution {
    int[] sum;
    public String minInteger(String num, int k) {
        // get the indexes for all numbers.
        Queue<Integer>[] qs = new Queue[10];
        for (int i = 0; i < 10; i++) {
            qs[i] = new LinkedList<>();
        }
        int l = num.length();
        sum = new int[l + 1];
        for (int i = 0; i < l; i++) {
            int val = num.charAt(i) - '0';
            qs[val].add(i);
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < l) {
            for (int i = 0; i < 10; i++) {
                // if we can move current number to the top. The number of move is currentIndex - 
                if (!qs[i].isEmpty() && qs[i].peek() - getSum(qs[i].peek()) <= k) {
                    int cur = qs[i].poll();
                    k -= cur - getSum(cur);
                    sb.append(i);
                    update(cur);
                    break;
                }
            }
        }
        return sb.toString();
    }
    private void update(int index) {
        for (int i = index + 1; i < sum.length; i += lowbit(i)) {
            sum[i]++;
        }
    }
    private int getSum(int index) {
        int res = 0;
        for (int i = index + 1; i > 0; i -= lowbit(i)) {
            res += sum[i];
        }
        return res;
    }
    private int lowbit(int i) {
        return i & -i;
    }
}