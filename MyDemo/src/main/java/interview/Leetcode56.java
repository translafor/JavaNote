package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-31
 */
public class Leetcode56 {
    public static int[][] merge(int[][] intervals) {
        // 按照第一个元素升序排序，第一个元素相等就按第二个
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        // 排序了以后，证明后面的第1个元素一定大于前面的第一个元素；则我们只需要比较后面的第一个元素是否在前面那组元素的中间
        // 来决定是否需要合并即可；如果需要合并，则第二个元素需要取两组第二个元素中最大的
        // 如果不需要合并，直接插入；
        // 另外，每个比较时，当前组，只需要与上一组对比即可；排序后，只存在和上一组合并的可能；可以用反证法来证明（利用第一个元素升序的特性）
        for (int i = 1; i < intervals.length; i++) {
            int[] pre = res.get(res.size() - 1);
            int[] interval = intervals[i];
            if (interval[0] <= pre[1]) {
                res.set(res.size() - 1, new int[]{pre[0], Math.max(interval[1], pre[1])});
            } else {
                res.add(interval);
            }
        }
        int[][] result = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
