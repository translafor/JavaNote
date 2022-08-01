package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-02
 */
public class Leetcode70 {
    public int climbStairs0(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs(int n) {
        int prePre = 1;
        int pre = 1;
        int r = 0;
        for (int i = 2; i <= n; i++) {
            r = prePre+pre;
            prePre = pre;
            pre = r;
        }
        return r;
    }
}
