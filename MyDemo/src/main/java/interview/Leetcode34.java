package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-19
 */
public class Leetcode34 {
    public static int[] searchRange0(int[] nums, int target) {
        int length = nums.length;
        if (length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            } else {
                return new int[]{-1, -1};
            }
        }
        int left = 0, right = length - 1;
        int l = -10, r = -1;
        // 利用
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                l = r = mid;
                while (l >= 0 && nums[l] == target) {
                    l--;
                }
                while (r < length && nums[r] == target) {
                    r++;
                }
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (l == -10) {
            return new int[]{-1, -1};
        }
        return new int[]{l + 1, r - 1};
    }


    public static int[] searchRange(int[] nums, int target) {
        int l = 0,r=nums.length-1;

        // right的初始值一定要nums.length，因为后面可能有整个数组都小于等于target的情况 此时right要取最后一个
        int left = -1,right = nums.length;
        while (l<=r){
            int mid = (l+r)/2;
            // 当满足>=时，证明这个可能就是第一个>=target的点，先记录下来，右指针前移动，继续比较
            if(nums[mid]>=target){
                r = mid-1;
                left = mid;
            }else {
                l = mid+1;
            }
        }

        left = -1;
        right = -1;
        while (l<=r){
            int mid = (l+r)/2;
            // 当满足>时，证明这个可能就是第一个>target的点，先记录下来，右指针前移动，继续比较
            if(nums[mid]>target){
                r = mid-1;
                right = mid;
            }else {
                l = mid+1;
            }
        }
        // 因为right是第一个大于target的点 所以取前一个元素
        right = right-1;
        if(left>-1 && right>=left && nums[left]==target && nums[right]==target){
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }
}
