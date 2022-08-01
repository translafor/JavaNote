package interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-07-06
 */
public class Leetcode128 {
    /**
     * 法一：先排序（不满足题目O(n)复杂度的要求）
     * @param nums
     * @return
     */
    public int longestConsecutive0(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        Arrays.sort(nums);
        int max = 1;
        int pre = nums[0];
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]-pre==0){
                continue;
            }
            else if(nums[i]-pre==1){
                temp++;
                pre = nums[i];
            }else {
                max = Math.max(max,temp);
                temp = 1;
                pre = nums[i];
            }
        }
        max = Math.max(max,temp);
        return max;
    }

    /**
     * hash表+特殊逻辑处理
     * 再不排序的情况下，常规思路就是对于每一个元素x，以其为起点，看看x+1、x+2在不在，计算最长连续长度
     * 然后对于其中所有的长度，取最大值；
     * x+1、x+2在不在使用hash表来做，可以降到O(1)的复杂度
     *
     * 优化点：其实不用对所有元素都做上面的动作，比如存在 1，2，5，7的元素；对1做了上面的处理后，对于2就没有必要了，因为肯定没有1为起点长
     * 所以：对于每一个元素X，如何X-1是存在hash表中的，就直接放弃为起点
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        // 用set顺便去重
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            // 只有在其x-1不再hash表中时，才去遍历
            if(!set.contains(nums[i]-1)){
                int j = 1;
                // 一直找到不连续的点，取最大长度
                while (set.contains(nums[i]+j)){
                    j++;
                }
                max = Math.max(max,j);
            }
        }
        return max;
    }
}
