package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-01
 */
public class Leetcode64 {

    /**
     * 递归--超时了。。
     *
     * @param grid
     * @return
     */
    public int minPathSum0(int[][] grid) {
        return doit(grid, 0, 0, 0);
    }

    private int doit(int[][] grid, int length, int height, int sum) {
        if (length == grid[0].length - 1 && height == grid.length - 1) {
            return sum + grid[height][length];
        }
        if (length == grid[0].length - 1) {
            return doit(grid, length, height + 1, sum + grid[height][length]);
        }
        if (height == grid.length - 1) {
            return doit(grid, length + 1, height, sum + grid[height][length]);
        }
        return Math.min(doit(grid, length, height + 1, sum + grid[height][length]), doit(grid, length + 1, height, sum + grid[height][length]));
    }

    /**
     * 动态规划
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        // dp[i][j]代表从(0,0)到(i,j)位置最小的路径和
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }
                // 如果高==0，则在底层，只可能是从左边往右边走一格（因为不能从下往上走）
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                    continue;
                }
                // 如果宽==0，则在最左层，只可能是从上往下走一格（因为不能从右往左走）
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                    continue;
                }
                // 否则有两种可能
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
