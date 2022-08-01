package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-23
 */
public class Leetcode46 {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        doit(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private static void doit(int[] nums, int i, ArrayList<Integer> integers, List<List<Integer>> res) {
        // 我这里是判断n-1，代表会显得复杂些，但是可以少走一层递归；如果判断n代码会清晰一些，但是逻辑都是一样的
        for (int j = 0; j < nums.length; j++) {
            // 遍历时，如果已经到塞最后一个元素了，就准备找最后一个元素，并且找到后可以直接break
            if (i == nums.length - 1) {
                if (!integers.contains(nums[j])) {
                    integers.add(nums[j]);
                    res.add(new ArrayList<>(integers));
                    integers.remove(integers.size() - 1);
                    break;
                }
            } else {
                // 如果不是最后一个元素，就找哪些元素是可以递归的，则递归，然后删除元素
                if (!integers.contains(nums[j])) {
                    integers.add(nums[j]);
                    doit(nums, i + 1, integers, res);
                    integers.remove(integers.size() - 1);
                }
            }
        }
    }
}
