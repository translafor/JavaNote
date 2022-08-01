package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-06
 */
public class Leetcode75 {
    public void sortColors0(int[] nums) {
        // 算法，核心就是，把小元素往前提；每个元素都与前面所有元素比较
        // 从第二个节点开始，排序
        for (int i = 1; i < nums.length; i++) {
            int temp = i;
            // 一直往前比较，如果当前节点比前节点小，则更换两个元素；直到比较到第一个元素
            for (int j = i - 1; j >= 0; j--) {
                if (nums[temp] <= nums[j]) {
                    int t = nums[temp];
                    nums[temp] = nums[j];
                    nums[j] = t;
                }
                temp = j;
            }
        }
    }

    public static void sortColors(int[] nums) {
        int first = 0;
        int last = nums.length - 1;
        int i = 0;
        while (i <= last) {
            if (i < first) {
                i = first;
                continue;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[first];
                nums[first] = temp;
                first++;
            } else if (nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[last];
                nums[last] = temp;
                last--;
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] n = {2, 0, 2, 1, 1, 0};
        sortColors(n);
    }
}
