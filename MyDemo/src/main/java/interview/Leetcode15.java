package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-11
 */
public class Leetcode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 1。 因为要保证每个结果集里面的元素不完全重复 先排序后在循环中比较与上一次是否相等，会比较简单
        // 2。 排序之后还有一个特性，从小到大排了以后，当我们确定a的值以后，将b从小值往大值遍历时，如果每个b都有对应的c满足题目条件，那么随着b的增加，c
        //     应该在一直减小；所以对于b的从小到大循环，c只需一个指针从大到小移动；这就是常用的双指针
        // 当然，需要注意的时，b不一定有唯一的c对应，所以我们的判断条件时b+c>a,如果不满足，就将b指针+1，这样才可能继续满足这个条件
        Arrays.sort(nums);
        int len = nums.length;
        for (int first = 0; first < len; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                // 首先保证不会完全重复
                continue;
            }
            // 确定a，以后 可以确定目标值，即b+c
            int target = -nums[first];
            // c的起始遍历节点
            int third = len - 1;
            for (int second = first + 1; second < len; second++) {
                // 确定b以后 可以开始判断前面提到的逻辑
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 当还是满足b+c>target时，需要减小c,直到不满足
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                // 此时两种情况:1. 知道b c重合都还是大于，就没有符合的条件了（此时已经最小了），可以跳出循环了
                if (second == third) {
                    break;
                }
                // 2. nums[second]+nums[third]==target,这个就是我们需要找的答案
                if (nums[second] + nums[third] == target) {
                    List<Integer> resultItem = new ArrayList<>(3);
                    resultItem.add(nums[first]);
                    resultItem.add(nums[second]);
                    resultItem.add(nums[third]);
                    result.add(resultItem);
                }
                // 第三种情况，nums[second]+nums[third]<target，这种情况就让c变大
            }
        }
        return result;
    }
}
