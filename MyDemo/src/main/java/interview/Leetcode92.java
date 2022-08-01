package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-07-14
 */
public class Leetcode92 {

    /**
     * 法一：两次遍历
     * 第一次：先找到pre 和 找到rightNode；然后把中间那块儿反转即可
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween0(ListNode head, int left, int right) {
        // 虚拟节点：很好的思想，这种头部会变得逻辑，都可以借鉴
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        
        // 找到pre
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 找到rightNode
        ListNode rightNode = pre;
        for (int i = 0; i < right - left+1; i++) {
            rightNode = rightNode.next;
        }

        // 切断
        ListNode leftNode =  pre.next;
        pre.next = null;

        ListNode last = rightNode.next;
        rightNode.next = null;

        // 反转断掉的部分
        ListNode reverse = reverse(leftNode);

        // 重新指向
        leftNode.next = last;
        pre.next = reverse;

        return dummyNode.next;
    }

    public ListNode reverse(ListNode head) {
        if(null==head || head.next==null){
            return head;
        }
        ListNode lastNode = reverse(head.next);
        ListNode next = head.next;
        head.next = null;
        next.next = head;
        return lastNode;
    }

    /**
     * 一次遍历：头插法
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 虚拟节点：很好的思想，这种头部会变得逻辑，都可以借鉴
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        // 这里需要让pre的默认值等于傀儡节点，为的是兼容（left==1的情况，比如[3,5] ,1 ,2）
        ListNode pre = dummyNode;
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
            head = head.next;
        }
        head = head.next;
        for (int i = 0; i < right-left; i++) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

//    public static void main(String[] args) {
//        int[] a = {1,2,3,4,5};
//        ListNode l = new ListNode(a[0]);
//        ListNode root = l;
//        for (int i = 1; i < a.length; i++) {
//            ListNode temp = new ListNode(a[i]);
//            l.next = temp;
//            l = temp;
//        }
//        reverseBetween(root,2,4);
//    }

//
//    private ListNode doit(ListNode head, int right) {
//
//    }


}
