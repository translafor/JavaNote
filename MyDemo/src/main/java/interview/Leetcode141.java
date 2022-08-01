package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-07-21
 */
public class Leetcode141 {

    /**
     * 法一：hash表，不多说
     * 法二：快慢指针；一个每次走一步 一个每次走两步；有环的话终会相遇
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode step1 = head.next;
        ListNode step2 = head.next.next;
        while (step1.next != null && step2.next != null && step2.next.next != null) {
            if (step1 == step2) {
                return true;
            }
            step1 = step1.next;
            step2 = step2.next.next;
        }
        return false;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
