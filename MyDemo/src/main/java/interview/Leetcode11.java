package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-10
 */
public class Leetcode11 {
    // 暴力
    @Deprecated
    public int maxArea0(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }

    // 双指针
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (right > left) {
            max = Math.max(max, (right - left) * Math.min(height[right], height[left]));
            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
