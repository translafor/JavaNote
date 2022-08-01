package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-17
 */
public class Leetcode104 {

    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public int maxDepth0(TreeNode root) {
        if(null==root){
            return 0;
        }
        if(null==root.left && null==root.right){
            return 1;
        }
        if(null==root.left){
            return maxDepthSun(root.right,1);
        }
        if(null==root.right){
            return maxDepthSun(root.left,1);
        }
        return Math.max(maxDepthSun(root.left,1),maxDepthSun(root.right,1));
    }

    private int maxDepthSun(TreeNode root, int i) {
        if(null==root.left && null==root.right){
            return i+1;
        }
        if(null==root.left){
            return maxDepthSun(root.right,i+1);
        }
        if(null==root.right){
            return maxDepthSun(root.left,i+1);
        }
        return Math.max(maxDepthSun(root.left,i+1),maxDepthSun(root.right,i+1));
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
