package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-26
 */
public class Leetcode53 {
    public int maxSubArray0(int[] nums) {
        int max = Integer.MIN_VALUE;
        int temp = 0;
        // 思路很简单，以[-2,1,-3,4,-1,2,1,-5,4]为例
        // 直接遍历这个数组，既然要求最大的连续合，则从左到右遍历时，只保留和>0的连续字符串
        // 比如，当遍历第一个数，-2小于0，那么直接抛弃-2；从下一个数开始算左边界
        //      当遍历第二个数，1>0，则保留1，左边界就是1开始
        //      当遍历第三个数，1+(-3)<0,则抛弃1和-3这个连续串，从下一个数开始...
        // 因为题目只需要求最大值，则每个取下最大值即可；如果题目需要返回这个串，则计算时，保留下左右边界即可
        for (int i = 0; i < nums.length; i++) {
            temp += nums[i];
            max = Math.max(max, temp);
            if (temp < 0) {
                temp = 0;
            }
        }
        return max;
    }


    public class Status {
        public int lSum, rSum, mSum, iSum;
        public Status(int iSum, int lSum, int rSum, int mSum) {
            // lSum 表示 [l,r][l,r] 内以 ll 为左端点的最大子段和
            this.lSum = lSum;
            // rSum 表示 [l,r][l,r] 内以 rr 为右端点的最大子段和
            this.rSum = rSum;
            // mSum 表示 [l,r][l,r] 内的最大子段和
            this.mSum = mSum;
            // iSum 表示 [l,r][l,r] 的区间和
            this.iSum = iSum;
        }
    }

    public int maxSubArray1(int[] nums) {
        return getStatus(nums, 0, nums.length - 1).mSum;
    }

    private Status getStatus(int[] nums, int l, int r) {
        // 递归最后往回走的临界点
        if (l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        }
        int m = (l + r) / 2;
        Status lStatus = getStatus(nums, l, m);
        Status rStatus = getStatus(nums, m + 1, r);
        return deduce(lStatus, rStatus);
    }

    /**
     * 由左区间(l,m) 和 右区间 (m+1,r);计算当前区间(l,r)的值
     */
    private Status deduce(Status lStatus, Status rStatus) {
        // 全数列的大小，等于左区间大小+右区间大小
        int iSUm = lStatus.iSum + rStatus.iSum;
        // 以左边界开始的最大值，等于左区间的lSUm（这种情况下相当于没有越过中间点）以及 左区间的iSUm+右区间.lSum（这种情况相当于越过了中间线）
        int lSum = Math.max(lStatus.lSum, lStatus.iSum + rStatus.lSum);
        // 和lSUm一样的道理
        int rSum = Math.max(rStatus.rSum, rStatus.iSum + lStatus.rSum);
        // 重点：全区间的最大值：有两种情况：1.只分布在一个区间内，则就是左区间的最大值和右区间的最大值中取最大的
        // 2. 分布在两个区间，就等于左区间的rSum+右区间的lSUm;   然后取这三个的最大值即可
        int mSum = Math.max(Math.max(lStatus.mSum, rStatus.mSum), lStatus.rSum + rStatus.lSum);
        return new Status(iSUm, lSum, rSum, mSum);
    }

    public static int maxSubArray(int[] nums) {
        // dp[i]，代表以i位置的元素，为结尾元素的最大连续字符串的值；所以取这个数组中的最大值即可
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            // 当求dp[i]时，有两种情况，一个是和dp[i]组合在一起（即dp[i-1]是正值）；另一个是dp[i]单独成立(即dp[i-1]是负值)
            // 和第一种方法是一个思维，只是这里更加明确思路是dp
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1};
        System.out.println(maxSubArray(nums));
    }

}
