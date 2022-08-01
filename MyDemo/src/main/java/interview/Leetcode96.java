package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-09
 */
public class Leetcode96 {
//    public int numTrees(int n) {
////        List<Integer> res = new ArrayList<>();
//        Integer res = 0;
//        for (int i = 1; i <= n; i++) {
//            res+=doit(i-1,i+1,0,n);
//        }
//        return res;
//    }
//
//    private Integer doit(int left, int right,int min,int max) {
//        int leftNum = 1;
//        if(left!=min){
//            leftNum = 0;
//            for (int i = min+1; i <= left; i++) {
//                leftNum += doit(i-1,i+1,min,left);
//            }
//        }
//        int rightNum = 1;
//
//        if(right!=max){
//            rightNum = 0;
//            for (int i = right; i <= max; i++) {
//                leftNum += doit(i-1,i+1,right,max);
//            }
//        }
//        return leftNum*rightNum;
//    }

    public int numTrees(int n) {
        // g[i]表示长度为i，对应的二叉搜索树的不同数量
        int[] g = new int[n + 1];
        // 显然：当长度为0/1时对应的数量都是1
        g[0] = 1;
        g[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 当长度为i时，可能以第一个元素到第i个元素都可能为顶点；所以把这些可能都加起来
            for (int j = 1; j <= i; j++) {
                // 当以j为顶点后，当前可能性就是j左边的二叉排序树数量*右边的数量（即组合）
                g[i] += g[j - 1] * g[i - j];
            }
        }
        return g[n];
    }
}
