package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-18
 */
public class Leetcode33 {
    public static int search1(int[] nums, int target) {
        // 特性：旋转后，分为两个递增有序集合，则可以，从左到右递增，从右到左递减来遍历处理
        int length = nums.length - 1;
        if (target < nums[0] && target > nums[length]) {
            return -1;
        }
        int before = Integer.MIN_VALUE;
        // 从左到右一定是部分递增的，1. 如果当前元素都大于target，后面肯定也大于；直接break 2.如果发现递减了，也break，让下一个循环去做
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
            if (target < nums[i]) {
                break;
            }
            if (nums[i] < before) {
                break;
            }
            before = nums[i];
        }
        before = Integer.MAX_VALUE;
        // 从左到右一定是部分递减的，1. 如果当前元素都小于target，后面肯定也小于；直接break 2.如果发现递增了，也break，让上一个循环去做
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == target) {
                return i;
            }
            if (target > nums[i]) {
                break;
            }
            if (nums[i] > before) {
                break;
            }
            before = nums[i];
        }
        return -1;
    }


    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;


        // 首先，当旋转了一个递增数组一部分后，得到的数组一定是两个递增的序列，并且第一个序列的所有元素都大于第二个序列
        // 所以当取一个mid节点时，如果nums[0]<nums[mid]，证明mid肯定是落到了第一个序列
        // 反之落到了第二个
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 落到了第一个序列
            if (nums[l] <= nums[mid]) {
                // 并且处于l-mid的范围中
                if (nums[l] <= target && target <= nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 落到了第二个序列,并且处于mid-r的区间
                if (nums[mid] <= target && nums[r] >= target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            // 如果一轮没有找到复合的条件 则进入下一轮二分查找
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int t = 4;
        System.out.println(search(nums, t));
    }
}
