package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-08-01
 */
public class Leetcode152 {
    /**
     * 动态规划;
     * 难点在于对于0的处理，
     * 1。 第一反应可以拆成子数组去做；会增加代码难度
     * 2。 可以改变下参考下l53的题目；表达式改为 dp[i] = Max(dp[i-1]*n[i],n[i])；这样当遇到0之后，就会自动隔断前面的数，从0开始计算
     *     然后在dp[i]数组中取最大值即可
     * @param nums
     * @return
     */
    public int maxProduct0(int[] nums) {
        // x:代表从下标。开始；y：代表长度为y
        // 看上面的解释，也就不需要二维了
//        int[][] dp = new int[nums.length][nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            dp[i][1] = nums[i];
//        }
        // 用来存储当下标为i时的最大值
        int maxV[] = new int[nums.length];
        // 因为乘积可能存在负负德正的情况，存储下最小值
        int minV[] = new int[nums.length];
        maxV[0] = nums[0];
        minV[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 两个重要的性质：1. Math.max(nums[i],nums[i]*maxV[i-1])这个是为了去掉0；也就是看看是否要从i位置重新开始计算
            //              2. 同时存储最大最小值；是因为可能有负数，负负得正，会得到更大值
            // 最大值的计算：主要就是最大值*当前元素，最小值(负数)*当前元素；取最大值
            maxV[i] = Math.max(Math.max(nums[i],nums[i]*maxV[i-1]),nums[i]*minV[i-1]);
            // 最小值同样道理;当前可能为负数，负负得正，就娶不到这个值了；需要加上max[]的乘机比较
            minV[i] = Math.min(Math.min(nums[i],nums[i]*minV[i-1]),nums[i]*maxV[i-1]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max,maxV[i]);
        }
        return max;
    }

    public int maxProduct(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            // 如果当前数为负数；则交换正负数
            if(nums[i]<0){
                int temp = min;
                min = max;
                max = temp;
            }
            // 然后运用规则，处理0；并存下到当前位置的最大最小值
            max = Math.max(max*nums[i],nums[i]);
            min = Math.min(min*nums[i],nums[i]);

            // 计算最大值
            res = Math.max(res,max);
        }
        return res;
    }
}
