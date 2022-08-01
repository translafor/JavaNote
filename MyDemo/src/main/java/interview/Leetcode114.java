package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-22
 */
public class Leetcode114 {

    public void flatten2(TreeNode root) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        preSortTree(treeNodeList,root);

        for (int i = 0; i < treeNodeList.size(); i++) {
            if(i==treeNodeList.size()-1){
                treeNodeList.get(i).left=null;
                treeNodeList.get(i).right=null;
            }else {
                treeNodeList.get(i).left=null;
                treeNodeList.get(i).right=treeNodeList.get(i+1);
            }
        }
    }

    private void preSortTree(List<TreeNode> treeNodeList, TreeNode root) {
        if(null==root){
            return;
        }
        treeNodeList.add(root);
        preSortTree(treeNodeList,root.left);
        preSortTree(treeNodeList,root.right);
    }

    public void flatten(TreeNode root) {
        if(null==root){
            return;
        }
        // 这里是先找到最左的元素
        flatten(root.left);
        // 然后处理最左元素的右元素
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right!=null){
            // 找到最左元素的最右元素==这个相当与左子树的最后一个元素了（把这个元素置为右子树的前一个元素即可）
            root = root.right;
        }
        // 对右子树处理
        flatten(right);
        root.right = right;
    }

    public static class TreeNode {
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
