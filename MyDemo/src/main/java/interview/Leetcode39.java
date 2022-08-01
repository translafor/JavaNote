package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-20
 */
public class Leetcode39 {
    /**
     * 暴力递归
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum0(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        doit0(new ArrayList<>(), res, candidates, target, 0);
        return res;
    }

    private void doit0(List<Integer> item, List<List<Integer>> res, int[] candidates, int target, int n) {
        if (target == 0) {
            res.add(item);
            return;
        }
        if (target < 0) {
            return;
        }
        // n的目的是为了防重复数列，比如对于candidates=[2,3,4] target=5时，[2,3] [3,2]是重复的
        // 所以我们枚举可能性时，只要[2,3]，这种可能；即只从当前位置往后(包括自身)枚举可能性，不往前枚举；这样就可以不重复
        for (int i = n; i < candidates.length; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(item);
            temp.add(candidates[i]);
            doit0(temp, res, candidates, target - candidates[i], i);
        }
    }

    /**
     * 回溯剪枝
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        doit(new ArrayList<>(), res, candidates, target, 0);
        return res;
    }

    private void doit(List<Integer> item, List<List<Integer>> res, int[] candidates, int target, int n) {
        for (int i = n; i < candidates.length; i++) {
            item.add(candidates[i]);
            int temp = target - candidates[i];
            if(temp==0){
                res.add(item);
                item.remove(item.size()-1);
                return;
            }
            if(temp<0){
                item.remove(item.size()-1);
                return;
            }
            doit(item, res, candidates, temp, i);
        }
    }
}
