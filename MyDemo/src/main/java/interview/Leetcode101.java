package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-15
 */
public class Leetcode101 {
    public boolean isSymmetric0(TreeNode root) {
        return isSymmetricTwo(root.left,root.right);
    }

    private boolean isSymmetricTwo(TreeNode left, TreeNode right) {
        // 如果都是空节点了，则符合推出条件
        if(null==left && null==right){
            return true;
        }
        // 一个为null，一个不是，则不对称
        if(null==left || null==right){
            return false;
        }
        // 不相等，则不对称
        if(left.val!=right.val){
            return false;
        }
        // 左.左==右.右 左.右==右.左
        return isSymmetricTwo(left.left, right.right) && isSymmetricTwo(left.right, right.left);
    }

//    public boolean isSymmetric1(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<>();
//        while (!stack.isEmpty() || null!=root){
//            while (null!=root){
//                stack.add(root);
//                root=root.left;
//            }
//            TreeNode pop = stack.pop();
//            list.add(pop.val);
//            root = pop.right;
//        }
//        int length = list.size();
//        int left = 0,right = length-1;
//        while (left<=right){
//            if(list.get(left).intValue()!=list.get(right)){
//                return false;
//            }
//            left++;
//            right--;
//        }
//        return true;
//    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
