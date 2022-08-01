package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-07-13
 */
public class JZ24 {
    /**
     * 双指针
     *
     * @param head
     * @return
     */
    public ListNode reverseList0(ListNode head) {
        // 需要一个pre节点记录上一个节点
        ListNode pre = null;
        while (head != null) {
            // 临时记录下next节点，便于将head.next指向pre后；还能找回来
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // 遍历到最后一个节点时return；证明这个节点可以开始变向了
        if(head==null || head.next == null ){
            return head;
        }
        // 一直往后吗递归，递归到head==倒数第二个节点；然后对倒数第一个节点变向
        // 和前面不同的时，这里就不记录pre的形式，而是在n-1节点时，操作n节点
        // 同时把最后一个节点记录下来，需要return
        ListNode lastNode = reverseList(head.next);
        // 将倒数第一个节点变向指向倒数第二个节点
        head.next.next = head;
        // 把倒数第二个节点的next指针取掉
        head.next = null;
        // 此时倒数第三个节点，倒数第一个节点都指向倒数第二个
        // 往上一层后，再处理倒数第二个节点指向倒数第三个。。
        return lastNode;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
