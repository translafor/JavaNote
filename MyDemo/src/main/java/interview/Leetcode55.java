package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-30
 */
public class Leetcode55 {
    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public boolean canJump0(int[] nums) {
        // dp[i]代表，能否跳跃到i位置
        boolean dp[] = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = false;
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && nums[j] >= (i - j)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[nums.length - 1];
    }


    public boolean canJump(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            if (i <= max) {
                // 对比当前能跳跃到的距离和之前max的距离哪个大
                max = Math.max(max, i + nums[i]);
            }
        }
        return nums.length - 1 <= max;
    }
}
