package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-06-20
 */
public class Leetcode105 {

    /**
     * 这里递归时，直接采用下标的方式去记录，而不直接用数组会减少很多遍历，所以先用map存下
     */
    private static Map<Integer, Integer> inMap = new HashMap<>();
    private static int[] preorder;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < preorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        Leetcode105.preorder = preorder;
        return doit(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode doit(int preFirst, int preLast, int inFirst, int inLast) {
        if (preFirst > preLast) {
            return null;
        }
        // 获取当前分支树的root节点
        int root = preorder[preFirst];
        // 找到那个root节点的位置,这就可以将中序遍历inorder，拆分成两棵树了
        Integer location = inMap.get(root);

        // 拆分成两棵树后，就可以利用递归指定左右子树
        // 但是此时有个比较关键点，左右子树的root根在preorder中怎么找到，或者说怎么找到右子树的根
        // 可以想到，当把root 和 左子树都去掉以后，中序遍历剩下的就是右子树了
        TreeNode treeNode = new TreeNode(root);
        // 计算，左子树有多长
        int leftLength = (location - inFirst);
        // preFirst+1代表去掉root，后的元素就是左子树的root
        treeNode.left = doit(preFirst + 1, preFirst + leftLength, inFirst, location - 1);
        // preFirst+leftLength+1代表去掉root和左子树。。。
        treeNode.right = doit(preFirst + leftLength + 1, preLast, location + 1, inLast);
        return treeNode;
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

