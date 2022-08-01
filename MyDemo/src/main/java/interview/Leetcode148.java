package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-07-25
 */
public class Leetcode148 {

    /**
     * 插入排序 o(n2)的复杂度；不满足题目要求
     *
     * @param head
     * @return
     */
    public static ListNode sortList0(ListNode head) {
        ListNode res = new ListNode(0);
        ListNode listNode = res;
        while (head != null) {
            // 遍历每一个，去比较插入到新的排序数组中
            doit(listNode, head);
            listNode = res;
            head = head.next;
        }
        return res.next;
    }

    private static void doit(ListNode listNode, ListNode head) {
        ListNode node = new ListNode(head.val);
        ListNode pre = listNode;
        listNode = listNode.next;
        while (listNode != null) {
            // 找到插入点插入
            if (node.val <= listNode.val) {
                pre.next = node;
                node.next = listNode;
                return;
            }
            pre = listNode;
            listNode = listNode.next;
        }
        pre.next = node;
    }

    /**
     * 归并排序
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        return doit2(head, null);
    }

    private static ListNode doit2(ListNode head, ListNode tail) {
        // 容易出错的误区
        // 第二个参数：tail节点，应该是代表这个链表的后一个元素(即last.next)，比如(0-mid) (mid-null)；因为整个链表的后一个元素是null
        // 这样的好处是，保证mid元素只会出现在一个表达式中(便于边界条件)

        if (null == head) {
            return null;
        }
        // 代表只有一个元素
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode mid = head;
        ListNode right = head;
        // 容易错点：这里一定要用tail作为边界条件了
        while (right != tail && tail != right.next) {
            mid = mid.next;
            right = right.next.next;
        }
        ListNode listNode1 = doit2(head, mid);
        ListNode listNode2 = doit2(mid, tail);
        ListNode merge = merge(listNode1, listNode2);
        return merge;
    }

    private static ListNode merge(ListNode left, ListNode right) {
        // 这里可以直接这么拼接的关键在于，left、right都已经是独立的链表了；前面已经把多余的隔断了（head.next = null;）
        // 傀儡节点
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, left1 = left, right1 = right;
        while (left1 != null && right1 != null) {
            if (left1.val < right1.val) {
                temp.next = left1;
                left1 = left1.next;
            } else {
                temp.next = right1;
                right1 = right1.next;
            }
            temp = temp.next;
        }
        // 有一个链走完以后，直接拼剩下的链表就好
        if (left1 != null) {
            temp.next = left1;
        }
        if (right1 != null) {
            temp.next = right1;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(4);
        ListNode a = listNode;
        listNode.next = new ListNode(2);
        listNode = listNode.next;

        listNode.next = new ListNode(1);
        listNode = listNode.next;

        listNode.next = new ListNode(3);
        listNode = listNode.next;

        sortList0(a);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
