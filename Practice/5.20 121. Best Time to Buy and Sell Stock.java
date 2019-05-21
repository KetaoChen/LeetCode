class Solution {
    // public int maxProfit(int[] prices) {
    //     //update the profit during one buy-sell process. if profit become negative, change profit to 0.
    //     int res = 0;
    //     int profit = 0;
    //     for (int i = 1; i < prices.length; i++) {
    //         profit = Math.max(0, profit + prices[i] - prices[i - 1]);
    //         res = Math.max(res, profit);
    //     }
    //     return res;
    // }
    
    public int maxProfit(int[] prices) {
        //find out each potential transaction which have profit.
        if (prices.length == 0) {
            return 0;
        }
        int curMin = prices[0];
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > curMin) {
                res = Math.max(res, prices[i] - curMin);
            }
            else {
                curMin = prices[i];
            }
        }
        return res;
    }
}