188. Best Time to Buy and Sell Stock IV
class Solution {
    public int maxProfit(int k, int[] prices) {
        int l = prices.length;
        if (l <= 1) {
            return 0;
        }
        if (k >= l / 2) {
            return getMax(prices);
        }
        
        //on each day, we can start a transaction or not start, end a transaction or not.
        //we need to record the maxProfit until that day with different situations.
        //there are totoally 2k + 1 situations, the odd number represent is having stock, 1,3,5 even number is not 0,2,4,6...2k
        int[][] dp = new int[2 * k + 1][l];
        //initialize, in day 0, profit is 0
        //for each day, we update the state for each situation
        for (int i = 1; i < l; i++) {
            //update the odd number and even number seperately
            for (int j = 1; j <= 2 * k && j <= i; j += 2) {
                //when there is a transaction today, it will be the max of (the profit + today's profit)
                dp[j][i] = Math.max(dp[j][i - 1], dp[j - 1][i - 1]) + prices[i] - prices[i - 1];
            }
            for (int j = 2; j <= 2 * k && j <= i; j += 2) {
                //for the odd number, when there is no transaction, it is the maxinum of the profit of the day before, or just sell the stock yesterday
                dp[j][i] = Math.max(dp[j][i - 1], dp[j - 1][i - 1]);
            }
        }
        int res = 0;
        for (int j = 0; j <= 2 * k; j++) {
            // for (int i = 0; i < l; i++) {
            //     System.out.print(dp[j][i] + " ");
            // }
            if (dp[j][l - 1] > res) {
                res = dp[j][l - 1];
            }
            //System.out.println();
        }
        return res;
    }
    
    private int getMax(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}