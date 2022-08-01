package interview;

import java.util.Stack;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-13
 */
public class Leetcode98 {

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public boolean isValidBST0(TreeNode root) {
        // 核心利用：维护一个最大值，左子树都要小于这个值，维护一个最小值，右子树都要大于这个值（最小值最大值都是root.val）
        return isValidBSTSun(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    private boolean isValidBSTSun(TreeNode root, long maxValue, long minValue) {
        if (null == root) {
            return true;
        }
        // 相等也不行
        if (root.val >= maxValue) {
            return false;
        }
        if (root.val <= minValue) {
            return false;
        }
        return isValidBSTSun(root.left, root.val, minValue) && isValidBSTSun(root.right, maxValue, root.val);
    }

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

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Long pre = Long.MIN_VALUE;
        while (!stack.isEmpty() || null!=root){
            // 因为中序遍历为 左->中->you;先找到最左节点
            while (null!=root){
                stack.add(root);
                root = root.left;
            }

            // 拿出最左节点；因为这个节点的左节点是null（循环推出的条件），所以直接处理当前节点和其右子树即可
            TreeNode pop = stack.pop();
            if(pop.val<=pre){
                return false;
            }
            // 对右子树也进行一套这个流程
            pre = Long.valueOf(pop.val);
            root = pop.right;
        }
        return true;
    }
}
