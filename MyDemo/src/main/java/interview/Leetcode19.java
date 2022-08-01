package interview;

import java.io.IOException;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-13
 */
public class Leetcode19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        //左指针
        ListNode leftNode = head;
        //右指针
        ListNode rightNode = head;
        // 确定右指针的位置
        for (int i = 0; i < n - 1; i++) {
            rightNode = rightNode.next;
        }
        // 特殊处理，删除的节点是头节点这种情况；（如果右指针直接指向了末尾，代表删除的是头节点）
        if (null == rightNode.next) {
            return head.next;
        }
        while (rightNode.next != null) {
            pre = leftNode;
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        pre.next = leftNode.next;
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

