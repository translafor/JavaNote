package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-17
 */
public class Leetcode31 {
    public void nextPermutation(int[] nums) {
        int left = -1;
        // 这里的逻辑很好理解，从右往左遍历，就是要找到一个开始递减的数left，这样将left与右边的某个数交换/后移时才能达到增到数列的效果
        // 2. 如果一直是递增的，代表这个数组序列已经是最大的了，则逆转数组即可
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                left = i - 1;
                break;
            }
        }
        if (left != -1) {
            // 找到第一个大于left位置的数，然后交换（这样就整体增大了序列）
            for (int i = nums.length - 1; i > 0; i--) {
                if (nums[i] > nums[left]) {
                    int temp = nums[i];
                    nums[i] = nums[left];
                    nums[left] = temp;
                    break;
                }
            }
            // 记住转换后，是高位的增大，低位就保持最小即可（即从小到大排序）
            reverse(nums, left + 1);
        } else {
            reverse(nums, 0);
        }
    }

    // 反转-因为这里都是有序列表 走反转即可(不用走排序)
    private void reverse(int[] nums, int i) {
        int right = nums.length - 1;
        while (i < right) {
            int temp = nums[i];
            nums[i] = nums[right];
            nums[right] = temp;
            i++;
            right--;
        }
    }
}
