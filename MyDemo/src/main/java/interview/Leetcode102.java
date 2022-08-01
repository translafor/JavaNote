package interview;

import java.util.*;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-16
 */
public class Leetcode102 {

    /**
     * 利用两个队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        Queue<TreeNode> deque1 = new LinkedList<>();
        Queue<TreeNode> deque2 = new LinkedList<>();
        deque1.add(root);
        while (!deque1.isEmpty() || !deque2.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            // 把这一层加入list；并将这一层的左右子树存入队列，供下次遍历使用
            if (!deque1.isEmpty()) {
                doIt(deque2, deque1, temp);
            } else {
                doIt(deque1, deque2, temp);
            }
            res.add(temp);
        }
        return res;
    }

    private void doIt(Queue<TreeNode> deque1, Queue<TreeNode> deque2, List<Integer> temp) {
        while (!deque2.isEmpty()) {
            TreeNode pop = deque2.poll();
            temp.add(pop.val);
            if (null != pop.left) {
                deque1.add(pop.left);
            }
            if (null != pop.right) {
                deque1.add(pop.right);
            }
        }
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
}
