package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-07-05
 */
public class Leetcode121 {
    /**
     * 法一：暴力法，遍历所有买卖的情况，取最大值
     * 超吃时间限制！！
     * @param prices
     * @return
     */
    public int maxProfit0(int[] prices) {
        int max = 0;
        int length = prices.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                max = Math.max(prices[j]-prices[i],max);
            }
        }
        return max;
    }

    /**
     * 法二：利用历史最低价格
     * 假如计划在第 i 天卖出股票，那么最大利润的差值一定是在[0, i-1] 之间选最低点买入；所以遍历数组，依次求每个卖出时机的的最大差值，再从中取最大值。
     * 所以我们只需要记录一个历史最低价格即可
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int least = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max,prices[i]-least);
            least = Math.min(least,prices[i]);
        }
        return max;
    }


}
